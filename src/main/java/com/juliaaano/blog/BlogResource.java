package com.juliaaano.blog;

import com.juliaaano.ResourceBuilder;
import com.juliaaano.payload.MediaType;
import com.juliaaano.payload.Payload;
import com.juliaaano.transformer.JsonTransformer;
import com.juliaaano.transformer.XmlTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Service;

import static com.juliaaano.payload.MediaType.JSON;
import static java.util.UUID.randomUUID;

public class BlogResource implements ResourceBuilder {

    private static final Logger logger = LoggerFactory.getLogger(BlogResource.class);

    @Override
    public void configure(final Service spark, final String basePath) {

        addAcceptTypeJson(spark, basePath);
        addAcceptTypeXml(spark, basePath);

        spark.after(basePath + "/blog", (request, response) -> {
            logger.info("Response body: {}", response.body());
        });
    }

    private void addAcceptTypeJson(final Service spark, final String basePath) {

        spark.post(basePath + "/blog", "application/json", (request, response) -> {

            final BlogPost blogPost = enrichBlogPost(request);
            response.type("application/json");
            return blogPost;

        }, new JsonTransformer());
    }

    private void addAcceptTypeXml(final Service spark, final String basePath) {

        spark.post(basePath + "/blog", "application/xml", (request, response) -> {

            BlogPost blogPost = enrichBlogPost(request);
            response.type("application/xml");
            return blogPost;

        }, new XmlTransformer());
    }

    private BlogPost enrichBlogPost(final Request request) {

        logger.info("Request body: {}", request.body());

        final Payload<BlogPost> payload =
                MediaType.of(request.contentType()).payload().newInstance(request.body(), BlogPost.class);

        JSON.payload().newInstance(new BlogPost());

        logger.info("New payload: {}", payload);

        payload.get().setIdentifier(randomUUID().toString());

        return payload.get();
    }
}
