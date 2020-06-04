package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.UserDTO;
import org.hvlive.server.demoserver.entity.User;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.exception.Errors;
import org.hvlive.server.demoserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return UserDTO.fromEntities(userRepository.findAll());
    }

    public Map<Long, UserDTO> getUsers(Set<Long> ids) {
        Map<Long, UserDTO> users = new HashMap<>();
        userRepository.findAllByIdIn(ids).forEach(user -> users.put(user.getId(), UserDTO.fromEntity(user)));
        return users;
    }

    public UserDTO getUser(Long id) {
        return userRepository.findById(id).map(UserDTO::fromEntity).orElse(null);
    }

    public UserDTO getUser(String account, String password) {
        return userRepository.findByAccountAndPassword(account, password)
                .map(UserDTO::fromEntity)
                .orElseThrow(() -> new BadRequestException(Errors.User.CODE_ACCOUNT_PASSWORD_WRONG, Errors.User.MESSAGE_ACCOUNT_PASSWORD_WRONG));
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByAccount(userDTO.getAccount())) {
            throw new BadRequestException(Errors.User.CODE_ACCOUNT_EXISTS, Errors.User.MESSAGE_ACCOUNT_EXISTS);
        }
        User user = User.builder()
                .account(userDTO.getAccount())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .streamName(userDTO.getAccount())
                .build();
        userRepository.saveAndFlush(user);
        return UserDTO.fromEntity(user);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        if (userRepository.existsByIdNotAndAccount(userDTO.getId(), userDTO.getAccount())) {
            throw new BadRequestException(Errors.User.CODE_ACCOUNT_EXISTS, Errors.User.MESSAGE_ACCOUNT_EXISTS);
        }
        User user = User.builder()
                .id(userDTO.getId())
                .account(userDTO.getAccount())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .streamName(userDTO.getAccount())
                .build();
        userRepository.saveAndFlush(user);
        return UserDTO.fromEntity(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
