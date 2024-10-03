package com.amazonaws.awsfathom.videoanalyticslogistics.util.aws;

import java.util.Optional;

public class IdentityManager {
    private static final ThreadLocal<String> accountId = new ThreadLocal<>();
    private static final ThreadLocal<Credentials> credentials = new ThreadLocal<>();

    public void setAccountId(String id) {
        accountId.set(id);
    }

    public Optional<String> getAccountId() {
        return Optional.ofNullable(accountId.get());
    }

    public void setCredentials(Credentials creds) {
        credentials.set(creds);
    }

    public Optional<Credentials> getCredentials() {
        return Optional.ofNullable(credentials.get());
    }

    public void clearIdentity() {
        accountId.remove();
        credentials.remove();
    }
}

