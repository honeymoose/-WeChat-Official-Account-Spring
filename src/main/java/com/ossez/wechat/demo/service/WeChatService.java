package com.ossez.wechat.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.req.CustomMessage;
import com.ossez.wechat.common.model.res.QueryQuotaResponse;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMsgService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountServiceOkHttp;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatPlatformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author XieYang, YuCheng
 * @Date: 2022/10/14/16:17
 * @Description:
 */
@Service
@Slf4j
public class WeChatService {
    private final WeChatOfficialAccountService weChatOfficialAccountService;
    private final ObjectMapper objectMapper;


    @Autowired
    public WeChatService(WeChatOfficialAccountService weChatOfficialAccountService, ObjectMapper objectMapper) throws WxErrorException {
        this.weChatOfficialAccountService = weChatOfficialAccountService;
        this.objectMapper = objectMapper;
    }


    /**
     * Get WeChatLoginQRUrl
     *
     * @return
     */
    public String getDomainIPs() throws WxErrorException {
        return new WeChatPlatformService(weChatOfficialAccountService).getDomainIPs();
    }

    public QueryQuotaResponse queryQuota() throws WxErrorException {
        return new WeChatPlatformService(weChatOfficialAccountService).queryQuota();
    }

    public String sendMessage() throws WxErrorException {
        CustomMessage.KfText kfText = new CustomMessage.KfText("微信异步消息发送");
        CustomMessage customMessage = new CustomMessage();
        customMessage.setToUser("o9phd5jz_We8mPs1ovmyjud97Ock");
        customMessage.setMsgType("text");
        customMessage.setText(kfText);

        return new WeChatMsgService(weChatOfficialAccountService).sendMessage(customMessage);
    }


}
