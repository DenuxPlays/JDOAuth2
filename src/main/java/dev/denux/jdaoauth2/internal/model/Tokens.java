package dev.denux.jdaoauth2.internal.model;

import com.google.gson.annotations.SerializedName;
import dev.denux.jdaoauth2.internal.Scope;

import javax.annotation.Nonnull;

public class Tokens {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    private String scope;

    /**
     * The access token you got from the server.
     * @return the token as a string.
     */
    public @Nonnull String getAccessToken() {
        return accessToken;
    }

    /**
     * The name of the type of the token you got.
     * @return the name as a string.
     */
    public @Nonnull String getTokenType() {
        return tokenType;
    }

    /**
     * Gets you the amount of seconds until the token expires.
     * @return the amount of seconds.
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     * The refresh token you got from the server.
     * @return the token as a string.
     */
    public @Nonnull String getRefreshToken() {
        return refreshToken;
    }

    /**
     * The name of the scope
     * @return the name as a string.
     */
    public @Nonnull String getScopeAsString() {
        return scope;
    }

    /**
     * The {@link Scope} you got from the server.
     * @return the {@link Scope} enum.
     */
    public @Nonnull Scope getScope() {
        return Scope.valueOf(scope);
    }
}
