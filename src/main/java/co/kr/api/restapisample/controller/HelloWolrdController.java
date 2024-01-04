package co.kr.api.restapisample.controller;


import co.kr.api.restapisample.bean.HelloWorldBean;
import co.kr.api.restapisample.bean.User;
import co.kr.api.restapisample.dao.UserDaoService;
import co.kr.api.restapisample.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name="user-controller", description = "일반 사용자 서비스를 위한 컨트롤러")
//@Controller + @ResponseBody  합쳐져있는 형태
public class HelloWolrdController {

    private MessageSource msg;

    public  HelloWolrdController(MessageSource msg){
        this.msg = msg;
    }

    @Autowired
    private UserDaoService userDaoService;

    //GET
    //URI : /hello-wolrd
    @GetMapping("/hello-world")
    public String HelloWolrdMethod(){
        return "hello-world";
    }

    //Bean 설정 후 Bean Return 형태
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("HelloWorld Bean 생성");
    }

    @GetMapping("/hello-world-bean/path-val/{name}")
    public HelloWorldBean helloWorldBeanss(@PathVariable String name){
        return new HelloWorldBean("path-val :: >> " + name);
        //return new HelloWorldBean(String.format(path-val :: %s) + name);
    }

    /*Swagger 적용 예시*/
    @Operation(summary = "사용자 정보 조회 API",description = "사용자 정보 모두 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok!! ")
    })
    @GetMapping("/users")
    public List<User> findUserAll(){
        return userDaoService.findAll();
    }

    //save
    /* @PostMapping("/users")
    public void createUser(@Valid @RequestBody User user){
        User us = userDaoService.save(user);
    }
    */

    //save
    @PostMapping("/users")
    public ResponseEntity<User> createUserResposeEntitys(@Valid @RequestBody User user){
        User us = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(us.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*@GetMapping("/users/{id}")
    public User findOne(@PathVariable int id){
        User user =  userDaoService.findOne(id);
        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            //throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            return Objects.requireNonNull(user,"데이터가 없습니다.");
        }
        return user;
    }*/

    @GetMapping("/users/{id}")
    public EntityModel<User> findOne(@PathVariable int id){
        User user =  userDaoService.findOne(id);
        if(user == null){
            //@ControllerAdvice 사용하여 예외처리 방식.
            //throw new NullPointerException();
            //throw new UserNotFoundException(String.format("ID[%s] not found " , id)); //여기 exceptionClass 내용 대신    custom ResponseEntity 생성
            //Null 체크 후 null 일경우 msg 전달.
            //return Objects.requireNonNull(user,"데이터가 없습니다.");
        }

        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).findUserAll());
        entityModel.add(linTo.withRel("all-users"));  // http://localhost:8088/users 링크 값 연동 해주는 것 같음()
        return entityModel;
    }

    //정상 삭제 후 리턴값이 없을때   status : 204
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteOne(@PathVariable int id){
       User user= userDaoService.deleteById(id);
       if(user == null){
           throw  new UserNotFoundException(String.format("Id[%s] not found", id));
       }
       return ResponseEntity.noContent().build();
    }

    @GetMapping("/hello-world-international")
    public String helloInternational(
            @RequestHeader(name="Accept-Language",required = false)Locale locale){
        return msg.getMessage("greeting.message",null,locale);
    }

}
