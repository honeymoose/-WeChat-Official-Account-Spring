package com.ossez.wechat.demo.config;

import com.ossez.wechat.demo.properties.WxMpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * .
 *
 * @author someone
 */
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
@Import({ WxMpStorageAutoConfiguration.class, WxMpServiceAutoConfiguration.class })
public class WxMpAutoConfiguration {
}
