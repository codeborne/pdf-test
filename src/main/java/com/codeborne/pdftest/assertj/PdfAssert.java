package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.pdftest.matchers.ContainsText;
import com.codeborne.pdftest.matchers.ContainsTextCaseInsensitive;
import com.codeborne.pdftest.matchers.DoesNotContainExactText;
import com.codeborne.pdftest.matchers.DoesNotContainText;
import org.assertj.core.api.AbstractAssert;

import static org.hamcrest.MatcherAssert.assertThat;

public class PdfAssert extends AbstractAssert<PdfAssert, PDF> {
  public PdfAssert(PDF actual) {
    super(actual, PdfAssert.class);
  }

  public PdfAssert containsText(String... strings) {
    isNotNull();
    assertThat(actual, new ContainsText(strings));
    return this;
  }

  public PdfAssert doesNotContainText(String... strings) {
    isNotNull();
    assertThat(actual, new DoesNotContainText(strings));
    return this;
  }

  public PdfAssert containsExactText(String substring) {
    isNotNull();
    assertThat(actual, new ContainsExactText(substring));
    return this;
  }

  public PdfAssert doesNotContainExactText(String substring) {
    isNotNull();
    assertThat(actual, new DoesNotContainExactText(substring));
    return this;
  }

  public PdfAssert containsTextCaseInsensitive(String substring) {
    isNotNull();
    assertThat(actual, new ContainsTextCaseInsensitive(substring));
    return this;
  }
}
