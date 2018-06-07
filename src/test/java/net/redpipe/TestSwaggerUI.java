package net.redpipe;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.rxjava.ext.web.client.WebClient;
import net.redpipe.engine.core.AppGlobals;
import net.redpipe.engine.core.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class TestSwaggerUI {

    private final static int PORT = 9998;
    private final static JsonObject CONF = new JsonObject().put("http_port", PORT);
    private Server server;
    private WebClient client;

    @Before
    public void setupServer(TestContext ctx) {
        Async async = ctx.async();
        server = new Server();
        server.start(CONF).subscribe(
            voidz -> async.complete(),
            ctx::fail
        );
        client = WebClient.create(AppGlobals.get().getVertx(), new WebClientOptions().setDefaultHost("localhost").setDefaultPort(PORT));
    }

    @After
    public void tearDown(TestContext ctx) {
        Async async = ctx.async();
        server.close().subscribe(
            voidz -> async.complete(),
            ctx::fail
        );
    }

    @Test
    public void accessSwaggerUI(TestContext ctx) {
        Async async = ctx.async();
        client.get("/swagger")
                .rxSend()
                .subscribe(resp -> {
                    ctx.assertEquals(200, resp.statusCode());
                    String html = resp.bodyAsString();
                    ctx.assertNotNull(html);
                    async.complete();
                });
    }


}
