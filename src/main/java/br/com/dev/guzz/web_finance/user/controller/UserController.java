package br.com.dev.guzz.web_finance.user.controller;

import br.com.dev.guzz.web_finance.user.dto.AuthorizeDTO;
import br.com.dev.guzz.web_finance.user.dto.UserDTO;
import br.com.dev.guzz.web_finance.user.entity.User;
import br.com.dev.guzz.web_finance.user.useCase.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private CreateUser createUser;

    @Autowired
    private AnonymizeUser anonymizeUser;

    @Autowired
    private GetUser getUser;

    @Autowired
    private GetAllUsers getAllUsers;

    @Autowired
    private UpdateUser updateUser;

    @Autowired
    private InactivateUser inactivateUser;

    @Autowired
    private AuthorizeUser authorizeUser;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO){
        try {
            UserDTO user = createUser.invoke(userDTO);
            return ResponseEntity.status(201).body(user);
        } catch (Exception e){
            log.warn("=== Error while creating user. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/anonymize")
    public ResponseEntity<?> anonymize(@RequestHeader Long id){
        try {
            anonymizeUser.invoke(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e){
            log.warn("=== Error while anonymize user. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") Long id){
        try {
            User user = getUser.invoke(id);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e){
            log.warn("=== Error while finding user. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        try {
            List<UserDTO> users = getAllUsers.invoke();
            return ResponseEntity.status(200).body(users);
        } catch (Exception e){
            log.warn("=== Error while finding all users. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO){
        try {
            UserDTO user = updateUser.invoke(userDTO);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e){
            log.warn("=== Error while updating user. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/inactivate")
    public ResponseEntity<?> inactivate(@RequestHeader Long id){
        try {
            inactivateUser.invoke(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e){
            log.warn("=== Error while inactivating user. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(@RequestBody AuthorizeDTO authorizeDTO){
        try {
            AuthorizeDTO authorized = authorizeUser.invoke(authorizeDTO);
            return ResponseEntity.status(200).body(authorized);
        } catch (Exception e){
            log.warn("=== Error while authorizing user. {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
