# WeChat-Official-Account-Spring

<p align="center">
    <a href="https://github.com/honeymoose">
        <img height=85 src="https://avatars1.githubusercontent.com/u/45009982?s=200&v=4">
    </a>
    <br>This project builds by JDK 11 and OpenJ9 for JVM.
</p>

* [社区和讨论 (community)](https://www.ossez.com/tag/wechat)

Spring Boot API Project for WeChat-J library.

这是一个基于 WeChat-J 和 Spring Boot 的 API 微信公众号测试程序。

我们旨在提供一个初始化的开发框架，能够让应用在使用 Spring Boot 框架的基础上让你的微信公众号快速接入微信平台。

# 项目配置

和所有的 Java 项目的标准配置一样，你首先需要把依赖添加到你的项目中，然后完成属性文件配置。

在这个基础上，需要有一些先决条件。

## 先决条件

需要对微信公众号进行测试，你首先需要有一个微信公众号，如果没有的话，你可以使用微信提供的测试公众号来获得需要的 appID 和
appsecret。

* [微信测试平台获得测试账号](https://www.ossez.com/t/topic/14281)

## 依赖

项目使用的是 Maven，如果你只希望使用 WeChat-J，你只需要往你的项目中添加依赖即可。

因为我们目前还在对项目进行整理，所以还是使用的 SNAPSHOT 版本。

```xml

<dependency>
    <groupId>com.ossez.wechat</groupId>
    <artifactId>wechat-j-oa</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## 修改配置文件

```properties
# WeChat Official Cccount Conf
wechat.official-account.app-id=appId
wechat.official-account.secret=secret
wechat.official-account.token=token
wechat.official-account.aes-key=aesKey
# 存储配置redis(可选)
wx.mp.config-storage.type=Jedis                     # 配置类型: Memory(默认), Jedis, RedisTemplate
wx.mp.config-storage.key-prefix=wx                  # 相关redis前缀配置: wx(默认)
wx.mp.config-storage.redis.host=127.0.0.1
wx.mp.config-storage.redis.port=6379
#单机和sentinel同时存在时，优先使用sentinel配置
#wx.mp.config-storage.redis.sentinel-ips=127.0.0.1:16379,127.0.0.1:26379
#wx.mp.config-storage.redis.sentinel-name=mymaster
# http客户端配置
wx.mp.config-storage.http-client-type=httpclient      # http客户端类型: HttpClient(默认), OkHttp, JoddHttp
wx.mp.config-storage.http-proxy-host=
wx.mp.config-storage.http-proxy-port=
wx.mp.config-storage.http-proxy-username=
wx.mp.config-storage.http-proxy-password=
# 公众号地址host配置
#wx.mp.hosts.api-host=http://proxy.com/
#wx.mp.hosts.open-host=http://proxy.com/
#wx.mp.hosts.mp-host=http://proxy.com/
```

## 自动注入的类型

- `WxMpService`
- `WxMpConfigStorage`

# 联系方式

请使用下面的联系方式和我们联系。

* [社区和讨论](https://www.ossez.com/tag/chat-gpt)

| 联系方式名称           | 联系方式                                          |
|------------------|-----------------------------------------------|
| 电子邮件（Email）      | [yhu@ossez.com](mailto:yhu@ossez.com)         |
| QQ 或微信（WeChat）   | 103899765                                     |
| QQ 交流群           | 15186112                                      |
| 社区论坛 （Community） | https://www.ossez.com/c/computer-technology/7 |

# 公众平台

我们建议您通过社区论坛来和我们进行沟通，请关注我们公众平台上的账号

## 微信公众号

![](https://cdn.ossez.com/img/cwikius/cwikius-qr-wechat-search-w400.png)

## 头条号

我们也在头条号上创建了我们的公众号，请扫描下面的 QR 关注我们的头条号。

![](https://cdn.ossez.com/img/cwikius/cwikus-qr-toutiao.png)

## 知乎

请关注我们的知乎：https://www.zhihu.com/people/huyuchengus

# License

[OpenAI-J is licensed under the MIT License](https://github.com/honeymoose/openai-j/blob/main/LICENSE)
