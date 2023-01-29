package com.ossez.wechat.demo.config;

import com.ossez.wechat.demo.common.enums.StorageCategory;
import com.ossez.wechat.demo.properties.RedisProperties;
import com.ossez.wechat.demo.properties.WeChatOfficialAccountProperties;
import com.google.common.collect.Sets;
import com.ossez.wechat.oa.config.ConfigStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.redis.RedisTemplateWxRedisOps;
import com.ossez.wechat.common.redis.WxRedisOps;
import com.ossez.wechat.oa.config.WxMpHostConfig;
import com.ossez.wechat.oa.config.DefaultConfigStorage;
import com.ossez.wechat.oa.config.impl.RedisConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolAbstract;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

/**
 * 微信公众号存储策略自动配置.
 *
 * @author Luo
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class WeChatStorageAutoConfiguration {
    private final ApplicationContext applicationContext;

    private final WeChatOfficialAccountProperties wxMpProperties;

    @Bean
    @ConditionalOnMissingBean(ConfigStorage.class)
    public ConfigStorage wxMpConfigStorage() {
        StorageCategory type = wxMpProperties.getConfigStorage().getType();
        ConfigStorage config;
        switch (type) {
            case REDIS:
                config = redisTemplateConfigStorage();
                break;
            default:
                config = defaultConfigStorage();
                break;
        }
        // wx host config
        if (null != wxMpProperties.getHosts() && StringUtils.isNotEmpty(wxMpProperties.getHosts().getApiHost())) {
            WxMpHostConfig hostConfig = new WxMpHostConfig();
            hostConfig.setApiHost(wxMpProperties.getHosts().getApiHost());
            hostConfig.setMpHost(wxMpProperties.getHosts().getMpHost());
            hostConfig.setOpenHost(wxMpProperties.getHosts().getOpenHost());
            config.setHostConfig(hostConfig);
        }
        return config;
    }

    private ConfigStorage defaultConfigStorage() {
        DefaultConfigStorage config = new DefaultConfigStorage();
        setWxMpInfo(config);
        return config;
    }


    private ConfigStorage redisTemplateConfigStorage() {
        StringRedisTemplate redisTemplate = null;
        try {
            redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            if (null == redisTemplate) {
                redisTemplate = (StringRedisTemplate) applicationContext.getBean("stringRedisTemplate");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (null == redisTemplate) {
            redisTemplate = (StringRedisTemplate) applicationContext.getBean("redisTemplate");
        }

        WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
        RedisConfigStorage wxMpRedisConfig = new RedisConfigStorage(redisOps,
                wxMpProperties.getConfigStorage().getKeyPrefix());

        setWxMpInfo(wxMpRedisConfig);
        return wxMpRedisConfig;
    }

    private void setWxMpInfo(DefaultConfigStorage config) {
        WeChatOfficialAccountProperties properties = wxMpProperties;
        WeChatOfficialAccountProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
        config.setAppId(properties.getAppId());
        config.setSecret(properties.getSecret());
        config.setToken(properties.getToken());
        config.setAesKey(properties.getAesKey());

        config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
        config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
        config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
        if (configStorageProperties.getHttpProxyPort() != null) {
            config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
        }
    }

    private JedisPoolAbstract getJedisPool() {
        RedisProperties redis = wxMpProperties.getConfigStorage().getRedis();

        JedisPoolConfig config = new JedisPoolConfig();
        if (redis.getMaxActive() != null) {
            config.setMaxTotal(redis.getMaxActive());
        }
        if (redis.getMaxIdle() != null) {
            config.setMaxIdle(redis.getMaxIdle());
        }
        if (redis.getMaxWaitMillis() != null) {
            config.setMaxWaitMillis(redis.getMaxWaitMillis());
        }
        if (redis.getMinIdle() != null) {
            config.setMinIdle(redis.getMinIdle());
        }
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        if (StringUtils.isNotEmpty(redis.getSentinelIps())) {
            Set<String> sentinels = Sets.newHashSet(redis.getSentinelIps().split(","));
            return new JedisSentinelPool(redis.getSentinelName(), sentinels);
        }

        return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(),
                redis.getDatabase());
    }
}
