package co.kr.api.restapisample.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 상세 정보 위한 도메인 객체")
@Entity
@Table(name= "user")
/*@JsonIgnoreProperties(value = {"password","ssn"})*/
public class User{
    @Schema(title = "사용자 ID", description = "사용자 이름 입력하시오.")
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;

    @Past(message = "미래일자는 등록하실 수 없습니다.")
    private Date joinDate;

    //보안 데이터 가림 역할.
    @JsonIgnore
    private String pasword;

    @JsonIgnore
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, @Size(min = 2, message = "name - 2글자 이상 입력해주세요.") String name, @Past Date joinDate, String pasword, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.pasword = pasword;
        this.ssn = ssn;
    }
}
