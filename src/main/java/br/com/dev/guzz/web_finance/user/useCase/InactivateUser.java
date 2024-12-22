package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import br.com.dev.guzz.web_finance.user.util.ValidateUser;
import br.com.dev.guzz.web_finance.util.RandomStringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InactivateUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ValidateUser validateUser;

    @Autowired
    private RandomStringValue random;

    public void invoke(Long id) throws Exception {
        User user = validateUser.invoke(id);

        anonymizeUser(user);
    }

    private void anonymizeUser(User user){
        user.setMail(random.maskLength10());
        user.setPassword("");
        user.setActive(false);

        repository.save(user);
    }
}
