package com.ossez.wechat.demo.config;

import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.demo.common.enums.HttpClientCategory;
import com.ossez.wechat.demo.properties.WeChatOfficialAccountProperties;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMsgService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatPlatformService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountServiceOkHttp;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

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
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WeChatPlatformService weChatPlatformService(WeChatOfficialAccountService weChatOfficialAccountService) throws WxErrorException {
        weChatOfficialAccountService.getAccessToken();
        return new WeChatPlatformService(weChatOfficialAccountService);
    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public WeChatMsgService weChatMsgService(WeChatOfficialAccountService weChatOfficialAccountService) throws WxErrorException {
//        return new WeChatMsgService(weChatOfficialAccountService);
//
//    }
}
