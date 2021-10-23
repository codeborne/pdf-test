package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DoesNotContainText extends PDFMatcher {
  private final String text;
  private final String[] texts;

  public DoesNotContainText(String text, String... texts) {
    this.text = text;
    this.texts = texts;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    String reducedPdfText = reduceSpaces(item.text);
    return !reducedPdfText.contains(reduceSpaces(text)) &&
            Arrays.stream(texts).noneMatch(str -> reducedPdfText.contains(reduceSpaces(str)));
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(reduceSpaces(item.text)).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a PDF not containing ");
    buildErrorMessage(description, text, texts);
  }
}
