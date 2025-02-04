package com.codeborne.pdftest.assertj;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.Spaces;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Strings;

import java.util.regex.Pattern;

public class PdfAssert extends AbstractAssert<PdfAssert, PDF> {
  private final Strings strings = Strings.instance();

  public PdfAssert(PDF actual) {
    super(actual, PdfAssert.class);
  }

  public PdfAssert containsText(String text, String... texts) {
    isNotNull();
    strings.assertContains(info, Spaces.reduce(actual.text), Spaces.reduce(text, texts));
    return this;
  }

  public PdfAssert doesNotContainText(String text, String... texts) {
    isNotNull();
    strings.assertDoesNotContain(info, Spaces.reduce(actual.text), Spaces.reduce(text, texts));
    return this;
  }

  public PdfAssert containsExactText(String substring) {
    isNotNull();
    strings.assertContains(info, actual.text, substring);
    return this;
  }

  public PdfAssert doesNotContainExactText(String substring) {
    isNotNull();
    strings.assertDoesNotContain(info, actual.text, substring);
    return this;
  }

  public PdfAssert containsTextCaseInsensitive(String substring) {
    isNotNull();
    strings.assertContainsIgnoringCase(info, Spaces.reduce(actual.text), Spaces.reduce(substring));
    return this;
  }

  public PdfAssert matchesText(String regex) {
    return matchesText(Pattern.compile(regex));
  }

  public PdfAssert matchesText(Pattern regex) {
    isNotNull();
    strings.assertMatches(info, Spaces.reduce(actual.text), regex);
    return this;
  }
}
