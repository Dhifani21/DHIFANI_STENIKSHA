package com.OM.dao;

import com.OM.entity.Users;
import java.util.ArrayList;
import java.util.List;

public class UserProcessor {
    private List<Users> users = new ArrayList<>();

    public void createUser(Users user) {
        users.add(user);
    }

    public Users getUserById(int userId) {
        for (Users user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}