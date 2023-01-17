package com.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig extends WebMvcConfigurationSupport {

    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        var dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        dateTimeRegistrar.registerFormatters(conversionService);

        var dateRegistrar = new DateFormatterRegistrar();
        dateRegistrar.setFormatter(new DateFormatter("dd-MM-yyyy"));
        dateRegistrar.registerFormatters(conversionService);

        return conversionService;
    }
}
