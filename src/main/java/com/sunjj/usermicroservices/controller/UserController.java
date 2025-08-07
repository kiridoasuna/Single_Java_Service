package com.sunjj.usermicroservices.controller;

import com.sunjj.usermicroservices.dto.UserDTO;
import com.sunjj.usermicroservices.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO queryUser = this.userService.getUserById(id);

        return ResponseEntity.ok().body(queryUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id) {

        UserDTO updateUser = this.userService.updateUser(id, userDTO);
        return ResponseEntity.ok().body(updateUser);
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = this.userService.createUser(userDTO);

        return ResponseEntity.ok().body(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
