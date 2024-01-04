package co.kr.api.restapisample.controller;


import co.kr.api.restapisample.Repository.PostRepository;
import co.kr.api.restapisample.Repository.UserRepository;
import co.kr.api.restapisample.bean.Post;
import co.kr.api.restapisample.bean.User;
import co.kr.api.restapisample.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UserJPAController {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJPAController(UserRepository userRepository,PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /*@GetMapping("/user")
    public List<User> jpaUserFindAll(){
        Map<Object, Object> test = new HashMap<Object, Object>();

        test.put("count",userRepository.count());
        test.put("users",userRepository.findAll());

        return userRepository.findAll();
    }*/

    @GetMapping("/user")
    public ResponseEntity<Map<Object,Object>> jpaUserFindAll(){
        Map<Object, Object> test = new HashMap<>();

        test.put("count",userRepository.count());
        test.put("users",userRepository.findAll());

        return ResponseEntity.ok(test);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity jpaUserFindById(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id" + id);

        }

        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).jpaUserFindAll());
        entityModel.add(linTo.withRel("all-users"));  // http://localhost:8088/users 링크 값 연동 해줌
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> creatUser(@Valid @RequestBody User user){
        User us = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(us.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> findPostAllByUser(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(90002);
        if(!user.isPresent()){
            throw new UserNotFoundException("id" + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable("id") int id, @RequestBody Post post){
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("id" + id);
        }

        User user = optionalUser.get();
        post.setUser(user);

        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

}
