package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    //JPA 표준으로 제공되는 어노테이션
    //스프링이 EntityManager 를 만들어 이 어노테이션이 붙은 객체에 주입해준다.
    @PersistenceContext
    private EntityManager em;


    /**회원 저장
     * Save.
     *
     * @param member the member
     */
    public void save(Member member){
        em.persist(member);
    }


    /**아이디를 가지고 회원을 찾아 반환
     * Find one member.
     *
     * @param id the id
     * @return the member
     */
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    /**회원 목록을 LIST형태로 반환하는 메서드
     * Find all list.
     *
     * @return the list
     */
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }

    /**이름을 파라미터로 세팅해 이름을 목록으로 조회한다
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.username = :name",Member.class)
                    .setParameter("name",name)
                        .getResultList();
    }

}
