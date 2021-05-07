package com.example.caigouapp.untils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.caigouapp.common.Constant;
import com.example.caigouapp.entity.User;

public class JwtUntil {
    public String getToken(User user) {
        String token = "";

        token = JWT.create().withAudience(String.valueOf(user.getId()))//将用户id保存到token里面
                            .sign(Algorithm.HMAC256(Constant.TOKEN_SECRET));

        return token;
    }

}
