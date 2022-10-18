package org.onlypearson.runningrecord.web.TestDataInit;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@Slf4j
public class TestMemberDataInit {

    private final MemberRepository memberRepository;

    @Autowired
    public TestMemberDataInit(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initTestMember(){
        log.info("initTestMemberStart");
        List<Member> members = memberRepository.findAll();
        if(members.isEmpty()){
            Member member = new Member();
            member.setLoginId("test");
            member.setPassword("1234");
            memberRepository.save(member);
        }
    }
}
