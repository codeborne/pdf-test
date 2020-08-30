package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.doesNotContainText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class DoesNotContainTextTest {
  @Test
  public void canAssertThatPdfContainsText() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, doesNotContainText("42 Quick Ideas"));
    assertThat(pdf, doesNotContainText("Bruce Willis"));
  }

  @Test
  public void shouldBeCaseSensitive() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, doesNotContainText("50 quick ideas"));
  }

  @Test
  public void errorDescription() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));
    try {
      assertThat(pdf, doesNotContainText("Hello World"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
          is("\nExpected: a PDF not containing \"Hello World\"\n     but: was \"Hello World\""));
    }
  }
}
