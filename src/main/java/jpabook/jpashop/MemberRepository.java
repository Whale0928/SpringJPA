package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    //Dao와 비슷 엔티티를 찾아준다.

    //엔티티 매니저를 설정해준다.
    @PersistenceContext
    private EntityManager em;

    /**회원 저장
     * Save long.
     *저장을 하면 리턴값을 제어한다.
     * @param member the member
     * @return the long
     */
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }


    /**회원찾기
     * Find member.
     *
     * @param id the id
     * @return the member
     */
    public Member find(Long id){
        return em.find(Member.class,id);
    }

}
