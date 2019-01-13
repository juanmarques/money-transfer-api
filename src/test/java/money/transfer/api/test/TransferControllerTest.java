package money.transfer.api.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import money.transfer.api.model.Transaction;

public class TransferControllerTest {

	private static EmbeddedServer server;
	private static HttpClient client;

	@BeforeClass
	public static void setupServer() {
		server = ApplicationContext.build()
				.packages("money.transfer.api.controller", "money.transfer.api.service", "money.transfer.api.model")
				.run(EmbeddedServer.class);
		client = server.getApplicationContext().createBean(HttpClient.class, server.getURL());

	}

	@AfterClass
	public static void stopServer() {
		if (server != null) {
			server.stop();
		}
		if (client != null) {
			client.stop();
		}
	}

	@Test
	public void transfer() {
		HttpRequest<?> request = HttpRequest.POST("/api/transfer",
				new Transaction(1, 2, 60, System.currentTimeMillis()));
		Optional<?> trans = request.getBody();
		assertNotNull(trans);
	}

	@After
	public void cleanup() throws IOException {
		server.stop();
		client.close();
	}
}