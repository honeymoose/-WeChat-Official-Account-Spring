package com.ossez.wechat.demo.config;

import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.demo.common.enums.StorageCategory;
import com.ossez.wechat.demo.model.entity.WeChatDataStorage;
import com.ossez.wechat.demo.properties.WeChatOfficialAccountProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.redis.RedisTemplateWxRedisOps;
import com.ossez.wechat.common.redis.WxRedisOps;
import com.ossez.wechat.common.config.WxMpHostConfig;
import com.ossez.wechat.common.config.DefaultConfigStorage;
import com.ossez.wechat.common.config.RedisConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

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

    private final WeChatOfficialAccountProperties weChatOfficialAccountProperties;

    @Bean
    @ConditionalOnMissingBean(ConfigStorage.class)
    public ConfigStorage wxMpConfigStorage() {
        StorageCategory type = weChatOfficialAccountProperties.getWeChatDataStorage().getType();
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
        if (null != weChatOfficialAccountProperties.getHosts() && StringUtils.isNotEmpty(weChatOfficialAccountProperties.getHosts().getApiHost())) {
            WxMpHostConfig hostConfig = new WxMpHostConfig();
            hostConfig.setApiHost(weChatOfficialAccountProperties.getHosts().getApiHost());
            hostConfig.setMpHost(weChatOfficialAccountProperties.getHosts().getMpHost());
            hostConfig.setOpenHost(weChatOfficialAccountProperties.getHosts().getOpenHost());
            config.setHostConfig(hostConfig);
        }
        return config;
    }

    private ConfigStorage defaultConfigStorage() {
        DefaultConfigStorage config = new DefaultConfigStorage();
        configWeChatServer(config);
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
        RedisConfigStorage redisConfigStorage = new RedisConfigStorage(redisOps,
                weChatOfficialAccountProperties.getWeChatDataStorage().getKeyPrefix());

        configWeChatServer(redisConfigStorage);
        return redisConfigStorage;
    }

    private void configWeChatServer(DefaultConfigStorage defaultConfigStorage) {
        WeChatOfficialAccountProperties properties = weChatOfficialAccountProperties;
        WeChatDataStorage weChatDataStorage = weChatOfficialAccountProperties.getWeChatDataStorage();

        defaultConfigStorage.setAppId(properties.getAppId());
        defaultConfigStorage.setSecret(properties.getSecret());
        defaultConfigStorage.setToken(properties.getToken());
        defaultConfigStorage.setAesKey(properties.getAesKey());

        defaultConfigStorage.setHttpProxyHost(weChatDataStorage.getHttpProxyHost());
        defaultConfigStorage.setHttpProxyUsername(weChatDataStorage.getHttpProxyUsername());
        defaultConfigStorage.setHttpProxyPassword(weChatDataStorage.getHttpProxyPassword());
        if (weChatDataStorage.getHttpProxyPort() != null) {
            defaultConfigStorage.setHttpProxyPort(weChatDataStorage.getHttpProxyPort());
        }
    }

}
