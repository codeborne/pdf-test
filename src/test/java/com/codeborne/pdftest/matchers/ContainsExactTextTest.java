package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import static com.codeborne.pdftest.PDF.containsExactText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ContainsExactTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsExactText("50 Quick Ideas to Improve your User Stories"));
  }

  @Test
  public void shouldBeCaseSensitive() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, not(containsExactText("50 quick ideas")));
  }

  @Test
  public void shouldNotIgnoreWhitespaces() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsExactText("Gojko Adzic"));
    assertThat(pdf, not(containsExactText("Gojko       Adzic")));
    assertThat(pdf, not(containsExactText("\tGojko \t Adzic\n")));
    assertThat(pdf, not(containsExactText("Gojko \u00a0 Adzic\r\n")));
  }
}
