package com.salaryexplorer.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.salaryexplorer.api.response.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class AuthorizationService {
    @Value("${google.client_id}")
    private String googleClientId; // Reemplaza con tu client ID de Google

    public GoogleIdTokenVerifier googleIdTokenVerifier;

    public AuthorizationResponse loginGoogle(String token) {

        if (googleIdTokenVerifier == null) {
            NetHttpTransport transport = new NetHttpTransport();
            JsonFactory jsonFactory = new GsonFactory();

            googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();
        }

        GoogleIdToken idToken = null;

        try {
            idToken = googleIdTokenVerifier.verify(token);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        if (idToken == null) {
            throw new RuntimeException("Invalid ID token.");
        }


        GoogleIdToken.Payload payload = idToken.getPayload();


        // Get profile information from payload
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String pictureUrl = (String) payload.get("picture");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        String fullname = givenName + ' ' + familyName;
        AuthorizationResponse response = AuthorizationResponse.builder().
                email(payload.getEmail())
                .name(fullname).build();

        return response;
    }

}
