package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    String firstName;
    String lastName;
    String street;
    String city;
    String zip;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime edited;

    boolean isApproved;
    byte ranking;

    public MemberResponse(Member member, boolean includeAll) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getCity();
        if (includeAll) {
            this.created = member.getCreated();
            this.edited = member.getEdited();
            this.isApproved = member.isApproved();
            this.ranking = member.getRanking();
        }
    }

    public static List<MemberResponse> getMembersFromEntities(List<Member> members){
        return members.stream().map(member -> new MemberResponse(member, false)).collect(Collectors.toList());
    }
}
