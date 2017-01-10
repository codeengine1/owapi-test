package com.codeengine.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.util.concurrent.ExecutionException;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class OwapiTestHandler {
	public String testHandler(int myCount, Context context) throws ExecutionException, InterruptedException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		Response response = client.prepareGet("https://owapi.net/api/v3/u/Kampfkeks-2498/stats").execute().get();
		context.getLogger().log(response.getResponseBody());
		return response.getResponseBody();
	}
}
