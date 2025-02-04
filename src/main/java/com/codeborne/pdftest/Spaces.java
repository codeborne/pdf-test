package com.codeborne.pdftest;

import java.util.stream.Stream;

public class Spaces {
  public static String reduce(String text) {
    return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
  }

  public static String[] reduce(String text, String... texts) {
    return Stream.concat(Stream.of(text), Stream.of(texts))
      .map(Spaces::reduce)
      .toArray(String[]::new);
  }
}
