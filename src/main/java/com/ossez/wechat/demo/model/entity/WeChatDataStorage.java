package com.ossez.wechat.demo.model.entity;

import com.ossez.wechat.demo.common.enums.HttpClientCategory;
import com.ossez.wechat.demo.common.enums.StorageCategory;
import com.ossez.wechat.demo.properties.RedisProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

import static com.ossez.wechat.demo.common.enums.StorageCategory.MEM;

@Getter
@Setter
@Accessors(chain = true)
public class WeChatDataStorage implements Serializable {
    private static final long serialVersionUID = -94405301936095366L;
    private StorageCategory type = MEM;
    private String keyPrefix = "wx";
    @NestedConfigurationProperty
    private final RedisProperties redis = new RedisProperties();
    private HttpClientCategory httpClientCategory = HttpClientCategory.OK_HTTP;
    private String httpProxyHost;
    private Integer httpProxyPort;
    private String httpProxyUsername;
    private String httpProxyPassword;

}
