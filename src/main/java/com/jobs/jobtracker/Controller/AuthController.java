package com.jobs.jobtracker.Controller;
import com.jobs.jobtracker.Model.LoginRequest;
import com.jobs.jobtracker.Model.LoginResponseBody;
import com.jobs.jobtracker.Model.User;
import com.jobs.jobtracker.Model.UserSignupRequest;
import com.jobs.jobtracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository user;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserSignupRequest userSignupRequest) {
        if(user.findByUsername(userSignupRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        User newUser = new User();
        newUser.setUsername(userSignupRequest.getUsername());
        newUser.setPassword(encoder.encode(userSignupRequest.getPassword()));
        user.save(newUser);
        System.out.println(newUser);
        return ResponseEntity.accepted().body("New user created");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBody> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();

            LoginResponseBody responseBody = new LoginResponseBody();
            responseBody.setSuccess(true);
            responseBody.setMessage("User logged in successfully!");
            responseBody.setStatusCode(200);
            responseBody.setUsername(authenticatedUser.getUsername());

            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException ex) {
            LoginResponseBody responseBody = new LoginResponseBody();
            responseBody.setSuccess(false);
            responseBody.setMessage("Invalid username or password. User does not exist");
            responseBody.setStatusCode(404);
            responseBody.setUsername(null);
            return ResponseEntity.ok(responseBody);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(String request) {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.status(HttpStatus.OK).body("User logged out successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during logout.");
        }
    }
}
