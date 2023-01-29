package com.ossez.wechat.demo.properties;


import com.ossez.wechat.demo.common.enums.HttpClientCategory;
import com.ossez.wechat.demo.common.enums.StorageCategory;
import com.ossez.wechat.demo.model.entity.WeChatHost;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

import static com.ossez.wechat.demo.common.enums.StorageCategory.MEM;

/**
 * WeChat Official Account Config
 * @author YuCheng Hu
 */
@Data
@ConfigurationProperties(prefix = "wechat.official-account")
public class WeChatOfficialAccountProperties {
  private String appId; //微信公众号 appId
  private String secret; //微信公众号 secret
  private String token; //微信公众号 token
  private String aesKey; //微信公众号 aesKey
  private WeChatHost hosts; //自定义 host 配置

  /**
   * 存储策略
   */
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    /**
     * 存储类型.
     */
    private StorageCategory type = MEM;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wx";

    /**
     * redis连接配置.
     */
    @NestedConfigurationProperty
    private final RedisProperties redis = new RedisProperties();

    /**
     * http客户端类型.
     */
    private HttpClientCategory httpClientCategory = HttpClientCategory.OK_HTTP;

    /**
     * http代理主机.
     */
    private String httpProxyHost;

    /**
     * http代理端口.
     */
    private Integer httpProxyPort;

    /**
     * http代理用户名.
     */
    private String httpProxyUsername;

    /**
     * http代理密码.
     */
    private String httpProxyPassword;

  }

}
