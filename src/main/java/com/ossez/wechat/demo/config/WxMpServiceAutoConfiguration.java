package com.ossez.wechat.demo.config;

import com.ossez.wechat.demo.enums.HttpClientType;
import com.ossez.wechat.demo.properties.WxMpProperties;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.api.impl.WxMpServiceHttpClientImpl;
import com.ossez.wechat.oa.api.impl.WxMpServiceImpl;
import com.ossez.wechat.oa.api.impl.WxMpServiceJoddHttpImpl;
import com.ossez.wechat.oa.api.impl.WxMpServiceOkHttpImpl;
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
  public WxMpService wxMpService(WxMpConfigStorage configStorage, WxMpProperties wxMpProperties) {
    HttpClientType httpClientType = wxMpProperties.getConfigStorage().getHttpClientType();
    WxMpService wxMpService;
    switch (httpClientType) {
      case OkHttp:
        wxMpService = newWxMpServiceOkHttpImpl();
        break;
      case JoddHttp:
        wxMpService = newWxMpServiceJoddHttpImpl();
        break;
      case HttpClient:
        wxMpService = newWxMpServiceHttpClientImpl();
        break;
      default:
        wxMpService = newWxMpServiceImpl();
        break;
    }

    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  private WxMpService newWxMpServiceImpl() {
    return new WxMpServiceImpl();
  }

  private WxMpService newWxMpServiceHttpClientImpl() {
    return new WxMpServiceHttpClientImpl();
  }

  private WxMpService newWxMpServiceOkHttpImpl() {
    return new WxMpServiceOkHttpImpl();
  }

  private WxMpService newWxMpServiceJoddHttpImpl() {
    return new WxMpServiceJoddHttpImpl();
  }

}
