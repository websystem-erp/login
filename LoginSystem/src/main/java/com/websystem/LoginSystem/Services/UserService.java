package com.websystem.LoginSystem.Services;

import com.websystem.LoginSystem.Repository.UserDetails;
import com.websystem.LoginSystem.models.CollegeEmployee;
import com.websystem.LoginSystem.models.User;
import com.websystem.LoginSystem.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository; // Local user repository for database interactions

    @Autowired
    private PasswordEncoder passwordEncoder; // For password encryption

    @Autowired
    private ObjectMapper objectMapper; // For custom JSON processing

    static final String COLLEGE_SERVICE_URL = "http://localhost:8081/api/college/employees/findByEmail";

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public void register(User user) {
        userRepository.save(user); // Save the user to the database
    }

    public UserDetails login(String email, String password) throws IOException {
        // Check local database for user
        User user = userRepository.findByEmail(email);

        // Check if user exists and password matches
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // Return local user if found
        }

        // Check in external services
        UserDetails externalUser = checkExternalServices(email, password);
        System.out.println("OUT : "+externalUser);
        if (externalUser != null) {
            System.out.println(externalUser);
            return externalUser; // Return external user if found
        }

        // If neither local nor external user is found, throw an error
        throw new UsernameNotFoundException("User not found in system or external services");
    }

    private UserDetails checkExternalServices(String email, String password) throws IOException {
        try {
            ResponseEntity<CollegeEmployee> collegeResponse = restTemplate.getForEntity(COLLEGE_SERVICE_URL + "?email=" + email, CollegeEmployee.class);

            if (collegeResponse.getStatusCode() == HttpStatus.OK) {
                CollegeEmployee collegeEmployee = collegeResponse.getBody();

                // Check if the password is in BCrypt format
                if (collegeEmployee != null) {
                    String externalPassword = collegeEmployee.getPassword();
                    if (isBCrypt(externalPassword)) {
                        // Match if the password is hashed with BCrypt
                        if (passwordEncoder.matches(password, externalPassword)) {
                            return collegeEmployee; // Valid College Employee
                        }
                    } else {
                        // Handle the scenario where the password is not in BCrypt format
                        System.err.println("Warning: External password is not in BCrypt format for user: " + email);
                        // Optionally, you might want to add logic to handle this case
                    }
                }
            } else {
                System.err.println("Error fetching college employee: " + collegeResponse.getStatusCode());
            }
        } catch (RestClientException e) {
            System.err.println("Error during API call: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // If no match is found
    }

    private boolean isBCrypt(String password) {
        return password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$");
    }
}
