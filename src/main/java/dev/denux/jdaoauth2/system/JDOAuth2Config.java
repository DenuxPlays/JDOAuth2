package dev.denux.jdaoauth2.system;

import dev.denux.jdaoauth2.internal.Scope;
import okhttp3.OkHttpClient;

import java.util.Set;

/**
 * Data class which contains the configuration for the JDOAuth2 library.
 */
public class JDOAuth2Config {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private Set<Scope> scopes;
    private OkHttpClient httpClient = new OkHttpClient();

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Set<Scope> scopes) {
        this.scopes = scopes;
    }
}
