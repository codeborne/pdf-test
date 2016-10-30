package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsTextCaseInsensitive;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ContainsTextCaseInsensitiveTest {
  @Test
  public void canAssertThatPdfContainsTextIgnoringCase() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsTextCaseInsensitive("50 quick ideas to improve your user stories"));
    assertThat(pdf, containsTextCaseInsensitive("50 quick IDEAS to improve YOUR user STORIES"));
  }

  @Test
  public void shouldIgnoreWhitespaces() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsTextCaseInsensitive("\n\tgOJKO     aDZIC  \u00a0  "));
  }

  @Test
  public void errorDescription() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));
    try {
      assertThat(pdf, containsTextCaseInsensitive("Goodbye word"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
          is("\nExpected: a PDF containing \"Goodbye word\"\n     but: was \"Hello World\""));
    }
  }
}
