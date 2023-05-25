package cn.lanqiao.springboot3.config.handler;

import cn.lanqiao.springboot3.entity.AdminUser;
import cn.lanqiao.springboot3.service.AdminUserService;
import cn.lanqiao.springboot3.config.annotation.TokenToUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;

@Component
// 标注Spring管理的Bean，使用@Component注解在一个类上，表示将此类标记为Spring容器中的一个Bean。
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private AdminUserService adminUserService;

    public TokenToUserMethodArgumentResolver() {
    }

    //supportsParameter方法用于判断该解析器是否支持当前方法的参数，如果参数上有TokenToUser注解，则返回true，否则返回false。
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    // resolveArgument方法用于解析方法参数。如果参数上有TokenToUser注解，则从请求头中获取令牌token，
    // 再通过注入的AdminUserService根据令牌获取对应的用户信息，最后将用户信息作为方法参数返回。
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
        if (parameter.getParameterAnnotation(TokenToUser.class) != null) {
            AdminUser adminUser = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                adminUser = adminUserService.getAdminUserByToken(token);
            }
            return adminUser;
        }
        return null;
    }

    //用于获取请求体中的字节数组。
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
}
