package jpabook.jpashop;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void saveTest()throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member target = memberRepository.find(saveId);;

        //then
        assertThat(target.getId()).isEqualTo(member.getId());
        assertThat(target.getUsername()).isEqualTo(member.getUsername());
        assertThat(target).isEqualTo(member);
        //영속성 컨텍스트에 같은 경우 같은 값으로 적용된다.
    }

}