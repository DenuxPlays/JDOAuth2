package dev.denux.jdaoauth2.internal.model.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class User {

	private long id;
	private String username;
	private String avatar;
	private String discriminator;
	@SerializedName("public_flags")
	private int publicFlags;
}
