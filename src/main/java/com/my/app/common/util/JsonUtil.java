package com.my.app.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

	public final static ObjectMapper JSON_MAPPER = new ObjectMapper();

	public ObjectMapper build() {
		JSON_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
		JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JSON_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		JSON_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return JSON_MAPPER;
	}

	public static String toJson(Object value) {
		try {
			return JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
