package br.com.dev.guzz.web_finance.user.util;

import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidateUser {

    @Autowired
    private UserRepository repository;

    public User invoke(Long id) throws Exception {
        Optional<User> user = getUserById(id);

        if(user.isPresent())
            return user.get();

        throw new Exception("User Not Found");
    }

    public Optional<User> getUserById(Long id){
        return repository.findByIdAndActive(id, true);
    }
}
