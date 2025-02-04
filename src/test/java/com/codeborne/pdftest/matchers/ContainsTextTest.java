package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.assertj.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.pdftest.PDF.doesNotContainText;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContainsTextTest {

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
    assertThat(fiftyIdeasPdf, containsText("50 Quick Ideas to Improve your User Stories"));
    assertThat(fiftyIdeasPdf, containsText("Gojko Adzic"));
    assertThat(fiftyIdeasPdf, containsText("©2013 - 2014 Gojko Adzic"));
    assertThat(fiftyIdeasPdf, containsText("#50quickideas"));
    assertThat(fiftyIdeasPdf, containsText("https://twitter.com/search?q=#50quickideas"));
  }

  @Test
  public void shouldBeCaseSensitive() {
    assertThat(fiftyIdeasPdf, not(containsText("50 quick ideas")));
    assertThat(fiftyIdeasPdf, doesNotContainText("50 quick ideas"));
  }

  @Test
  public void shouldIgnoreWhitespaces() {
    assertThat(fiftyIdeasPdf, containsText("Gojko       Adzic"));
    assertThat(fiftyIdeasPdf, containsText("\tGojko \t Adzic\t"));
    assertThat(fiftyIdeasPdf, containsText("Gojko \n Adzic\t\n"));
    assertThat(fiftyIdeasPdf, containsText("Gojko \n Adzic\n"));
    assertThat(fiftyIdeasPdf, containsText("Gojko\r\n Adzic\r\n"));
    assertThat(fiftyIdeasPdf, containsText("Gojko \u00a0 Adzic\r\n"));
  }

  @Test
  public void errorDescriptionForSingleParameter() {
    try {
      assertThat(minimalPdf, containsText("Goodbye word"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
          is("\nExpected: a PDF containing \"Goodbye word\"\n     but: was \"Hello World\""));
    }
  }

  @Test
  public void errorDescriptionForMultipleParameters() {
    assertThatThrownBy(() -> Assertions.assertThat(minimalPdf).containsText("Goodbye word", "Privet", "Hello World"))
            .isInstanceOf(AssertionError.class)
            .hasMessage(String.format("%nExpecting actual:%n" +
                                      "  \"Hello World\"%nto contain:%n" +
                                      "  [\"Goodbye word\", \"Privet\", \"Hello World\"]%n" +
                                      "but could not find:%n" +
                                      "  [\"Goodbye word\", \"Privet\"]%n "));
  }

  @Test
  public void pdfShouldContainMultipleTexts() {
    assertThat(fiftyIdeasPdf, containsText("50", "Quick", "Ideas"));
  }

  @Test
  public void shouldFailWhenOneTextIsMissing() {
    assertThatThrownBy(() -> assertThat(fiftyIdeasPdf, containsText("50", "Quick", "Applications")))
      .isInstanceOf(AssertionError.class)
      .hasMessageContaining("Expected: a PDF containing \"50\", \"Quick\", \"Applications\"")
      .hasMessageContaining("but: was \"50 Quick Ideas");
  }
}
