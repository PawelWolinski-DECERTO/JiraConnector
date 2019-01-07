package org.mulesoft.internal;


import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class JiraConnection {

	private final RestTemplate restTemplate;
	private final String url;
	private final String uid = UUID.randomUUID().toString();

	public JiraConnection(String url, String username, String password) {
		restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
		this.url = StringUtils.removeEnd(url.trim(), "/") + "/{issueIdOrKey}";
	}

	public void invalidate() {
		// do something to invalidate this connection!
	}

	public RestTemplate getClient() {
		return restTemplate;
	}

	public String url(String taskId) {
		return url;
	}

	public String getId() {
		return uid;
	}
}
