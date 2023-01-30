package com.ossez.wechat.demo.controller;

import com.ossez.wechat.common.exception.WxErrorException;

import com.ossez.wechat.demo.data.repository.redis.StudentRepository;
import com.ossez.wechat.demo.model.entity.Student;
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
    private final StudentRepository studentRepository;

    @Autowired
    public WeChatController(WeChatOfficialAccountService weChatOfficialAccountService, StudentRepository studentRepository) {
        this.weChatOfficialAccountService = weChatOfficialAccountService;
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

    @GetMapping("/api_domain_ip")
    @ResponseBody
    public String getDomainIPs() throws WxErrorException {
        log.debug("Get access token from WeChat");
        return weChatOfficialAccountService.getDomainIPs();
    }

}
