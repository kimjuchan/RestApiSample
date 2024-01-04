package co.kr.api.restapisample.Repository;

import co.kr.api.restapisample.bean.Post;
import co.kr.api.restapisample.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
