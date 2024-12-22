package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import br.com.dev.guzz.web_finance.user.util.BuilderUser;
import br.com.dev.guzz.web_finance.user.util.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ValidateUser validateUser;

    @Autowired
    private BuilderUser builderUser;

    public UserDTO invoke(Long id, UserDTO userDTO) throws Exception {
        User user = validateUser.invoke(id);

        user.setName(userDTO.getName());
        user.setMail(userDTO.getMail());

        repository.save(user);
        return builderUser.toDTO(user);
    }

}
