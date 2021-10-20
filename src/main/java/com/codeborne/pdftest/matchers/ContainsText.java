package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ContainsText extends PDFMatcher {
  private final String[] strings;

  public ContainsText(String... strings) {
    this.strings = strings;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    String reducedPdfText = reduceSpaces(item.text);
    return Arrays.stream(strings).allMatch(str -> reducedPdfText.contains(reduceSpaces(str)));
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(reduceSpaces(item.text)).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    List<String> reducedStrings = Arrays.stream(strings).map(this::reduceSpaces).collect(Collectors.toList());
    description.appendText("a PDF containing ").appendValue(reducedStrings);
  }
}
