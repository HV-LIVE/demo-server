package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.UserDTO;
import org.hvlive.server.demoserver.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/users")
public class UsersController {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
