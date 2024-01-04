package co.kr.api.restapisample.controller;


import co.kr.api.restapisample.bean.AdminUser;
import co.kr.api.restapisample.bean.AdminUser2;
import co.kr.api.restapisample.bean.HelloWorldBean;
import co.kr.api.restapisample.bean.User;
import co.kr.api.restapisample.dao.UserDaoService;
import co.kr.api.restapisample.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
//@Controller + @ResponseBody  합쳐져있는 형태
public class AdminUserController {

    private MessageSource msg;

    public AdminUserController(MessageSource msg){
        this.msg = msg;
    }

    @Autowired
    private UserDaoService userDaoService;

    //GET
    //URI : /hello-wolrd
    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue adminUserFindOne(@PathVariable("id") int id){

        User user = userDaoService.findOne(id);
        AdminUser adminUser = new AdminUser();

        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            //return Objects.requireNonNull(user,"데이터가 없습니다.");
        }else{
            BeanUtils.copyProperties(user,adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filterProvider);
        return mapping;

    }

    @GetMapping(value = "/users/{id}", params = "version=1")
    public MappingJacksonValue adminUserFindOneParam(@PathVariable("id") int id){

        User user = userDaoService.findOne(id);
        AdminUser adminUser = new AdminUser();

        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            //return Objects.requireNonNull(user,"데이터가 없습니다.");
        }else{
            BeanUtils.copyProperties(user,adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filterProvider);
        return mapping;

    }

    @GetMapping(value = "/header/users/{id}",  headers = "X-API-VERSION=1")
    public MappingJacksonValue adminUserFindOneHeader(@PathVariable("id") int id){

        User user = userDaoService.findOne(id);
        AdminUser adminUser = new AdminUser();

        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            //return Objects.requireNonNull(user,"데이터가 없습니다.");
        }else{
            BeanUtils.copyProperties(user,adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filterProvider);
        return mapping;

    }

    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    public MappingJacksonValue adminUserFindOneParam2(@PathVariable("id") int id){

        User user = userDaoService.findOne(id);
        AdminUser adminUser = new AdminUser();

        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            //return Objects.requireNonNull(user,"데이터가 없습니다.");
        }else{
            BeanUtils.copyProperties(user,adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filterProvider);
        return mapping;

    }

    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue adminUserFindOne2(@PathVariable("id") int id){

        User user = userDaoService.findOne(id);
        AdminUser2 adminUser = new AdminUser2();

        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            //return Objects.requireNonNull(user,"데이터가 없습니다.");
        }else{
            BeanUtils.copyProperties(user,adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo2",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filterProvider);
        return mapping;

    }

    @GetMapping("/users")
    public MappingJacksonValue adminUserfindAll(){

        List<User> users = userDaoService.findAll();
        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;

        for(User user : users){
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user,adminUser);

            adminUsers.add(adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filterProvider);
        return mapping;

    }


}
