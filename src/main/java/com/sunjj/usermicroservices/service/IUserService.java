package com.sunjj.usermicroservices.service;

import com.sunjj.usermicroservices.dto.UserDTO;

public interface IUserService {

    UserDTO getUserById(Integer id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Integer id, UserDTO userDTO);

    void deleteUser(int id);

}
