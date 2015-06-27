package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;

public class ContainsTextCaseInsensitive extends PDFMatcher {
  private final String substring;

  public ContainsTextCaseInsensitive(String substring) {
    this.substring = substring;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    return reduceSpaces(item.text).toLowerCase().contains(reduceSpaces(substring).toLowerCase());
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(reduceSpaces(item.text)).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a PDF containing ").appendValue(reduceSpaces(substring));
  }
}
