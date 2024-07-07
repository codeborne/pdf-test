package com.codeborne.pdftest.matchers;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.doesNotContainExactText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class DoesNotContainExactTextTest {
  @Test
  public void shouldBeCaseSensitive() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, doesNotContainExactText("50 quick ideas"));
  }

  @Test
  public void shouldNotIgnoreWhitespaces() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, doesNotContainExactText("Gojko       Adzic"));
    assertThat(pdf, doesNotContainExactText("\tGojko \t Adzic\n"));
    assertThat(pdf, doesNotContainExactText("Gojko \u00a0 Adzic\r\n"));
  }

  @Test
  public void errorDescription() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));
    try {
      assertThat(pdf, doesNotContainExactText("Hello World"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(),
          is("\nExpected: a PDF not containing exactly \"Hello World\"\n     but: was \"Hello World\n\""));
    }
  }
}
