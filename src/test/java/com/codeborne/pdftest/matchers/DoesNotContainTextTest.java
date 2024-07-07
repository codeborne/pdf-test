package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.codeborne.pdftest.PDF.doesNotContainText;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class DoesNotContainTextTest {

  private PDF fiftyIdeasPdf;
  private PDF minimalPdf;

  @BeforeEach
  public void setUp() throws Exception {
    fiftyIdeasPdf = new PDF(
            Objects.requireNonNull(getClass().getClassLoader().getResource("50quickideas.pdf"))
    );
    minimalPdf = new PDF(Objects.requireNonNull(getClass().getClassLoader().getResource("minimal.pdf")));
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
  public void errorDescriptionForSingleParameter() {
    try {
      assertThat(minimalPdf, doesNotContainText("Hello World"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
          is("\nExpected: a PDF not containing \"Hello World\"\n     but: was \"Hello World\""));
    }
  }

  @Test
  public void errorDescriptionForMultipleParameters() {
    try {
      assertThat(minimalPdf, doesNotContainText("Hello", "World"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
              is("\nExpected: a PDF not containing \"Hello\", \"World\"\n     but: was \"Hello World\""));
    }
  }

  @Test
  public void shouldNotContainMultipleTexts() {
    assertThat(fiftyIdeasPdf, doesNotContainText("42", "Slow", "Applications"));
  }

  @Test
  public void shouldFailWhenNotAllTextsAreMissing() {
    assertThatThrownBy(() -> assertThat(fiftyIdeasPdf, doesNotContainText("42", "Slow", "Ideas")))
      .isInstanceOf(AssertionError.class)
      .hasMessageContaining("Expected: a PDF not containing \"42\", \"Slow\", \"Ideas\"")
      .hasMessageContaining("but: was \"50 Quick Ideas");
  }
}
