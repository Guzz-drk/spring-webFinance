package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import br.com.dev.guzz.web_finance.user.util.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InactivateUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ValidateUser validateUser;

    public void invoke(Long id) throws Exception {
        User user = validateUser.invoke(id);
        user.setActive(false);

        repository.saveAndFlush(user);
    }
}
