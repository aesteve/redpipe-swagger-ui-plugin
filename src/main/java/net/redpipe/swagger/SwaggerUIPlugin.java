package net.redpipe.swagger;

import net.redpipe.engine.spi.Plugin;
import net.redpipe.swagger.controller.SwaggerUIController;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;
import rx.Single;

public class SwaggerUIPlugin extends Plugin {

    @Override
    public Single<Void> deployToResteasy(VertxResteasyDeployment deployment) {
        deployment.getRegistry().addPerInstanceResource(SwaggerUIController.class);
        return Single.just(null);
    }


}
