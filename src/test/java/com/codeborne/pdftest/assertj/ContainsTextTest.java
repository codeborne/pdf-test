package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContainsTextTest {

  private PDF fiftyIdeasPdf;
  private PDF minimalPdf;

  @BeforeEach
  public void setUp() throws Exception {
    fiftyIdeasPdf = new PDF(requireNonNull(getClass().getClassLoader().getResource("50quickideas.pdf")));
    minimalPdf = new PDF(requireNonNull(getClass().getClassLoader().getResource("minimal.pdf")));
  }

  @Test
  public void canAssertThatPdfContainsText() {
    assertThat(fiftyIdeasPdf).containsText("50 Quick Ideas to Improve your User Stories");
    assertThat(fiftyIdeasPdf).containsText("Gojko Adzic");
    assertThat(fiftyIdeasPdf).containsText("©2013 - 2014 Gojko Adzic");
    assertThat(fiftyIdeasPdf).containsText("#50quickideas");
    assertThat(fiftyIdeasPdf).containsText("https://twitter.com/search?q=#50quickideas");
  }

  @Test
  public void shouldBeCaseSensitive() {
    assertThat(fiftyIdeasPdf).doesNotContainText("50 quick ideas");
  }

  @Test
  public void shouldIgnoreWhitespaces() {
    assertThat(fiftyIdeasPdf).containsText("Gojko       Adzic");
    assertThat(fiftyIdeasPdf).containsText("\tGojko \t Adzic\t");
    assertThat(fiftyIdeasPdf).containsText("Gojko \n Adzic\t\n");
    assertThat(fiftyIdeasPdf).containsText("Gojko \n Adzic\n");
    assertThat(fiftyIdeasPdf).containsText("Gojko\r\n Adzic\r\n");
    assertThat(fiftyIdeasPdf).containsText("Gojko \u00a0 Adzic\r\n");
  }

  @Test
  public void errorDescriptionForSingleParameter() {
    assertThatThrownBy(() -> assertThat(minimalPdf).containsText("Goodbye word"))
      .isInstanceOf(AssertionError.class)
      .hasMessage(String.format("%nExpecting actual:%n" +
                                "  \"Hello World\"%n" +
                                "to contain:%n" +
                                "  \"Goodbye word\" "));
  }

  @Test
  public void errorDescription_as() {
    assertThatThrownBy(() -> assertThat(minimalPdf).as("The World has changed").containsText("Goodbye word"))
      .isInstanceOf(AssertionError.class)
      .hasMessage(String.format("[The World has changed] %nExpecting actual:%n" +
                                "  \"Hello World\"%n" +
                                "to contain:%n" +
                                "  \"Goodbye word\" "));
  }

  @Test
  public void errorDescriptionForMultipleParameters() {
    assertThatThrownBy(() -> assertThat(minimalPdf).containsText("Goodbye word", "Privet", "Hello World"))
      .isInstanceOf(AssertionError.class)
      .hasMessage(String.format("%nExpecting actual:%n" +
                                "  \"Hello World\"%n" +
                                "to contain:%n" +
                                "  [\"Goodbye word\", \"Privet\", \"Hello World\"]%n" +
                                "but could not find:%n" +
                                "  [\"Goodbye word\", \"Privet\"]%n "));
  }

  @Test
  public void pdfShouldContainMultipleTexts() {
    assertThat(fiftyIdeasPdf).containsText("50", "Quick", "Ideas");
  }

  @Test
  public void shouldFailWhenOneTextIsMissing() {
    assertThatThrownBy(() -> {
      assertThat(fiftyIdeasPdf).containsText("50", "Quick", "Applications");
    }).isInstanceOf(AssertionError.class);
  }

  @Test
  public void shouldNotContainMultipleTexts() {
    assertThat(fiftyIdeasPdf).doesNotContainText("42", "Slow", "Applications");
  }

  @Test
  public void shouldFailWhenNotAllTextsAreMissing() {
    assertThatThrownBy(() -> {
      assertThat(fiftyIdeasPdf).doesNotContainText("42", "Slow", "Ideas");
    }).isInstanceOf(AssertionError.class);
  }
}
