package com.woopaca.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않다. 실무에서는 ConcurrentHashMap이나 AtomicLong을 고려
 */
public class MemberRepository {

    // static은 없어도 됨
    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {}

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 테스트에서 주로 사용
    public void clearStore() {
        store.clear();
    }
}
