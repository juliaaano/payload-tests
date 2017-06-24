package com.juliaaano;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

public abstract class ResourceTest {

    private static final int port = 8000;
    private static final String basePath = "/payload-tests";

    private RestContext context;

    @Before
    public void setup() {

        setupRestAssured();

        context = new RestContext(port, basePath);
        context.addResource(resourceUnderTest());
    }

    @After
    public void tearDown() {

        context.destroy();
    }

    protected abstract ResourceBuilder resourceUnderTest();

    private static void setupRestAssured() {

        RestAssured.port = port;
        RestAssured.basePath = basePath;
    }
}
