package org.onlypearson.runningrecord.domain.member.repository.memory;

import org.onlypearson.runningrecord.domain.member.Member;
import org.onlypearson.runningrecord.domain.member.MemberDto;
import org.onlypearson.runningrecord.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Long sequence=0L;
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(sequence, member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return store.values().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findAny();
    }

    @Override
    public void update(Long id, MemberDto memberDto) {
        Member updateMember = store.get(id);
        updateMember.setLoginId(memberDto.getLoginId());
        updateMember.setPassword(memberDto.getPassword());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    public void deleteAll() {
        store.clear();
    }
}
