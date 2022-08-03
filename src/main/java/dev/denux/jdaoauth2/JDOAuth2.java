package dev.denux.jdaoauth2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.denux.jdaoauth2.exceptions.HttpFailedResponseException;
import dev.denux.jdaoauth2.internal.GrantType;
import dev.denux.jdaoauth2.internal.Scope;
import dev.denux.jdaoauth2.internal.model.AuthInformation;
import dev.denux.jdaoauth2.internal.model.Tokens;
import dev.denux.jdaoauth2.system.JDOAuth2Config;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class JDOAuth2 {
    private static final Logger log = LoggerFactory.getLogger(JDOAuth2.class);

    private final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().create();
    private final JDOAuth2Config config;

    protected JDOAuth2(@Nonnull JDOAuth2Config config) {
        this.config = config;
    }

    /**
     * Get the URL to authorize the user.
     * @return the URL or null.
     */
    public @Nullable String getAuthorizationURL() {
        URIBuilder builder;
        try {
            builder = new URIBuilder(Constants.AUTH_URL);
        } catch (URISyntaxException exception) {
            log.error("Failed to create URIBuilder", exception);
            return null;
        }
        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", config.getClientId());
        builder.addParameter("redirect_uri", config.getRedirectUri());
        return builder + "&scope=" + config.getScopes().stream().map(Scope::getScope).collect(Collectors.joining("%20"));
    }

    /**
     * Exchanges the code for an access token.
     * @param code the code.
     * @return the access token wrapped in an {@link Tokens} object or null.
     * @throws HttpFailedResponseException if the response failed.
     */
    public @Nullable Tokens exchangeCode(@Nonnull String code) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", config.getClientId())
                .add("client_secret", config.getClientSecret())
                .add("grant_type", GrantType.AUTHORIZATION_CODE.getGrantType())
                .add("code", code)
                .add("redirect_uri", config.getRedirectUri())
                .add("scope", config.getScopes().stream().map(Scope::getScope).collect(Collectors.joining("%20")))
                .build();
        return performRequest(formBody);
    }

    /**
     * Refreshs the access token.
     * @param refreshToken the refresh token.
     * @return the new access token wrapped in an {@link Tokens} object or null.
     */
    public @Nullable Tokens refreshTokens(@Nonnull String refreshToken) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", config.getClientId())
                .add("client_secret", config.getClientSecret())
                .add("grant_type", GrantType.REFRESH_TOKEN.getGrantType())
                .add("refresh_token", refreshToken)
                .build();
        return performRequest(formBody);
    }

    private Tokens performRequest(@Nonnull RequestBody formBody) {
        Request request = new Request.Builder()
                .url(Constants.TOKEN_URL)
                .post(formBody)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try(Response response = config.getHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new HttpFailedResponseException("Failed to perform request.", response.code());
            }
            String json = response.body().string();
            response.close();
            return gson.fromJson(json, Tokens.class);
        } catch (IOException exception) {
            log.error("Failed to refresh token", exception);
            return null;
        }
    }

    /**
     * Get a Bot application object. <br>
     * Only works with bot accounts.
     */
    public AuthInformation getAuthInformation() {
        return gson.fromJson(getRequest("/@me"), AuthInformation.class);
    }

    private String getRequest(@Nonnull String path) {
        Request request = new Request.Builder()
                .url(Constants.oAUTH_URL + path)
                .build();
        try(Response response = config.getHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new HttpFailedResponseException("Failed to perform request.", response.code());
            }
            String json = response.body().string();
            response.close();
            return json;
        } catch (IOException exception) {
            log.error("Failed to refresh token", exception);
            return null;
        }
    }

    /**
     * Gets the {@link JDOAuth2Config} object.
     * @return the {@link JDOAuth2Config} instance.
     */
    public @Nonnull JDOAuth2Config getConfig() {
        return config;
    }
}