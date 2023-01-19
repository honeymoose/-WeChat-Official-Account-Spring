package com.ossez.wechat.demo.config;

import com.ossez.wechat.demo.common.enums.HttpClientType;
import com.ossez.wechat.demo.properties.WeChatOfficialAccountProperties;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.WeChatOfficialAccountServiceOkHttp;
import com.ossez.wechat.oa.config.WxMpConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号相关服务自动注册.
 *
 * @author someone
 */
@Configuration
public class WxMpServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public WeChatOfficialAccountService weChatOfficialAccountService(WxMpConfigStorage configStorage, WeChatOfficialAccountProperties wxMpProperties) {
    HttpClientType httpClientType = wxMpProperties.getConfigStorage().getHttpClientType();
    WeChatOfficialAccountService weChatOfficialAccountService  =  new WeChatOfficialAccountServiceOkHttp();

    weChatOfficialAccountService.setWxMpConfigStorage(configStorage);
    return weChatOfficialAccountService;
  }

}
