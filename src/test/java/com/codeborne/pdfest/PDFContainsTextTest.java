package com.codeborne.pdfest;

import org.junit.Test;

import java.io.IOException;

import static com.codeborne.pdfest.PDFMatchers.containsText;
import static org.junit.Assert.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
    assertThat(pdf, containsText("Gojko Adzic"));
    assertThat(pdf, containsText("©2013 - 2014 Gojko Adzic"));
    assertThat(pdf, containsText("#50quickideas"));
    assertThat(pdf, containsText("https://twitter.com/search?q=#50quickideas"));
  }
}