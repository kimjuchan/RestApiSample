package co.kr.api.restapisample.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Schema(title = "사용자 ID", description = "사용자 이름 입력하시오.")
    @Id
    @GeneratedValue
    private Integer id;


    private String testContents;
}
