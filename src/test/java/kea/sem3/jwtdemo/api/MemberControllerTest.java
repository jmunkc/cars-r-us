package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    static String mem1Id, mem2Id;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        mem1Id = memberRepository.save(new Member("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000")).getUsername();
        mem2Id = memberRepository.save(new Member("b", "b@mail.dk", "bbb", "bf", "bl", "bvej", "bby", "2000")).getUsername();
    }

    @Test
    void getMembers() {
    }

    @Test
    void getMember() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/members/" + mem1Id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").exists())
                .andExpect((MockMvcResultMatchers.jsonPath("$.userName").value("a")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("a@mail.dk"));
    }

//    @Test
//    void addMember() throws Exception {
//        MemberRequest newMem = new MemberRequest("a", "a@mail.dk", "aaa", "af", "al", "avej", "aby", "2000");
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/members")
//                .contentType("application/json")
//                .accept("application/json")
//                .content(objectMapper.writeValueAsString(newMem)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").exists());
//        assertEquals(6, memberRepository.count());
//    }

    @Test
    void editMember() {
    }

    @Test
    void deleteMember() {
    }
}