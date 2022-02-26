package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    MemberService memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void getMembers() {
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(
                new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000"),
                new Member("b", "b@mail.dk", "bbb", "bf", "bl", "bvej", "bby", "2000"),
                new Member("c", "c@mail.dk", "ccc", "cf", "cl", "cvej", "cby", "2000")
        ));
        List<MemberResponse> members = memberService.getMembers();
        assertEquals(3, members.size());
    }

    @Test
    void getMember() {
        Member member = new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000");
        member.setUsername("a1000");
        Mockito.when(memberRepository.findById("a1000")).thenReturn(Optional.of(member));
        MemberResponse memRes = memberService.getMember("a1000");
        assertEquals("a@mail.dk", memRes.getEmail());
    }

    @Test
    void addMember() {
        Member memberWithId = new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000");
        memberWithId.setUsername("a1000");
        Mockito.when(memberRepository.save(any(Member.class))).thenReturn(memberWithId);
        MemberResponse res = memberService.addMember(new MemberRequest(memberWithId.getUsername(), memberWithId.getPassword(),
                memberWithId.getEmail(), memberWithId.getFirstName(), memberWithId.getLastName(), memberWithId.getStreet(),
                memberWithId.getCity(), memberWithId.getZip()));
        assertEquals("a1000", res.getUserName());
    }

    @Test
    void editMember() {
    }

    @Test
    void deleteMember() {
    }
}