package dev.denux.jdaoauth2;

public class Constants {
    private Constants(){
    }

    public static final String BASE_URL ="https://discord.com/api";
    public static final String oAUTH_URL = BASE_URL + "/oauth2";
    public static final String AUTH_URL = oAUTH_URL + "/authorize";
    public static final String TOKEN_URL = oAUTH_URL + "/token";
    public static final String ME_URL = oAUTH_URL + "/@me";
}
