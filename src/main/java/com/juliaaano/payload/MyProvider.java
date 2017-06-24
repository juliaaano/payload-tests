package com.juliaaano.payload;

import com.juliaaano.payload.json.JsonProviderFactory;
import com.juliaaano.payload.provider.Provider;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.util.Optional;

public class MyProvider implements JsonProviderFactory {

    @Override
    public Optional<Provider> newInstance() {

        return Optional.of(new Provider() {
            @Override
            public String serialize(Object object) {
                JSONSerializer serializer = new JSONSerializer();
                return serializer.serialize(object);
            }

            @Override
            public <T> T deserialize(String raw, Class<T> type) {

                return type.cast(new JSONDeserializer<>().deserialize(raw, type));
            }

            @Override
            public String toString() {
                return "hello";
            }
        });
    }
}
