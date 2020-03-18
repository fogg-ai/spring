package org.itstep.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

import java.time.LocalDate;

public class ConversionDemo {

    @Test
    void convertToInt() {
        ConversionService conversionService = new DefaultConversionService();
        Integer number = conversionService.convert("1", Integer.class);
        Assertions.assertEquals(1, number);
    }

    @Test
    void convertToLocalDate() {
        ConversionService conversionService = new DefaultConversionService();
        LocalDate date = conversionService.convert("1990-01-18", LocalDate.class);
        Assertions.assertEquals(LocalDate.of(1990, 1, 18), date);
    }
}
