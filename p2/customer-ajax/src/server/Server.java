package server;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.Results;

public class Server extends Jooby {

	public Server() {

		assets("/**");
		assets("/", "index.html");
		get("/favicon.ico", () -> Results.noContent());

	}

	public static void main(String[] args) throws IOException {

		Server server = new Server();

		server.port(8081);

		CompletableFuture.runAsync(() -> {
			server.start();
		});

		server.onStarted(() -> {
			System.out.println("\nPress Enter to stop service.");
		});

		System.in.read();
		server.stop();

	}

}
