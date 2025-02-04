package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;
import org.opentest4j.MultipleFailuresError;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SoftAssertionsTest {
  @Test
  public void canUseSoftAssertions() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));

    PdfSoftAssertions softly = new PdfSoftAssertions();
    softly.assertThat(pdf).containsExactText("one");
    softly.assertThat(pdf).containsExactText("two");
    softly.assertThat(pdf).containsExactText("three");
    softly.assertThat(pdf).containsExactText("four");

    assertThatThrownBy(softly::assertAll)
      .isInstanceOf(MultipleFailuresError.class)
      .hasMessageContaining("Multiple Failures (4 failures)")
      .isInstanceOfSatisfying(MultipleFailuresError.class, e -> {
        assertThat(e.getFailures()).hasSize(4);
        assertThat(e.getFailures().get(0)).hasMessageStartingWith(message("one"));
        assertThat(e.getFailures().get(1)).hasMessageStartingWith(message("two"));
        assertThat(e.getFailures().get(2)).hasMessageStartingWith(message("three"));
        assertThat(e.getFailures().get(3)).hasMessageStartingWith(message("four"));
      });
  }

  private static String message(String expected) {
    return String.format("%n" +
                         "Expecting actual:%n" +
                         "  \"Hello World%n\"%n" +
                         "to contain:%n" +
                         "  \"" + expected + "\"");
  }
}
