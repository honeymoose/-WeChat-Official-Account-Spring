package com.ossez.wechat.demo.config;

import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.demo.common.enums.HttpClientCategory;
import com.ossez.wechat.demo.properties.WeChatOfficialAccountProperties;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatPlatformService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountServiceOkHttp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * .
 *
 * @author someone
 */
@Configuration
@EnableConfigurationProperties(WeChatOfficialAccountProperties.class)
@Import({WeChatStorageAutoConfiguration.class})
public class WeChatConfiguration {


    /**
     * weChat Service Inject
     */
    @Bean
    @ConditionalOnMissingBean
    public WeChatOfficialAccountService weChatOfficialAccountService(ConfigStorage configStorage, WeChatOfficialAccountProperties weChatOfficialAccountProperties) {
        HttpClientCategory httpClientCategory = weChatOfficialAccountProperties.getWeChatDataStorage().getHttpClientCategory();
        WeChatOfficialAccountService weChatOfficialAccountService = new WeChatOfficialAccountServiceOkHttp();

        weChatOfficialAccountService.setWxMpConfigStorage(configStorage);
        return weChatOfficialAccountService;
    }

    @Bean
    @ConditionalOnMissingBean
    public WeChatPlatformService weChatPlatformService(WeChatOfficialAccountService weChatOfficialAccountService) {
        WeChatPlatformService weChatPlatformService = new WeChatPlatformService();

        weChatPlatformService.setWeChatOfficialAccountService(weChatOfficialAccountService);
        return weChatPlatformService;
    }
}
