package com.dorsetsoftware.store.user;

public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getUsername()
        );
    }
}
