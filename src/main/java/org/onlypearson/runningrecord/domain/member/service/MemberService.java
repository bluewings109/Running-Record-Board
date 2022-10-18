package org.onlypearson.runningrecord.domain.member.service;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    //create
    Member join(Member member);

    //read
    Optional<Member> getMemberById(Long id);
    Optional<Member> getMemberByLoginId(String loginId);
    List<Member> getMembers();

    //update
    void edit(Long id, MemberDto memberDto);

    //delete
    void withdrawMember(Long id);

}
