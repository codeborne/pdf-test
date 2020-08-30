package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;

public class DoesNotContainExactText extends PDFMatcher {
  private final String substring;

  public DoesNotContainExactText(String substring) {
    this.substring = substring;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    return !item.text.contains(substring);
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(item.text).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a PDF not containing exactly ").appendValue(substring);
  }
}
