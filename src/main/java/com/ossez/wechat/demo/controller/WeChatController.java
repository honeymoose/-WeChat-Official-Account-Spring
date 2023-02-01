package com.ossez.wechat.demo.controller;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.res.NetworkCheckResponse;
import com.ossez.wechat.common.model.res.QueryQuotaResponse;
import com.ossez.wechat.demo.data.repository.redis.StudentRepository;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * WeChatController to process WeChat request
 *
 * @author YuCheng Hu
 */
@RestController
@RequestMapping(value = "/wechat")
@Slf4j
public class WeChatController {

    private final WeChatOfficialAccountService weChatOfficialAccountService;
    private final WeChatPlatformService weChatPlatformService;
    private final StudentRepository studentRepository;

    @Autowired
    public WeChatController(WeChatOfficialAccountService weChatOfficialAccountService, WeChatPlatformService weChatPlatformService, StudentRepository studentRepository) {
        this.weChatOfficialAccountService = weChatOfficialAccountService;
        this.weChatPlatformService = weChatPlatformService;
        this.studentRepository = studentRepository;
    }

    /**
     * Get weChat access token
     *
     * @return The access token
     * @throws WxErrorException WeChat API Error
     */
    @GetMapping("/token")
    @ResponseBody
    public String getAccessToken() throws WxErrorException {
        log.debug("Get access token from WeChat");
        return weChatOfficialAccountService.getAccessToken(true);
    }

    @GetMapping("/ip")
    @ResponseBody
    public String getDomainIPs() throws WxErrorException {
        log.debug("Get access token from WeChat");
        return weChatPlatformService.getDomainIPs();
    }

    @GetMapping("/networkcheck")
    @ResponseBody
    public NetworkCheckResponse checkNetwork() throws WxErrorException {
        log.debug("Get access token from WeChat");
        return weChatPlatformService.checkNetwork();
    }

    @GetMapping("/query/quota")
    @ResponseBody
    public QueryQuotaResponse queryQuota() throws WxErrorException {
        log.debug("Get access token from WeChat");
        return weChatPlatformService.queryQuota();
    }

}
