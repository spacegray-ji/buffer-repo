package iot.iotspring.repository;


import iot.iotspring.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.*;

@Primary
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
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
    public Optional<Member> findByName(String name) {
        // 이름이 같은 회원을 찾아서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findFirst();
    }
    public void cleanStore() {
        store.clear();
    }
}