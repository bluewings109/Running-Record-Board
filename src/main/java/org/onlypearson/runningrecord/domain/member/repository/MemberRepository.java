package org.onlypearson.runningrecord.domain.member.repository;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //create
    Member save(Member member);

    //read
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Optional<Member> findByLoginId(String loginId);

    //update
    void update(Long id, MemberDto memberDto);

    //delete
    void delete(Long id);

}
