package br.com.dev.guzz.web_finance.user.repository;

import br.com.dev.guzz.web_finance.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndActive(Long id, boolean active);

    List<User> findAllByActive(boolean active);

    UserDetails findByMail(String login);
}
