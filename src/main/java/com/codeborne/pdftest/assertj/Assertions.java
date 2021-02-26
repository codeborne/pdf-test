package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;

public class Assertions extends org.assertj.core.api.Assertions {
  public static PdfAssert assertThat(PDF actual) {
    return new PdfAssert(actual);
  }
}
