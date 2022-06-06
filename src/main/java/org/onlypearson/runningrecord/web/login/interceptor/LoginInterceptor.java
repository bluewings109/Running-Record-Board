package org.onlypearson.runningrecord.web.login.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.web.SessionConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("request={}",request);
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(SessionConstant.LOGIN_MEMBER) == null){
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login?redirectURL=" + request.getRequestURI());
            return false;
        }
        return true;
    }

}
