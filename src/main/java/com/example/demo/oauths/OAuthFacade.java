package com.example.demo.oauths;

import com.example.demo.application.dtos.out.oauth.OAuthOutput;
import com.example.demo.oauths.github.OAuthGitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthFacade {

    private final OAuthGitHubService gitHubService;

    public OAuthOutput getGitHubUser(String code) {
        return gitHubService.getUser(code);
    }

}