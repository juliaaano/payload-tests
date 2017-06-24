package com.juliaaano;

import spark.Service;

public interface ResourceBuilder {

    void configure(Service spark, String basePath);
}
