package org.mulesoft.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mulesoft.internal.model.JiraComment;
import org.mulesoft.internal.model.JiraCommentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class JiraOperations {
	private final Logger LOGGER = LoggerFactory.getLogger(JiraOperations.class);

	@MediaType(value = ANY, strict = false)
	public String getTask( String taskId, @Connection JiraConnection connection) {
		RestTemplate client = connection.getClient();
		String url = connection.url(taskId);
		Map<String, Object> params = getStringObjectMap(taskId);
		LOGGER.info("Performing GET request for: {}", url);
		return client.getForEntity(url, String.class, params).getBody();
	}

	@MediaType(value = ANY, strict = false)
	public String addComment( String taskId, String msg, @Connection JiraConnection connection) {
		RestTemplate client = connection.getClient();
		Map<String, Object> params = getStringObjectMap(taskId);
		JiraComment comment = new JiraComment();
		comment.setBody(msg);
		String url = connection.url(taskId) + "/comment";
		LOGGER.info("Performing POST request for: {}", url);
		return client.postForObject(url, comment, String.class, params);
	}

	private Map<String, Object> getStringObjectMap(String taskId) {
		Map<String, Object> params = new HashMap<>();
		params.put("issueIdOrKey", taskId);
		return params;
	}
}
