package com.example.caigouapp.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.caigouapp.common.Constant;
import com.example.caigouapp.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String getToken(User user) {
        String token="";

        token= JWT.create().withAudience(String.valueOf(user.getId()))// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(Constant.TOKEN_SECRET));// 以 password 作为 token 的密钥
        return token;
    }
}
