/*
 * Author: Ade Hendini
 * Email: adehendini@gmail.com
 * Date: 24/10/2022 19.02
 */

package ade.hendini.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;


@Configuration
public class JacksonConfiguration {

    @Bean
    @Primary
    public ObjectMapper customJson() {
        ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
                .build();

        mapper.registerModule(new JavaTimeModule());
        return mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .setVisibility(mapper.getSerializationConfig()
                        .getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME));
            builder.deserializers(new LocalTimeDeserializer(DateTimeFormatter.ISO_LOCAL_TIME));
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }

//    @Bean
//    @Primary
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.build();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        return objectMapper;
//    }

}
