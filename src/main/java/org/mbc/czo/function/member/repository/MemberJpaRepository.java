package org.mbc.czo.function.member.repository;

import org.mbc.czo.function.member.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, String> {

    // id가 들어가면 해당 롤이 나옴
    @EntityGraph(attributePaths = "m_roleSet") // 연관된 롤을 가져옴
    @Query("select m from Member m where m.m_id = :member_id and m.m_isSocialActivate=false")
    Optional<Member> getWithRoles(@Param("member_id") String mid);

    // name phoneNumber로 id 있는지 체크
    @EntityGraph(attributePaths = "m_roleSet") // 연관된 롤을 가져옴
    @Query("select m from Member m where m.m_name = :member_name and m.m_phoneNumber = :member_phoneNumber")
    Optional<Member> checkExistIDFromNameAndPhoneNumber(@Param("member_name") String m_name, @Param("member_phoneNumber") String m_phoneNumber);


}
