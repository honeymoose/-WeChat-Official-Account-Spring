package com.ossez.wechat.demo.controller;

import com.ossez.wechat.common.exception.WxErrorException;

import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
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

    @Autowired
    public WeChatController(WeChatOfficialAccountService weChatOfficialAccountService) {
        this.weChatOfficialAccountService = weChatOfficialAccountService;
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

        return weChatOfficialAccountService.getAccessToken();
    }


}
