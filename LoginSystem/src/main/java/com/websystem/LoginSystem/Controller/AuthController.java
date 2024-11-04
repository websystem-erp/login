package com.websystem.LoginSystem.Controller;

import com.websystem.LoginSystem.Repository.UserDetails;
import com.websystem.LoginSystem.Services.UserService;
import com.websystem.LoginSystem.models.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Key;

import io.jsonwebtoken.security.Keys;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Generate a secure signing key for HS512
    private final Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam("name") String name,
            @RequestParam("campusType") String campusType,
            @RequestParam("boardType") String boardType,
            @RequestParam("role") String role,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("image") MultipartFile image,
            @RequestParam("adminimage") MultipartFile adminimage,
            @RequestParam("campusname") String campusname,
            @RequestParam("campuslocation") String campuslocation,
            @RequestParam("campusfoundyear") String campusfoundyear,
            @RequestParam("branchname") String branchname,
            @RequestParam("branchlocation") String branchlocation
            ){

        try {
            User user = new User();
            user.setName(name);
            user.setCampusType(CampusType.valueOf(campusType));
            user.setBoardType(BoardType.valueOf(boardType));
            user.setRole(role);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password)); // Encrypt the password

            // Convert the image to byte array and set it
            user.setImage(image.getBytes());
            user.setAdminimage(adminimage.getBytes());

            user.setCampusname(campusname);
            user.setCampuslocation(campuslocation);
            user.setCampusfoundyear(campusfoundyear);
            user.setBranchname(branchname);
            user.setBranchlocation(branchlocation);

            userService.register(user);
            return ResponseEntity.ok("User registered successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

            // Log the user details for debugging
            System.out.println("User Details: " + userDetails);

            if (userDetails instanceof User) {
                User user = (User) userDetails;
                String token = generateToken(user.getEmail());
                UserData userData = new UserData(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getCampusType(),
                        user.getRole(),
                        user.getCampusname(),
                        user.getCampuslocation(),
                        user.getCampusfoundyear(),
                        user.getBranchname(),
                        user.getBranchlocation(),
                        user.getImage(),
                        user.getAdminimage()
                );
                String message = user.getCampusType() == CampusType.COLLEGE ?
                        "Redirect to College Dashboard" : "Redirect to School Dashboard";

                return ResponseEntity.ok(new LoginResponse(message, token, userData));
            }
            // Attempt to login college employee
            if (userDetails instanceof CollegeEmployee) {
                CollegeEmployee user = (CollegeEmployee) userDetails;
                String token = generateToken(user.getEmail());
                UserData userData = new UserData(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getRole(),
                        user.getImage()
                );
                String message = "Redirect to College employee Dashboard" ;

                return ResponseEntity.ok(new LoginResponse(message, token, userData));
            }

        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid credentials", null));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid credentials", null));
    }

    private String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .signWith(jwtSecretKey) // Use the secure signing key
                .compact();
    }

}
