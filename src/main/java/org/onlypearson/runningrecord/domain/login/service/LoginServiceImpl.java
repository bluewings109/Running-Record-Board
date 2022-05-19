package org.onlypearson.runningrecord.domain.login.service;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final MemberService memberService;

    @Autowired
    public LoginServiceImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Member login(String loginId, String password) {
        return memberService.getMemberByLoginId(loginId).filter(member -> member.getPassword().equals(password)).orElse(null);
    }
}
