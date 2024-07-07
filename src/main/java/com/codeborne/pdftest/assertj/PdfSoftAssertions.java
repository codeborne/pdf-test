package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import org.assertj.core.api.SoftAssertions;

public class PdfSoftAssertions extends SoftAssertions {
  public PdfAssert assertThat(PDF actual) {
    return proxy(PdfAssert.class, PDF.class, actual);
  }
}
