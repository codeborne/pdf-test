package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.hamcrest.Description;

import java.util.regex.Pattern;

public class MatchesText extends PDFMatcher {
  private final Pattern expectedText;

  public MatchesText(Pattern expectedText) {
    this.expectedText = expectedText;
  }

  @Override
  protected boolean matchesSafely(PDF item) {
    String reducedPdfText = reduceSpaces(item.text);
    return expectedText.matcher(reducedPdfText).matches();
  }

  @Override
  protected void describeMismatchSafely(PDF item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(reduceSpaces(item.text)).appendText("\"");
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a PDF matching ");
    buildErrorMessage(description, expectedText.toString(), new String[0]);
  }


}

