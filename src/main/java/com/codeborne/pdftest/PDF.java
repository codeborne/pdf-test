package com.codeborne.pdftest;

import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.pdftest.matchers.ContainsText;
import com.codeborne.pdftest.matchers.ContainsTextCaseInsensitive;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.hamcrest.Matcher;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;

import static com.codeborne.pdftest.IO.readBytes;
import static com.codeborne.pdftest.IO.readFile;
import static com.codeborne.pdftest.IO.toURL;

public class PDF {
  public final byte[] content;
  
  public final String text;
  public final int numberOfPages;
  public final String author;
  public final Calendar creationDate;
  public final String creator;
  public final String keywords;
  public final String producer;
  public final String subject;
  public final String title;
  public final boolean encrypted;

  private PDF(String name, byte[] content) {
    this.content = content;

    try (InputStream inputStream = new ByteArrayInputStream(content)) {
      PDDocument pdf = PDDocument.load(inputStream);
      try {
        this.text = new PDFTextStripper().getText(pdf);
        this.numberOfPages = pdf.getNumberOfPages();
        this.author = pdf.getDocumentInformation().getAuthor();
        this.creationDate = pdf.getDocumentInformation().getCreationDate();
        this.creator = pdf.getDocumentInformation().getCreator();
        this.keywords = pdf.getDocumentInformation().getKeywords();
        this.producer = pdf.getDocumentInformation().getProducer();
        this.subject = pdf.getDocumentInformation().getSubject();
        this.title = pdf.getDocumentInformation().getTitle();
        this.encrypted = pdf.isEncrypted();
      }
      finally {
        try {pdf.close();}
        catch (IOException ignore) {}
      }
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid PDF " + name, e);
    }
  }

  public PDF(File pdfFile) {
    this(pdfFile.getAbsolutePath(), readFile(pdfFile));
  }
  
  public PDF(URL url) {
    this(url.toString(), readBytes(url));
  }

  public PDF(URI uri) {
    this(toURL(uri));
  }
  
  public PDF(byte[] content) {
    this("", content);
  }

  public PDF(InputStream inputStream) {
    this(readBytes(inputStream));
  }
  
  public static Matcher<PDF> containsText(String text) {
    return new ContainsText(text);
  }
  public static Matcher<PDF> containsExactText(String text) {
    return new ContainsExactText(text);
  }
  public static Matcher<PDF> containsTextCaseInsensitive(String text) {
    return new ContainsTextCaseInsensitive(text);
  }
}
