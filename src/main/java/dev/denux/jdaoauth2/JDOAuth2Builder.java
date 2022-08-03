package dev.denux.jdaoauth2;

import dev.denux.jdaoauth2.internal.Scope;
import dev.denux.jdaoauth2.system.JDOAuth2Config;
import okhttp3.OkHttpClient;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JDOAuth2Builder {

    private final JDOAuth2Config config;

    public JDOAuth2Builder() {
        config = new JDOAuth2Config();
    }

    /**
     * Sets the client id.
     * @param clientId the client id.
     * @return the builder. Good for chaining.
     */
    public @Nonnull JDOAuth2Builder setClientId(@Nonnull String clientId) {
        config.setClientId(clientId);
        return this;
    }

    /**
     * Sets the client secret.
     * @param clientSecret the client secret.
     * @return the builder. Good for chaining.
     */
    public @Nonnull JDOAuth2Builder setClientSecret(@Nonnull String clientSecret) {
        config.setClientSecret(clientSecret);
        return this;
    }

    /**
     * Sets the redirect uri.
     * @param redirectUri the redirect uri.
     * @return the builder. Good for chaining.
     */
    public @Nonnull JDOAuth2Builder setRedirectUri(@Nonnull String redirectUri) {
        config.setRedirectUri(redirectUri);
        return this;
    }

    /**
     * Sets the scopes.
     * @param scopes the scopes.
     * @return the builder. Good for chaining.
     */
    public @Nonnull JDOAuth2Builder setScopes(@Nonnull Scope... scopes) {
        config.setScopes(Arrays.stream(scopes).collect(Collectors.toSet()));
        return this;
    }

    /**
     * Sets a custom http client.
     * @param httpClient the http client.
     * @return the builder. Good for chaining.
     */
    public @Nonnull JDOAuth2Config setHttpClient(@Nonnull OkHttpClient httpClient) {
        config.setHttpClient(httpClient);
        return config;
    }

    /**
     * Checks everything and returns the finished {@link JDOAuth2} instance.
     * @return the finished {@link JDOAuth2} instance.
     */
    public @Nonnull JDOAuth2 build() {
        if (config.getClientId() == null) {
            throw new IllegalStateException("Client ID is not set");
        }
        if (config.getClientSecret() == null) {
            throw new IllegalStateException("Client Secret is not set");
        }
        if (config.getRedirectUri() == null) {
            throw new IllegalStateException("Redirect URI is not set");
        }
        if (config.getScopes() == null) {
            throw new IllegalStateException("Scopes are not set");
        }
        return new JDOAuth2(config);
    }
}
