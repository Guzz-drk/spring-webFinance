package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import br.com.dev.guzz.web_finance.user.util.BuilderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsers {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BuilderUser builderUser;

    public List<UserDTO> invoke(){
        List<User> users = repository.findAllByActive(true);

        return users.stream()
                .map(user -> this.builderUser.toDTO(user))
                .toList();
    }
}
