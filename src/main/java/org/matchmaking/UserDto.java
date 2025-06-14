package org.matchmaking;

import org.matchmaking.persistence.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    public UserDto(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.id = user.id;
    }
}
