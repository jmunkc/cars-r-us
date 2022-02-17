package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(){
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> new MemberResponse(member, false)).collect(Collectors.toList());
    }

    public MemberResponse getMember(String userName) {
        Member member = memberRepository.findById(userName).orElseThrow(()-> new Client4xxException("User not found", HttpStatus.NOT_FOUND));
        return  new MemberResponse(member, false);
    }

    public MemberResponse addMember(MemberRequest body){
        if(memberRepository.existsById(body.getUserName())){
            throw new Client4xxException(("Username already exists"));
        }
        if(memberRepository.emailExist(body.getEmail())){
            throw new Client4xxException("An account with that email already exists");
        }
        Member memberNew = new Member(body);
        memberNew.addRole(Role.USER);
        memberNew = memberRepository.save(memberNew);

        return new MemberResponse(memberNew.getUsername(), memberNew.getRoles(), memberNew.getCreated());
    }

    public MemberResponse editMember(MemberRequest body, String userName){
        if(!(memberRepository.existsById(userName))){
            throw new Client4xxException("No Such member exists");
        }
        Member memberToEdit = new Member(body);
        memberToEdit.setUsername(userName);
        memberRepository.save(memberToEdit);
        return new MemberResponse(memberToEdit, false);
    }

    public void deleteMember(String userName){
        if(!(memberRepository.existsById(userName))){
            throw new Client4xxException("No Such member exists");
        }
        memberRepository.deleteById(userName);
    }
}
