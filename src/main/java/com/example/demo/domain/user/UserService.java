package com.example.demo.domain.user;

import com.example.demo.application.dtos.out.oauth.OAuthOutput;
import com.example.demo.oauths.OAuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final OAuthFacade facade;

    public User auth(String key) {
        OAuthOutput data = facade.getGitHubUser(key);
        return repository.findById(data.id()).orElseGet(() -> {
            return repository.save(new User(
                    data.id(),
                    data.avatar(),
                    data.username(),
                    data.displayName(),
                    data.bio()
            ));
        });
    }

}