package dev.denux.jdaoauth2.internal.model.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Application {

	private long id;
	private String name;
	private String icon;
	private String description;
	private boolean hook;
	@SerializedName("bot_public")
	private boolean botPublic;
	@SerializedName("bot_require_code_grant")
	private boolean botRequireCodeGrant;
	@SerializedName("verify_key")
	private String verifyKey;
}
