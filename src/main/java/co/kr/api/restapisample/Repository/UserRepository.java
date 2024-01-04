package co.kr.api.restapisample.Repository;

import co.kr.api.restapisample.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {

}
