package org.example.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.example.typeconverter.type.IpPort;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {

  @Override
  public IpPort convert(String source) {
    log.info("convert source={}", source);

    //"127.0.0.1:8080"
    int index = source.indexOf(":");
    String ip = source.substring(0, index);
    int port = Integer.parseInt(source.substring(index + 1));

    return new IpPort(ip, port);
  }
}
