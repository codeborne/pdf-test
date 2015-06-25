package com.codeborne.pdfest;

import com.codeborne.pdfest.matchers.PDFContainsString;
import org.hamcrest.Matcher;

public class PDFMatchers {
  public static Matcher<PDF> containsText(String text) {
    return new PDFContainsString(text);
  }
}
