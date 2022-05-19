package org.onlypearson.runningrecord.web.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.service.MemberService;
import org.onlypearson.runningrecord.web.member.form.MemberJoinForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinMemberForm(Model model) {
        model.addAttribute(new MemberJoinForm());
        return "joinMemberForm";
    }

    @PostMapping("/join")
    public String joinMember(@Validated @ModelAttribute MemberJoinForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //Bean Validation
        if(bindingResult.hasErrors()){
            return "joinMemberForm";
        }

        Member member = new Member();
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());

        memberService.join(member);

        redirectAttributes.addAttribute("joinMemberStatus",true);

        return "redirect:/";
    }
}
