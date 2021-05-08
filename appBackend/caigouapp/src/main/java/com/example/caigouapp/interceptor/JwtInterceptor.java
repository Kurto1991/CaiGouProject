package com.example.caigouapp.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.caigouapp.annotation.PassToken;
import com.example.caigouapp.annotation.UserLoginToken;
import com.example.caigouapp.common.Constant;
import com.example.caigouapp.entity.User;
import com.example.caigouapp.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");//从请求头中取出token

        if(!(handler instanceof Method)){
            return true;
        }

        HandlerMethod handlerMethod =(HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果有passtoken注解，则跳过验证
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }

        //如果有需要UserLoginToken的注解
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if(userLoginToken.required()){
                //判定token是否为空
                if(StringUtils.isBlank(token)){
                    throw new RuntimeException("请登录后进行操作");
                }

                Integer userId;

                try{
                    //获取用户Id
                    userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));

                }
                catch (JWTDecodeException e){
                    throw new RuntimeException("401");//用户无权限
                }
                User user = userService.findById(userId);

                if(user == null){
                    throw new RuntimeException("用户不存在或密码错误，请重新登录");
                }

                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constant.TOKEN_SECRET)).build();
                try{

                }
                catch (JWTVerificationException e){
                    throw new RuntimeException("401");
                }
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
