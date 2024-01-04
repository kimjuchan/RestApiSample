package co.kr.api.restapisample.dao;

import co.kr.api.restapisample.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCnt =  3;
    //LocalDate  jdk 1.8부터 요거 사용
    //Date

    static {
            users.add(new User(1,"Kim1",new Date(),"test1","111-1111"));
            users.add(new User(2,"Kim2",new Date(),"test2","112-1111"));
            users.add(new User(3,"Kim3",new Date(),"test3","113-1111"));

    }
    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++userCnt);
        }

        if(user.getJoinDate() == null){
            user.setJoinDate(new Date());
        }
        users.add(user);

        return user;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }

        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){

            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }

        }
        return null;
    }

}
