import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class OwapiTest {
	@Test
	public void testSslCommunication() throws ExecutionException, InterruptedException {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		Response response = client.prepareGet("https://owapi.net/api/v3/u/Kampfkeks-2498/stats").execute().get();
		System.out.println(response.getResponseBody());
	}
}
