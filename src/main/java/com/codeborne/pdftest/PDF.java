package com.codeborne.pdftest;

import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.pdftest.matchers.ContainsText;
import com.codeborne.pdftest.matchers.ContainsTextCaseInsensitive;
import com.codeborne.pdftest.matchers.DoesNotContainExactText;
import com.codeborne.pdftest.matchers.DoesNotContainText;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.text.PDFTextStripper;
import org.hamcrest.Matcher;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Calendar;

import static java.nio.file.Files.readAllBytes;

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
  public final boolean signed;
  public final String signerName;
  public final Calendar signatureTime;

  private PDF(String name, byte[] content) {
    this(name, content, 1, Integer.MAX_VALUE);
  }

  private PDF(String name, byte[] content, int startPage, int endPage) {
    this.content = content;

    try (InputStream inputStream = new ByteArrayInputStream(content)) {
      try (PDDocument pdf = PDDocument.load(inputStream)) {
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setStartPage(startPage);
        pdfTextStripper.setEndPage(endPage);
        this.text = pdfTextStripper.getText(pdf);
        this.numberOfPages = pdf.getNumberOfPages();
        this.author = pdf.getDocumentInformation().getAuthor();
        this.creationDate = pdf.getDocumentInformation().getCreationDate();
        this.creator = pdf.getDocumentInformation().getCreator();
        this.keywords = pdf.getDocumentInformation().getKeywords();
        this.producer = pdf.getDocumentInformation().getProducer();
        this.subject = pdf.getDocumentInformation().getSubject();
        this.title = pdf.getDocumentInformation().getTitle();
        this.encrypted = pdf.isEncrypted();

        PDSignature signature = pdf.getLastSignatureDictionary();
        this.signed = signature != null;
        this.signerName = signature == null ? null : signature.getName();
        this.signatureTime = signature == null ? null : signature.getSignDate();
      }
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid PDF file: " + name, e);
    }
  }

  public PDF(File pdfFile) throws IOException {
    this(pdfFile.getAbsolutePath(), readAllBytes(Paths.get(pdfFile.getAbsolutePath())));
  }
  
  public PDF(URL url) throws IOException {
    this(url.toString(), readBytes(url));
  }

  public PDF(URI uri) throws IOException {
    this(uri.toURL());
  }
  
  public PDF(byte[] content) {
    this("", content);
  }

  public PDF(InputStream inputStream) throws IOException {
    this(readBytes(inputStream));
  }

  public PDF(File pdfFile, int startPage, int endPage) throws IOException {
    this(pdfFile.getAbsolutePath(), readAllBytes(Paths.get(pdfFile.getAbsolutePath())), startPage, endPage);
  }

  public PDF(URL url, int startPage, int endPage) throws IOException {
    this(url.toString(), readBytes(url), startPage, endPage);
  }

  public PDF(URI uri, int startPage, int endPage) throws IOException {
    this(uri.toURL(), startPage, endPage);
  }

  public PDF(byte[] content, int startPage, int endPage) {
    this("", content, startPage, endPage);
  }

  public PDF(InputStream inputStream, int startPage, int endPage) throws IOException {
    this(readBytes(inputStream), startPage, endPage);
  }

  private static byte[] readBytes(URL url) throws IOException {
    try (InputStream inputStream = url.openStream()) {
      return readBytes(inputStream);
    }
  }

  private static byte[] readBytes(InputStream inputStream) throws IOException {
    ByteArrayOutputStream result = new ByteArrayOutputStream(2048);
    byte[] buffer = new byte[2048];

    int nRead;
    while ((nRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
      result.write(buffer, 0, nRead);
    }

    return result.toByteArray();
  }

  public static Matcher<PDF> containsText(String... texts) {
    return new ContainsText(texts);
  }
  public static Matcher<PDF> doesNotContainText(String... texts) {
    return new DoesNotContainText(texts);
  }
  public static Matcher<PDF> containsExactText(String text) {
    return new ContainsExactText(text);
  }
  public static Matcher<PDF> doesNotContainExactText(String text) {
    return new DoesNotContainExactText(text);
  }
  public static Matcher<PDF> containsTextCaseInsensitive(String text) {
    return new ContainsTextCaseInsensitive(text);
  }
}
