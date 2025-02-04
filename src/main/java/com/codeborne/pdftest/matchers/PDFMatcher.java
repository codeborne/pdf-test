package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.Spaces;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract class PDFMatcher extends TypeSafeMatcher<PDF> implements SelfDescribing {
  protected String reduceSpaces(String text) {
    return Spaces.reduce(text);
  }

  protected void buildErrorMessage(Description description, String text, String[] texts) {
    if (texts.length > 0) {
      List<String> reducedStrings = Arrays.stream(texts).map(Spaces::reduce).collect(Collectors.toList());
      reducedStrings.add(0, reduceSpaces(text));
      description.appendValueList("", ", ", "", reducedStrings);
    } else {
      description.appendValue(reduceSpaces(text));
    }
  }
}
