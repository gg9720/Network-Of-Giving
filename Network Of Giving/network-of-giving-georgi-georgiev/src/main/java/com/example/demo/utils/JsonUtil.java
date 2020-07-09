package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode toJsonNode(String jsonString) {
        try {
            return MAPPER.readTree(jsonString);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T mapObject(String body, Class<T> tClass) {
        try {
            return MAPPER.readValue(body, tClass);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T mapObject(Reader body, Class<T> tClass) {
        try {
            return MAPPER.readValue(body, tClass);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> String toStringObject(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}