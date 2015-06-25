package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

public  class PDFContainsString extends TypeSafeMatcher<PDF> implements SelfDescribing {
  private final String substring;

  public PDFContainsString(String substring) {
    this.substring = substring;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    return item.text.contains(substring);
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(item.text).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a PDF containing ").appendValue(substring);
  }
}
