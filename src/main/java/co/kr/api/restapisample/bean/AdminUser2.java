package co.kr.api.restapisample.bean;


import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo2")
//어노테이션으로 여러 key 값 가림 처리 작업.
/*@JsonIgnoreProperties(value = {"password","ssn"})*/
public class AdminUser2 {

    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;

    @Past(message = "미래일자는 등록하실 수 없습니다.")
    private Date joinDate;

    //보안 데이터 가림 역할.
    private String pasword;

    private String grade;


}
