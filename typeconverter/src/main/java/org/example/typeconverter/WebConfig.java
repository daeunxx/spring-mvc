package org.example.typeconverter;

import org.example.typeconverter.converter.IntegerToStringConverter;
import org.example.typeconverter.converter.IpPortToStringConverter;
import org.example.typeconverter.converter.StringToIntegerConverter;
import org.example.typeconverter.converter.StringToIpPortConverter;
import org.example.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    //주석처리 우선순위
//    registry.addConverter(new StringToIntegerConverter());
//    registry.addConverter(new IntegerToStringConverter());
    registry.addConverter(new StringToIpPortConverter());
    registry.addConverter(new IpPortToStringConverter());

    //추가
//    registry.addFormatter(new MyNumberFormatter());
  }
}
