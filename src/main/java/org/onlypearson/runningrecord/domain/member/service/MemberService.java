package org.onlypearson.runningrecord.domain.member.service;

import org.onlypearson.runningrecord.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    //create
    void join(Member member);

    //read
    Member getMemberById(Long id);
    Optional<Member> getMemberByLoginId(String loginId);
    List<Member> getMembers();

    //update
    void edit(Long id, Member member);

    //delete
    Member withdrawMember(Long id);
    default void withdrawAll(){}
}
