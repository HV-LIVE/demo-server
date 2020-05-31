package org.hvlive.server.demoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hvlive.server.demoserver.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String account;
    private String password;
    private String name;
    private String phoneNumber;
    private String streamName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .streamName(user.getStreamName())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
    }

    public static List<UserDTO> fromEntities(List<User> users) {
        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
}
