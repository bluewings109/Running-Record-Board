package org.onlypearson.runningrecord.domain.member.service;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public void edit(Long id, Member member) {
        memberRepository.update(id,member);
    }

    @Override
    public Member withdrawMember(Long id) {
        return memberRepository.delete(id);
    }

    @Override
    public void withdrawAll() {
        memberRepository.deleteAll();
    }
}
