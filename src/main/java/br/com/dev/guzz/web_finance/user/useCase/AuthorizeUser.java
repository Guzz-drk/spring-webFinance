package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.user.dto.AuthorizeDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizeUser {

    @Autowired
    private UserRepository repository;

    public AuthorizeDTO invoke(AuthorizeDTO authorizeDTO) throws Exception {
        Optional<User> optionalUser = repository.findByMail(authorizeDTO.getMail());

        if(optionalUser.isEmpty()) throw new Exception("Mail or Password incorrect");

        User user = optionalUser.get();

        if(!user.getPassword().equals(authorizeDTO.getPassword())) throw new Exception("Mail or Password incorrect");

        authorizeDTO.setAuthenticated(true);

        return authorizeDTO;
    }
}
