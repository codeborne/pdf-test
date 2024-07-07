package com.codeborne.pdftest;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PDFInformationTest {
  @Test
  public void canGetInformationFromPdf() throws IOException, ParseException {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Tallinn"));

    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf.author, equalTo("Gojko Adzic"));
    assertThat(pdf.creationDate.getTime(),
        equalTo(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("25.03.2014 20:31:28")));
    assertThat(pdf.creator, equalTo("LaTeX with hyperref package"));
    assertThat(pdf.encrypted, is(false));
    assertThat(pdf.keywords, is(nullValue()));
    assertThat(pdf.numberOfPages, equalTo(17));
    assertThat(pdf.producer, equalTo("xdvipdfmx (0.7.8)"));
    assertThat(pdf.subject, is(nullValue()));
    assertThat(pdf.title, equalTo("50 Quick Ideas to Improve your User Stories"));
    assertFalse(pdf.signed);
    assertNull(pdf.signerName);
    assertNull(pdf.signatureTime);
  }
}
