package com.example.scoreboard.service;

import com.example.scoreboard.document.User;
import com.example.scoreboard.response.APIResponse;

public interface UserService {

    APIResponse insert(User User);

    boolean userExists(String id);
}
