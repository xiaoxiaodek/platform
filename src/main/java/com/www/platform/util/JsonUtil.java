package com.www.platform.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by upsmart on 17-8-3.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午3:57
 */
@Component
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    // 定义jackson对象
    private static final ObjectMapper mapper;

    public ObjectMapper getMapper() {
        return mapper;
    }

    static {

        mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        mapper.registerModule(module);
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转换json字符失败!");
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("将json字符转换为对象时失败!");
        }
    }
}

class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeString(dateFormatter.format(value));
    }
}

class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeString(dateTimeFormatter.format(value));
    }

}

class LocalTimeSerializer extends JsonSerializer<LocalTime> {

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void serialize(LocalTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeString(timeFormatter.format(value));

    }
}
