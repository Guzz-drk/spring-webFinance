package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import br.com.dev.guzz.web_finance.user.util.BuilderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BuilderUser builderUser;

    public UserDTO invoke(UserDTO userDTO){
        User user = builderUser.toEntity(userDTO);
        user = repository.save(user);

        return builderUser.toDTO(user);
    }
}
