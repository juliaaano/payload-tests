package com.juliaaano;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import spark.Service;

import static spark.utils.StringUtils.isNotEmpty;

class RestContext {

    private static final Logger logger = LoggerFactory.getLogger(RestContext.class);

    private final Service spark;

    private final String basePath;

    RestContext(final int port, final String basePath) {

        this.basePath = basePath;

        spark = Service.ignite().port(port);
        spark.init();
        spark.awaitInitialization();
    }

    void addResource(final ResourceBuilder resource) {

        resource.configure(spark, basePath);
        logger.info("REST endpoints registered for the {}.", resource.getClass().getSimpleName());
    }

    void enableLogLevelPerRequest() {

        final String LOG_LEVEL_HEADER = "X-Log-Level";

        spark.before(basePath + "/*", (request, response) -> {

            final String logLevelHeader = request.headers(LOG_LEVEL_HEADER);
            if (isNotEmpty(logLevelHeader))
                MDC.put(LOG_LEVEL_HEADER, logLevelHeader);
        });

        spark.after(basePath + "/*", (request, response) -> MDC.remove(LOG_LEVEL_HEADER));

        logger.info("Log level per-request support enabled via '{}' header.", LOG_LEVEL_HEADER);
    }

    void destroy() {

        spark.stop();
    }
}
