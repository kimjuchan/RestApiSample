package co.kr.api.restapisample.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    //지연 로딩   (필요한 시점에서만 가져옴)
    //persistence에서  정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

}
