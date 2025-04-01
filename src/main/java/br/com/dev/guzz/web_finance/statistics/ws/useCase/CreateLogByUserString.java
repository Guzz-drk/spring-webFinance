package br.com.dev.guzz.web_finance.statistics.ws.useCase;

import br.com.dev.guzz.web_finance.statistics.ws.entity.WsStatistics;
import br.com.dev.guzz.web_finance.statistics.ws.repository.WsStatisticsRepository;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLogByUserString {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WsStatisticsRepository wsStatisticsRepository;

    public void invoke(String userMail, String wsCalled) {
        User user = getUserByEmail(userMail);

        if (user == null) return;

        wsStatisticsRepository.save(
                WsStatistics
                        .builder()
                        .user(user)
                        .wsCalled(wsCalled)
                        .build()
        );
    }

    public User getUserByEmail(String mail) {
        return (User) userRepository.findByMail(mail);
    }
}
