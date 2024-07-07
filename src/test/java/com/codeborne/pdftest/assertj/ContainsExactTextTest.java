package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContainsExactTextTest {
  @Test
  public void canAssertThatPdfContainsText() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf).containsExactText("50 Quick Ideas to Improve your User Stories");
  }

  @Test
  public void shouldBeCaseSensitive() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf).doesNotContainExactText("50 quick ideas");
  }

  @Test
  public void shouldNotIgnoreWhitespaces() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf).containsExactText("Gojko Adzic");
    assertThat(pdf).doesNotContainExactText("Gojko       Adzic");
    assertThat(pdf).doesNotContainExactText("\tGojko \t Adzic\n");
    assertThat(pdf).doesNotContainExactText("Gojko \u00a0 Adzic\r\n");
  }

  @Test
  public void errorDescription() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));

    assertThatThrownBy(() -> {
      assertThat(pdf).containsExactText("Goodbye word");
    })
        .isInstanceOf(AssertionError.class)
        .hasMessage("\nExpected: a PDF containing \"Goodbye word\"\n     but: was \"Hello World\n\"");
  }
}
