package org.onlypearson.runningrecord.domain.member.repository;

import org.onlypearson.runningrecord.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Long sequence=0L;
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        member.setId(++sequence);
        store.put(sequence, member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
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
    public void update(Long id, Member member) {
        Member updateMember = store.get(id);
        updateMember.setLoginId(member.getLoginId());
        updateMember.setPassword(member.getPassword());
    }

    @Override
    public Member delete(Long id) {
        Member deletedMember = store.get(id);
        store.remove(id);
        return deletedMember;
    }

    @Override
    public void deleteAll() {
        store.clear();
    }
}
