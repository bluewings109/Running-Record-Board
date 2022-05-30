package org.onlypearson.runningrecord.web.login;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.login.service.LoginService;
import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.web.SessionConstant;
import org.onlypearson.runningrecord.web.login.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    String loginForm(Model model){
        model.addAttribute(new LoginForm());
        return "loginForm";
    }

    @PostMapping("/login")
    String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            return "loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "loginForm";
        }

        //세션 저장
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConstant.LOGIN_MEMBER, loginMember);


        return "redirect:/";
    }

    @GetMapping("/logout")
    String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.removeAttribute(SessionConstant.LOGIN_MEMBER);

        return "redirect:/";
    }

}
