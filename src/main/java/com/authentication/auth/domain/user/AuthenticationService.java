package com.authentication.auth.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.auth.domain.ValidationException;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private userRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado:  " + email);
        }
        return user;
    }

    public User register(DataRegister data) {
        var existing = repository.findByEmail(data.email());
        if (existing != null) {
            throw new ValidationException("Email já cadastrado");
        }

        var user = new User(null, data.name(), data.lastname(), data.email(), passwordEncoder.encode(data.password()));
        return repository.save(user);
    }

}
