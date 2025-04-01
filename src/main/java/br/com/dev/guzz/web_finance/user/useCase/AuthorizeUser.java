package br.com.dev.guzz.web_finance.user.useCase;

import br.com.dev.guzz.web_finance.infra.config.TokenService;
import br.com.dev.guzz.web_finance.user.dto.AuthorizeDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthorizeDTO invoke(AuthorizeDTO authorizeDTO) throws Exception {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authorizeDTO.getMail(), authorizeDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User userAuthenticated = (User) auth.getPrincipal();
        String token = tokenService.generateToken(userAuthenticated);

        authorizeDTO.setName(userAuthenticated.getName());
        authorizeDTO.setPassword(null);
        authorizeDTO.setToken(token);
        authorizeDTO.setAuthenticated(true);

        return authorizeDTO;
    }
}
