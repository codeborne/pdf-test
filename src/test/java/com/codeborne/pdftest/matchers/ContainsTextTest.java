package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.pdftest.PDF.doesNotContainText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class ContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
    assertThat(pdf, containsText("Gojko Adzic"));
    assertThat(pdf, containsText("©2013 - 2014 Gojko Adzic"));
    assertThat(pdf, containsText("#50quickideas"));
    assertThat(pdf, containsText("https://twitter.com/search?q=#50quickideas"));
  }

  @Test
  public void shouldBeCaseSensitive() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, not(containsText("50 quick ideas")));
    assertThat(pdf, doesNotContainText("50 quick ideas"));
  }

  @Test
  public void shouldIgnoreWhitespaces() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("Gojko       Adzic"));
    assertThat(pdf, containsText("\tGojko \t Adzic\t"));
    assertThat(pdf, containsText("Gojko \n Adzic\t\n"));
    assertThat(pdf, containsText("Gojko \n Adzic\n"));
    assertThat(pdf, containsText("Gojko\r\n Adzic\r\n"));
    assertThat(pdf, containsText("Gojko \u00a0 Adzic\r\n"));
  }

  @Test
  public void errorDescription() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));
    try {
      assertThat(pdf, containsText("Goodbye word"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
          is("\nExpected: a PDF containing \"Goodbye word\"\n     but: was \"Hello World\""));
    }
  }

  @Test
  public void pdfShouldContainMultipleTexts() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50", "Quick", "Ideas"));
  }

  @Test(expected = AssertionError.class)
  public void shouldFailWhenOneTextIsMissing() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50", "Quick", "Applications"));
  }
}
