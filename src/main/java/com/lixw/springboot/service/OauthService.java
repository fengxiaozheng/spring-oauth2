package com.lixw.springboot.service;

/**
 * @author lixw
 * @date 2021/04/21
 */
public interface OauthService {

    String getAccessToken(String code);

    String getUserInfo(String accessToken);
}
