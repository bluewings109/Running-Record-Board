package org.onlypearson.runningrecord.web.TestDataInit;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestMemberDataInit {

    private final MemberService memberService;

    @Autowired
    public TestMemberDataInit(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostConstruct
    void initTestMember(){
        Member testMember = new Member();
        testMember.setLoginId("test");
        testMember.setPassword("test123");
        memberService.join(testMember);
    }
}
