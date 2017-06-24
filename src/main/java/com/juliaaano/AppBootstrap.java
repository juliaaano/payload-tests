package com.juliaaano;

import com.juliaaano.blog.BlogResource;
import org.slf4j.MDC;

import static com.juliaaano.AsciiBanner.asciiBanner;

public class AppBootstrap {

    public static void main(final String... args) {

        MDC.put("Correlation-Id", "correlation-id");
        asciiBanner("application-ascii-banner.txt").ifPresent(AsciiBanner::print);

        RestContext context = new RestContext(8000, "/payload-tests");
        context.enableLogLevelPerRequest();

        context.addResource(new BlogResource());
    }
}
