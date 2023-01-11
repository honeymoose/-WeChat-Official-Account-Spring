package com.ossez.wechat.demo.controller;

import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.common.exception.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Batch Controller to response all batch process request
 *
 * @author YuCheng Hu
 */
@RestController
@RequestMapping(value = "/wechat")
@Slf4j
public class WeChatController {


    @Autowired
    private WxMpService mpService;


    /**
     * @return
     */
    @GetMapping("/token")
    @ResponseBody
    public String getAccessToken() throws WxErrorException {

        return this.mpService.getAccessToken();
    }


}
