package com.xeanco.xeanco.IService;

import com.xeanco.xeanco.model.AppUser;

import java.util.List;

public interface IUserService {
    AppUser saveUser(AppUser user);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
