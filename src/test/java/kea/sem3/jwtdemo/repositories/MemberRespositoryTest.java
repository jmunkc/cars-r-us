package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest  // Make SUREEEEE you understand ALL the things you get from this annotation
class MemberRespositoryTest {

    @Autowired
    MemberRepository memberRepository;

    /*
    Make sure you understand the difference between @BeforeEach and @BeforeAll and
    why @BeforeAll here ensures the SAME DATA for ALL tests
     */
    @BeforeAll
    static void setUp(@Autowired MemberRepository memberRepository) {
        memberRepository.save(new Member("aa","aa@a.dk","test12","Kurt","Wonnegut","a vej 12","Lyngby","2800"));
        memberRepository.save(new Member("bb","bb@a.dk","test12","Hanne","Wonnegut","b vej 12","Lyngby","2800"));
    }
    //These test does not really makes sense, since we are testing the repository made by Spring Data JPA, but initally to convince yourself that the
    //Magic works, do it anyway

    @Test
    public void testCount(){
      assertEquals(2, memberRepository.count());
    }

    @Test
    public void testFindById(){
        Member aa = memberRepository.findById("aa").orElse(null);  //Remember --> Returns an Optional
        assertEquals("aa@a.dk",aa.getEmail());
    }

    @Test
    public void testFindAll(){
        List<Member> all = memberRepository.findAll();
        assertEquals(2,all.size());
        assertThat(all,containsInAnyOrder(hasProperty("username",is("aa")),hasProperty("username",is("bb"))));
    }

    @Test
    public void testAddMember(){
        Member m = memberRepository.save(new Member("xxx","x@a.dk","xxxxx","yyy","zzzzzz","en vej","Lyngby","2800"));
        memberRepository.flush(); //Ensures that dates are written
        assertEquals("xxx",m.getUsername());
        assertEquals("2800",m.getZip());
        //Verify that timestamps were created
        assertNotNull(m.getCreated());
        assertNotNull(m.getEdited());
        assertEquals(3, memberRepository.count());
    }

    @Test
    public void testEditMember(){
        Member memberToEdit = memberRepository.findById("aa").orElse(null);
        memberToEdit.setEmail("axz@a.dk");
        memberRepository.save(memberToEdit);
        assertEquals(2, memberRepository.count());
        assertEquals("axz@a.dk", memberRepository.findById("aa").get().getEmail());
        //Verify that edited was updated
        assertTrue(memberToEdit.getEdited().isAfter(memberToEdit.getCreated()));
    }

    @Test
    public void testDeleteById(){
        memberRepository.deleteById("aa");
        assertEquals(1, memberRepository.count());
        assertNull(memberRepository.findById("aa").orElse(null));
    }

}