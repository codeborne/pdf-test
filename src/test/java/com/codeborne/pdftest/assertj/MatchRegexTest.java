package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import org.junit.Test;

import java.io.IOException;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static java.util.Objects.requireNonNull;

public class MatchRegexTest {
  @Test
  public void verifyTextWithRegex() throws IOException {
    PDF pdf = new PDF(requireNonNull(getClass().getClassLoader().getResource("50quickideas.pdf")));
    assertThat(pdf).matchesText(".*50 Quick Ideas.+50 Quick Ideas.*");
    assertThat(pdf).matchesText("50 Quick Ideas.*Gojko Adzic.*2014-03-25.*©2013 - 2014.*");
  }
}
