package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    MemberRespository memberRespository;

    public MemberService(MemberRespository memberRespository) {
        this.memberRespository = memberRespository;
    }

    public List<MemberResponse> getMembers(){
        List<Member> members = memberRespository.findAll();
        return MemberResponse.getMembersFromEntities(members);
    }

    public MemberResponse getMember(String userName, boolean all) throws Exception{
        Member member = memberRespository.findById(userName).orElseThrow(()-> new Client4xxException("No member with this user name exists"));
        return  new MemberResponse(member, false);
    }

    public MemberResponse addMember(MemberRequest body){
        Member memberNew = memberRespository.save(new Member(body));
        return new MemberResponse(memberNew, true);
    }

    public MemberResponse editMember(MemberRequest body, String userName){
        Member memberToEdit = new Member(body);
        memberToEdit.setUsername(userName);
        memberRespository.save(memberToEdit);
        return new MemberResponse(memberToEdit, true);
    }

    public void deleteMember(String userName){
        memberRespository.deleteById(userName);
    }
}
