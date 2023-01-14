package com.ossez.wechat.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.charset.StandardCharsets;

/**
 * Email Testing
 *
 * @author YuCheng
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class WeChatTest {

    private String wechatMessageStr = StringUtils.EMPTY;

    @BeforeEach
    protected void setUp() throws Exception {
        this.wechatMessageStr = ("        <xml>\n" +
                "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "  <CreateTime>1348831860</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[this is a test - 您好]]></Content>\n" +
                "  <MsgId>1234567890123456</MsgId>\n" +
                "  <MsgDataId>xxxx</MsgDataId>\n" +
                "  <Idx>xxxx</Idx>\n" +
                "</xml>");

    }

    @AfterEach
    protected void tearDown() throws Exception {
    }


    @Test
    public void testToWeChatMessage() {
        SAXReader xmlReader = new SAXReader();
        Document document = null;



        try {
            document = xmlReader.read(IOUtils.toInputStream(wechatMessageStr, StandardCharsets.UTF_8));




//            log.debug("WeChat Message Content - [{}]", weChatMessage.getContent());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}

