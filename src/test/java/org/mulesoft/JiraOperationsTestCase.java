package org.mulesoft;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.springframework.util.Assert;

public class JiraOperationsTestCase extends MuleArtifactFunctionalTestCase {

	/**
	 * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
	 */
	@Override
	protected String getConfigFile() {
		return "test-mule-config.xml";
	}

	@Test
	public void executeGetTaskOperation() throws Exception {
		String payloadValue = ((String) flowRunner("getTaskFlow").run()
				.getMessage()
				.getPayload()
				.getValue());

		Assert.notNull(payloadValue);
	}

	@Test
	public void executeAddCommentOperation() throws Exception {
		String payloadValue = ((String) flowRunner("addCommentFlow").run()
				.getMessage()
				.getPayload()
				.getValue());
		Assert.notNull(payloadValue);
	}
}
