package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static com.codeborne.pdftest.PDF.doesNotContainText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class DoesNotContainTextTest {

  private PDF fiftyIdeasPdf;

  @Before
  public void setUp() throws Exception {
    fiftyIdeasPdf = new PDF(
            Objects.requireNonNull(getClass().getClassLoader().getResource("50quickideas.pdf"))
    );
  }

  @Test
  public void canAssertThatPdfContainsText() {
    assertThat(fiftyIdeasPdf, doesNotContainText("42 Quick Ideas"));
    assertThat(fiftyIdeasPdf, doesNotContainText("Bruce Willis"));
  }

  @Test
  public void shouldBeCaseSensitive() {
    assertThat(fiftyIdeasPdf, doesNotContainText("50 quick ideas"));
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
          is("\nExpected: a PDF not containing <[Hello World]>\n     but: was \"Hello World\""));
    }
  }

  @Test
  public void shouldNotContainMultipleTexts() {
    assertThat(fiftyIdeasPdf, doesNotContainText("42", "Slow", "Applications"));
  }

  @Test(expected = AssertionError.class)
  public void shouldFailWhenNotAllTextsAreMissing() {
    assertThat(fiftyIdeasPdf, doesNotContainText("42", "Slow", "Ideas"));
  }
}
