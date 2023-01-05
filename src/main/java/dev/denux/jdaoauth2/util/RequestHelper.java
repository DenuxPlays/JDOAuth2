package dev.denux.jdaoauth2.util;

import dev.denux.jdaoauth2.exceptions.HttpFailedResponseException;
import dev.denux.jdaoauth2.system.JDOAuth2Config;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.Nonnull;
import java.io.IOException;

@Slf4j
public class RequestHelper {

	private RequestHelper() {}

	public static String bearerAuthentication(@Nonnull String token, @Nonnull String url, @Nonnull JDOAuth2Config config) {
		Request request = new Request.Builder()
				.url(url)
				.addHeader("authorization", String.format("Bearer %s", token))
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.build();
		return performRequest(request, config);
	}

	public static String performRequest(@Nonnull Request request, @Nonnull JDOAuth2Config config) {
		try(Response response = config.getHttpClient().newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new HttpFailedResponseException("Failed to perform request.", response.code());
			}
			if (response.body() == null) {
				throw new NullPointerException("Response body is null");
			}
			return response.body().string();
		} catch (IOException exception) {
			log.error("An IO problem occurred.", exception);
		}
		return null;
	}
}
