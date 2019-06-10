package com.amadeus.framework.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

public class QcloudSmsUtil {

    // 单发短信
    public SmsSingleSenderResult sendSingleSms(int verificationCode,String telephoneNumber) {
        SmsSingleSenderResult result = null;
        try {
            // 短信应用SDK AppID
            // 1400开头
            int appid = 1400145734;
            // 短信应用SDK AppKey
            String appkey = "78422acf7b585badbb3fdeb92b0f7f97";
            String msg=verificationCode+"为您的登录验证码，请于5分钟内填写。如非本人操作，请忽略本短信。";
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            result = ssender.send(0, "86", telephoneNumber,
                    msg, "", "");
        } catch (HTTPException | JSONException | IOException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } // json解析错误
        // 网络IO错误
        return result;
    }

}
