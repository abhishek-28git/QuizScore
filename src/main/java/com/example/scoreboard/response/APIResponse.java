package com.example.scoreboard.response;

import lombok.Data;

@Data
public class APIResponse<T> {
    private boolean status;
    private String message;
    private T data;


}
