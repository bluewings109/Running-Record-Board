package org.onlypearson.runningrecord.domain.member.repository;

import org.onlypearson.runningrecord.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //create
    void save(Member member);

    //read
    Member findById(Long id);
    List<Member> findAll();
    Optional<Member> findByLoginId(String loginId);

    //update
    void update(Long id, Member member);

    //delete
    Member delete(Long id);
    default void deleteAll(){ }

}
