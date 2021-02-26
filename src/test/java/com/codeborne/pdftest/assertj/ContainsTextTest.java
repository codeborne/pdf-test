package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import java.io.IOException;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf).containsText("50 Quick Ideas to Improve your User Stories");
    assertThat(pdf).containsText("Gojko Adzic");
    assertThat(pdf).containsText("©2013 - 2014 Gojko Adzic");
    assertThat(pdf).containsText("#50quickideas");
    assertThat(pdf).containsText("https://twitter.com/search?q=#50quickideas");
  }

  @Test
  public void shouldBeCaseSensitive() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf).doesNotContainText("50 quick ideas");
  }

  @Test
  public void shouldIgnoreWhitespaces() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf).containsText("Gojko       Adzic");
    assertThat(pdf).containsText("\tGojko \t Adzic\t");
    assertThat(pdf).containsText("Gojko \n Adzic\t\n");
    assertThat(pdf).containsText("Gojko \n Adzic\n");
    assertThat(pdf).containsText("Gojko\r\n Adzic\r\n");
    assertThat(pdf).containsText("Gojko \u00a0 Adzic\r\n");
  }

  @Test
  public void errorDescription() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));
    assertThatThrownBy(() -> {
      assertThat(pdf).containsText("Goodbye word");
    })
        .isInstanceOf(AssertionError.class)
        .hasMessage("\nExpected: a PDF containing \"Goodbye word\"\n     but: was \"Hello World\"");
  }
}
