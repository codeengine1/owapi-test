package com.codeengine.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.asynchttpclient.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class OwapiTestHandler {
	static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("javax.net.debug", "ssl,handshake");
	}

	public String testHandler(Context context) throws Exception {

	Security.addProvider(new BouncyCastleProvider());

	// get cert
	X509Certificate rootCert =
			(X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
					new BufferedInputStream(OwapiTestHandler.class.getResourceAsStream("/comodo-ecc-ca.crt"))
			);
	X509Certificate domainCert =
			(X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
					new BufferedInputStream(OwapiTestHandler.class.getResourceAsStream("/comodo-ecc-domain-ca.crt"))
			);
	X509Certificate sniCert =
			(X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
					new BufferedInputStream(OwapiTestHandler.class.getResourceAsStream("/sni235897.cloudflaressl.com.crt"))
			);

	// add cert to keystore
	KeyStore keystore = KeyStore.getInstance("PKCS12", BouncyCastleProvider.PROVIDER_NAME);
	keystore.load(null, "".toCharArray());
	keystore.setCertificateEntry("comodo-ecc-ca", rootCert);
	keystore.setCertificateEntry("comodo-ecc-doman-ca", domainCert);
	keystore.setCertificateEntry("cloudflair-sni", sniCert);

		KeyManagerFactory keyManagerFactory =
				KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keystore, "".toCharArray());

		TrustManagerFactory trustManagerFactory =
				TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keystore);

		SslContext sslContext = SslContextBuilder.forClient()
				.keyManager(keyManagerFactory)
				.trustManager(trustManagerFactory)
				.build();


		AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setSslContext(sslContext).build();
		AsyncHttpClient client = new DefaultAsyncHttpClient(config);

		Response response = client.prepareGet("https://owapi.net/api/v3/u/Kampfkeks-2498/stats").execute().get();
		context.getLogger().log(response.getResponseBody());
		return response.getResponseBody();
	}
}
