package org.example.typeconverter.converter;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.example.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

  @Test
  void conversionService() {
    //등록
    DefaultConversionService conversionService = new DefaultConversionService();
    conversionService.addConverter(new StringToIntegerConverter());
    conversionService.addConverter(new IntegerToStringConverter());
    conversionService.addConverter(new StringToIpPortConverter());
    conversionService.addConverter(new IpPortToStringConverter());

    //사용
    assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
    assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
    assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(
        new IpPort("127.0.0.1", 8080));
    assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class)).isEqualTo(
        "127.0.0.1:8080");
  }
}
