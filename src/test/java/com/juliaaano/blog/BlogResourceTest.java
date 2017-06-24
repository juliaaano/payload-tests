package com.juliaaano.blog;

import com.juliaaano.ResourceBuilder;
import com.juliaaano.ResourceTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.XML;
import static java.lang.String.format;
import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.*;

public class BlogResourceTest extends ResourceTest {

    private static final String randomUUID = randomUUID().toString();

    private static final String jsonBlogPost = format("{\"title\":\"%s\",\"extraJsonProperty\":\"Should ignore it.\"}", randomUUID);

    private static final String xmlBlogPost = format("<BlogPost><title>%s</title><extraXmlProperty>Should ignore it.</extraXmlProperty></BlogPost>", randomUUID);

    @Override
    protected ResourceBuilder resourceUnderTest() {
        return new BlogResource();
    }

    @Test
    public void should_return_json_when_post_json() {

        given()
            .accept(JSON)
            .contentType(JSON)
            .body(jsonBlogPost)
        .when()
            .post("/blog")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("identifier", not(nullValue()))
            .body("title", equalTo(randomUUID))
//            .body("tags", empty())
            .body("$", hasKey("author"))
//            .body("$", not(hasKey("extraObjectProperty")))
            .body("$", not(hasKey("extraJsonProperty")));
    }

    @Test
    public void should_return_xml_when_post_xml() {

        given()
            .accept(XML)
            .contentType(XML)
            .body(xmlBlogPost)
        .when()
            .post("/blog")
        .then()
            .statusCode(200)
            .contentType(XML)
            .body("blogPost.identifier", not(nullValue()))
            .body("blogPost.title", equalTo(randomUUID))
//            .body("blogPost.tags", empty())
            .body(not(hasXPath("/blogPost/extraObjectProperty")))
            .body(not(hasXPath("/blogPost/extraXmlProperty")));
    }
}
