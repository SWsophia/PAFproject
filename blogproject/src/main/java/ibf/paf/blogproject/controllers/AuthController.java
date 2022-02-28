package ibf.paf.blogproject.controllers;

import ibf.paf.blogproject.dto.LoginRequest;
import ibf.paf.blogproject.dto.RegisterRequest;
import ibf.paf.blogproject.services.AuthService;
import ibf.paf.blogproject.services.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup (@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest loginRequest) {
//       return authService.login(loginRequest);
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);

    }
}
