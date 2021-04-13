package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;

    /*
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String msg) {
        this.message = msg;
    }
    public HelloWorldBean(String message) {
        this.message = message;
    }
    */
}
