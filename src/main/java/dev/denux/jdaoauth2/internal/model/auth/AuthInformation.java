package dev.denux.jdaoauth2.internal.model.auth;

import lombok.Getter;

@Getter
public class AuthInformation {

    private final Application application = new Application();
    private String[] scopes;
    private String expires;
    private final User user = new User();
}
