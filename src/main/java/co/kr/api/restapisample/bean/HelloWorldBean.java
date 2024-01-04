package co.kr.api.restapisample.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class HelloWorldBean {
    private final String msg;
/*
    public HelloWorldBean(String msg){
        this.msg = msg;
    }*/
}
