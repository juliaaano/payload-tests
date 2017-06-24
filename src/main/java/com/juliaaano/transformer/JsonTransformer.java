package com.juliaaano.transformer;

import spark.ResponseTransformer;

import static com.juliaaano.payload.MediaType.JSON;

public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(final Object model) {

        return JSON.payload().newInstance(model).raw();
    }
}
