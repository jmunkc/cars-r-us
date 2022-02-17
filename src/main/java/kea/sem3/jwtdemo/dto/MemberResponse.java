package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private List<String> roleNames;

    // Admins
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    LocalDateTime created;
    @UpdateTimestamp
    LocalDateTime edited;
    Boolean isApproved;
    Byte ranking;

    // Creation response
    public MemberResponse(String userName, List<Role> roleNames, LocalDateTime created) {
        this.userName = userName;
        this.roleNames = roleNames.stream().map(Enum::toString).collect(Collectors.toList());
        this.created = created;
    }

    // All details
    public MemberResponse(Member member, boolean isAdmin) {
        this.userName = member.getUsername();
        this.email = member.getEmail();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.street = member.getStreet();
        this.city = member.getCity();
        this.zip = member.getZip();
        if (isAdmin) {
            this.created = member.getCreated();
            this.edited = member.getEdited();
            this.isApproved = member.getIsApproved();
            this.ranking = member.getRanking();

        }
    }
//    public static List<MemberResponse> getMembersFromEntities(List<Member> members){
//        return members.stream().map(member -> new MemberResponse(member, false)).collect(Collectors.toList());
//    }
}
