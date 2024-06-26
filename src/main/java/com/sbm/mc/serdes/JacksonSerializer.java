package com.sbm.mc.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class JacksonSerializer<T> implements Serializer<T> {

    private ObjectMapper objectMapper;
    private JacksonSerializerConfig config;

    public JacksonSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    public static Map<String, String> nonDefaultSettings(ObjectMapper objectMapper) {
        return JacksonSerializerConfig.nonDefaultSettings(objectMapper);
    }

    @Override
    public void configure(Map<String, ?> settings, boolean isKey) {
        this.config = new JacksonSerializerConfig(settings);
        this.config.configure(this.objectMapper);
    }

    @Override
    public byte[] serialize(String topic, T message) {
        if (null == message) {
            return null;
        }
        try {
            return this.objectMapper.writeValueAsBytes(message);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {}
}
