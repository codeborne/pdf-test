package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract class PDFMatcher extends TypeSafeMatcher<PDF> implements SelfDescribing {
  protected String reduceSpaces(String text) {
    return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
  }

  protected void buildErrorMessage(Description description, String text, String[] texts) {
    if (texts.length > 0) {
      List<String> reducedStrings = Arrays.stream(texts).map(this::reduceSpaces).collect(Collectors.toList());
      reducedStrings.add(0, reduceSpaces(text));
      description.appendValueList("", ", ", "", reducedStrings);
    } else {
      description.appendValue(reduceSpaces(text));
    }
  }
}
