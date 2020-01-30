package com.example.scoreboard.response;

import lombok.Data;

@Data
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;


}
