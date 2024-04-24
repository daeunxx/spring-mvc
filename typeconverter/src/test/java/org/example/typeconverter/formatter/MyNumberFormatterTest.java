package org.example.typeconverter.formatter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Locale;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MyNumberFormatterTest {

  MyNumberFormatter formatter = new MyNumberFormatter();

  @Test
  void parse() throws ParseException {
    Number result = formatter.parse("1,000", Locale.KOREA);
    //내부에서는 Long 타입으로 리턴
    assertThat(result).isEqualTo(1000L);
  }

  @Test
  void print() {
    String result = formatter.print(1000, Locale.KOREA);
    assertThat(result).isEqualTo("1,000");
  }
}