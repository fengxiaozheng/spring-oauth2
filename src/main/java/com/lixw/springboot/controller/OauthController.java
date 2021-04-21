package com.lixw.springboot.controller;

import com.lixw.springboot.service.OauthService;
import com.lixw.springboot.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author lixw
 * @date 2021/04/21
 */
@Controller
@Slf4j
public class OauthController {

    @Autowired
    private OauthService oauthService;


    @RequestMapping("login")
    public String login() {
        return "home";
    }

    @RequestMapping("oauth-callback")
    @ResponseBody
    public String githubCallback(String code, String state, Model model, HttpServletRequest req) {
        log.info("收到回调：{}", code);

        if(!StringUtils.isEmpty(code)&&!StringUtils.isEmpty(state)){
            //拿到我们的code,去请求token
            //发送一个请求到
            String responseStr = oauthService.getAccessToken(code);
            log.info("请求token返回：{}", responseStr);
            // 调用方法从map中获得返回的--》 令牌
            String token = null;
            try {
                token = JsonUtils.parseResponseEntity(responseStr).get("access_token");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //根据token发送请求获取登录人的信息  ，通过令牌去获得用户信息
            responseStr = oauthService.getUserInfo(token);
            // 成功则登陆
            return "登录成功，用户信息：" + responseStr;
        }
        // 否则返回到登陆页面
        return "登录失败";
    }
}
