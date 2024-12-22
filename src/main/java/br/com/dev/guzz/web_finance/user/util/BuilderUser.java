package br.com.dev.guzz.web_finance.user.util;

import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class BuilderUser {

    public User toEntity(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .mail(userDTO.getMail())
                .active(true)
                .password(userDTO.getPassword())
                .build();
    }

    public UserDTO toDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .mail(user.getMail())
                .build();
    }

}
