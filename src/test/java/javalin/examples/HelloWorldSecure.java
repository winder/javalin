package javalin.examples;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import javalin.Javalin;
import javalin.embeddedserver.EmbeddedServer;
import javalin.embeddedserver.jetty.EmbeddedJettyFactory;

public class HelloWorldSecure {

    public static void main(String[] args) {
        Javalin.create()
            .embeddedServer(new EmbeddedJettyFactory(() -> {
                Server server = new Server();
                ServerConnector sslConnector = new ServerConnector(server, getSslContextFactory());
                sslConnector.setPort(443);
                ServerConnector connector = new ServerConnector(server);
                connector.setPort(80);
                server.setConnectors(new Connector[] {sslConnector, connector});
                return server;
            }))
            .get("/", (req, res) -> res.body("Hello World")); // valid endpoint for both connectors
    }

    private static SslContextFactory getSslContextFactory() {
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(EmbeddedServer.class.getResource("/keystore.jks").toExternalForm());
        sslContextFactory.setKeyStorePassword("password");
        return sslContextFactory;
    }

}
