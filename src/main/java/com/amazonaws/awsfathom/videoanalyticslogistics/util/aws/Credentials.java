package com.amazonaws.awsfathom.videoanalyticslogistics.util.aws;

public class Credentials {
    private final String accessKey;
    private final String secretKey;
    private final String sessionToken;

    public Credentials(String accessKey, String secretKey, String sessionToken) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.sessionToken = sessionToken;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }
}
