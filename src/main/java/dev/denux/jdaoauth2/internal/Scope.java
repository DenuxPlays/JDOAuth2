package dev.denux.jdaoauth2.internal;

public enum Scope {

    //activities
    ACTIVITIES_READ("activities.read"),
    ACTIVITIES_WRITE("activities.write"),

    //applications
    APPLICATIONS_BUILDS_READ("applications.builds.read"),
    APPLICATIONS_BUILDS_upload("applications.builds.upload"),
    APPLICATION_COMMANDS("applications.commands"),
    APPLICATIONS_COMMANDS_UPDATE("applications.commands.update"),
    APPLICATIONS_COMMANDS_PERMISSIONS_UPDATE("applications.commands.permissions.update"),
    APPLICATIONS_ENTITLEMENTS("applications.entitlements"),
    APPLICATIONS_STORE_UPDATE("applications.store.update"),

    BOT("bot"),
    CONNECTIONS("connections"),
    DM_CHANNELS_READ("dm_channels.read"),
    EMAIL("email"),
    GDM_JOIN("gdm.join"),

    //guilds
    GUILDS("guilds"),
    GUILDS_JOIN8("guilds.join"),
    GUILDS_MEMBERS_READ("guilds.members.read"),

    IDENTIFY("identify"),
    MESSAGES_READ("messages.read"),
    RELATIONSHIPS_READ("relationships.read"),

    //rpc
    RPC("rpc"),
    RPC_ACTIVITIES_WRITE("rpc.activities.write"),
    RPC_NOTIFICATIONS_READ("rpc.notifications.read"),
    RPC_VOICE_READ("rpc.voice.read"),

    VOICE("voice"),
    WEBHOOK_INCOMING("webhook.incoming");

    private final String scope;

    Scope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
