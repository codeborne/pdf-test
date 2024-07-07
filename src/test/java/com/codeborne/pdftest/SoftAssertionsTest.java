package com.codeborne.pdftest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.MultipleFailuresError;

import java.io.IOException;

import static com.codeborne.pdftest.PDF.containsExactText;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SoftAssertionsTest {
  @Test
  public void canUseSoftAssertions() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));

    assertThatThrownBy(() -> {
      assertAll("minimal PDF",
        () -> assertThat(pdf, containsExactText("one")),
        () -> assertThat(pdf, containsExactText("two")),
        () -> assertThat(pdf, containsExactText("three")),
        () -> assertThat(pdf, containsExactText("four"))
      );
    })
      .isInstanceOf(MultipleFailuresError.class)
      .hasMessageStartingWith("minimal PDF (4 failures)")
      .isInstanceOfSatisfying(MultipleFailuresError.class, (MultipleFailuresError e) -> {
        Assertions.assertThat(e.getFailures()).hasSize(4);
        Assertions.assertThat(e.getFailures().get(0)).hasMessageContaining("Expected: a PDF containing \"one\"");
        Assertions.assertThat(e.getFailures().get(1)).hasMessageContaining("Expected: a PDF containing \"two\"");
        Assertions.assertThat(e.getFailures().get(2)).hasMessageContaining("Expected: a PDF containing \"three\"");
        Assertions.assertThat(e.getFailures().get(3)).hasMessageContaining("Expected: a PDF containing \"four\"");
      });
  }
}
