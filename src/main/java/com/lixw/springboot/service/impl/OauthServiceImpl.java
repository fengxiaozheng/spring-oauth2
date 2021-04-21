package com.lixw.springboot.service.impl;

import com.lixw.springboot.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author lixw
 * @date 2021/04/21
 */
@Service
@Slf4j
public class OauthServiceImpl implements OauthService {

    private final RestTemplate restTemplate;
    private static final String CLIENT_ID = "74a731cba1aaa2c18a01";
    private static final String CLIENT_SECRET = "b322052a4abd6db05bdd58334668a5e6de6f4285";
    private static final String CALLBACK = "http://localhost:8080/oauth-callback";

    public OauthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getAccessToken(String code) {
        log.info("获取token");
        String url = "https://github.com/login/oauth/access_token?client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&code="+code+"&redirect_uri="+CALLBACK+"";
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getUserInfo(String accessToken) {
        log.info("获取用户信息");
        String USER_INFO_URL = "https://api.github.com/user?access_token=" + accessToken;
        return restTemplate.getForObject(USER_INFO_URL, String.class);
    }
}
