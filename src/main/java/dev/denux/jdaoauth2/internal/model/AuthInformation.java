package dev.denux.jdaoauth2.internal.model;

import com.google.gson.annotations.SerializedName;
import dev.denux.jdaoauth2.internal.Scope;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthInformation {

    @SerializedName("user.id")
    private long clientId;
    private String scopes;
    private String expires;

    /**
     * Gets you the id of the client.
     * @return the id as a long.
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Gets you the timestamp when your auth information expires.
     * @return the timestamp.
     */
    public String getExpires() {
        return expires;
    }

    /**
     * Gets you the expiring date as an {@link Instant} object.
     * @return the expiring date.
     */
    public Instant getExpiresAsInstant() {
        return Instant.parse(DateTimeFormatter.ISO_INSTANT.parse(expires).toString());
    }

    /**
     * Gets you the scopes seperated with a space.
     * @return the scopes.
     */
    public String getScopes() {
        return scopes;
    }

    /**
     * Gets you the scopes as a {@link List} of {@link Scope} objects.
     * @return the scopes.
     */
    public List<Scope> getScopesAsScopeList() {
        return Arrays.stream(scopes.split(" ")).map(Scope::valueOf).collect(Collectors.toList());
    }
}
