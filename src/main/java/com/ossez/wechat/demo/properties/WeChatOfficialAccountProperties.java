package com.ossez.wechat.demo.properties;


import com.ossez.wechat.demo.common.enums.HttpClientCategory;
import com.ossez.wechat.demo.common.enums.StorageCategory;
import com.ossez.wechat.demo.model.entity.WeChatDataStorage;
import com.ossez.wechat.demo.model.entity.WeChatHost;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

import static com.ossez.wechat.demo.common.enums.StorageCategory.MEM;

/**
 * WeChat Official Account Config
 *
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
    private final WeChatDataStorage weChatDataStorage = new WeChatDataStorage();


}
