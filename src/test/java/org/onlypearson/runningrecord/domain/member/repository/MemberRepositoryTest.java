package org.onlypearson.runningrecord.domain.member.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        //given
        Member member = getMember();

        //when
        Member savedMember = memberRepository.save(member);

        //then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    void findById() {
        //given
        Member member = getMember();
        Member savedMember = memberRepository.save(member);
        Long savedId = savedMember.getId();

        //when
        Member findMember = memberRepository.findById(savedId).get();

        //then
        assertThat(savedMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        //given
        Member memberA = getMember();
        memberRepository.save(memberA);

        Member memberB = getMember();
        memberRepository.save(memberB);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);

    }

    @Test
    void findByLoginId() {
        //given
        Member member = getMember();
        String loginId = member.getLoginId();

        Member savedMember = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findByLoginId(loginId).get();

        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void update() {
        //given
        Member member = getMember();
        String password = member.getPassword();
        String loginId = member.getLoginId();

        memberRepository.save(member);

        Long id = member.getId();

        //when
        String changedLoginId = "USER_A";
        String changedPassword = "1234";

        MemberDto memberDto = new MemberDto();
        memberDto.setLoginId(changedLoginId);
        memberDto.setPassword(changedPassword);
        memberRepository.update(id, memberDto);

        //then
        Member updatedMember = memberRepository.findById(id).get();

        assertThat(updatedMember.getLoginId()).isEqualTo(changedLoginId);
        assertThat(updatedMember.getPassword()).isEqualTo(changedPassword);


    }

    @Test
    void delete() {
        //given
        Member member = getMember();

        Member savedMember = memberRepository.save(member);
        Long id = savedMember.getId();

        //when
        memberRepository.delete(id);

        //then
        assertThrows(NoSuchElementException.class, () -> memberRepository.findById(id).get());
    }

    Member getMember(){
        Member member = new Member();
        member.setLoginId("USER" + UUID.randomUUID());
        member.setPassword("PASSWORD" + UUID.randomUUID());
        return member;
    }
}