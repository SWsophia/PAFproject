package ibf.paf.blogproject.controllers;

import ibf.paf.blogproject.dto.LoginRequest;
import ibf.paf.blogproject.dto.RegisterRequest;
import ibf.paf.blogproject.mail.MailService;
import ibf.paf.blogproject.model.User;
import ibf.paf.blogproject.repositories.UserRepository;
import ibf.paf.blogproject.services.AuthService;
import ibf.paf.blogproject.services.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @PostMapping("/signup")
    public ResponseEntity signup (@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest loginRequest) {
//       return authService.login(loginRequest);
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse login = authService.login(loginRequest);
        this.authService.getCurrentUser().ifPresent(auth -> {
            this.userRepository.findByUserName(auth.getUsername()).ifPresent(user -> {
                try {
                    this.mailService.resetPasswordEmail(user.getEmail());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        });
        return login;
    }
}
