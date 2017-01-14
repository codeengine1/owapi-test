package com.codeengine.lambda;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Test;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class OwapiTestHandlerTest {
	@Test
	public void testTestHandler() throws Exception {
		new OwapiTestHandler().testHandler(new Context() {
			public String getAwsRequestId() {
				return null;
			}

			public String getLogGroupName() {
				return null;
			}

			public String getLogStreamName() {
				return null;
			}

			public String getFunctionName() {
				return null;
			}

			public String getFunctionVersion() {
				return null;
			}

			public String getInvokedFunctionArn() {
				return null;
			}

			public CognitoIdentity getIdentity() {
				return null;
			}

			public ClientContext getClientContext() {
				return null;
			}

			public int getRemainingTimeInMillis() {
				return 0;
			}

			public int getMemoryLimitInMB() {
				return 0;
			}

			public LambdaLogger getLogger() {
				return new LambdaLogger() {
					public void log(String string) {
						System.out.println(string);
					}
				};
			}
		});
	}
}
