package com.codeborne.pdftest;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static com.codeborne.pdftest.PDF.matchesText;
import static org.hamcrest.MatcherAssert.assertThat;

public class MatchRegexTest {
  @Test
  public void verifyTextWithRegex() throws IOException {
    URL url = getClass().getClassLoader().getResource("50quickideas.pdf");
    assertThat(new PDF(url), matchesText(".*50 Quick Ideas.+50 Quick Ideas.*"));
  }
}
