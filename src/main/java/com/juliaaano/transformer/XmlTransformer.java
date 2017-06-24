package com.juliaaano.transformer;

import spark.ResponseTransformer;

import static com.juliaaano.payload.MediaType.XML;

public class XmlTransformer implements ResponseTransformer {

    @Override
    public String render(Object model) throws Exception {

        return XML.payload().newInstance(model).raw();
    }
}
