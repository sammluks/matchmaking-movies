package org.matchmaking;

import org.matchmaking.persistence.Users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchUserDto {
    private UserDto user;
    private Double matchLevel;
    
    public MatchUserDto(Users user, Double value) {
        this.user = new UserDto(user);
        this.matchLevel = value;
    }

}
