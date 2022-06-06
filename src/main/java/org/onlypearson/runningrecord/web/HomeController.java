package org.onlypearson.runningrecord.web;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.web.login.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    //ArgumentResolver 사용 이전 로직
//    @RequestMapping("/")
//    String home(@SessionAttribute(name= SessionConstant.LOGIN_MEMBER, required = false) Member loginMember, Model model){
//        log.info("loginMember={}",loginMember);
//        if(loginMember == null) {
//            return "home";
//        }
//
//        model.addAttribute(loginMember);
//        return "loginHome";
//    }

    @RequestMapping("/")
    String home(@Login Member loginMember, Model model){
        log.info("loginMember={}",loginMember);
        if(loginMember == null) {
            return "home";
        }

        model.addAttribute(loginMember);
        return "loginHome";
    }
}
