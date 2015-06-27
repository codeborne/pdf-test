package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import static com.codeborne.pdftest.PDFMatchers.containsTextCaseInsensitive;
import static org.junit.Assert.assertThat;

public class ContainsTextCaseInsensitiveTest {
  @Test
  public void canAssertThatPdfContainsTextIgnoringCase() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsTextCaseInsensitive("50 quick ideas to improve your user stories"));
    assertThat(pdf, containsTextCaseInsensitive("50 quick IDEAS to improve YOUR user STORIES"));
  }

  @Test
  public void shouldIgnoreWhitespaces() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsTextCaseInsensitive("\n\tgOJKO     aDZIC  \u00a0  "));
  }
}
