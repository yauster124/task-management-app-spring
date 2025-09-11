package com.dorsetsoftware.store.user;

public class UserDto {
    private Integer id;
    private String username;

    public UserDto(){}

    public UserDto(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }
}
