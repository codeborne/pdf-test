package com.codeborne.pdftest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

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

  private static byte[] readFile(File pdfFile) {
    try {
      return Files.readAllBytes(Paths.get(pdfFile.getAbsolutePath()));
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public PDF(URL url) {
    this(url.toString(), readBytes(url));
  }

  public PDF(URI uri) {
    this(toURL(uri));
  }

  private static URL toURL(URI uri) {
    try {
      return uri.toURL();
    }
    catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public PDF(byte[] content) {
    this("", content);
  }

  public PDF(InputStream inputStream) {
    this(readBytes(inputStream));
  }

  private static byte[] readBytes(URL url) {
    try (InputStream inputStream = url.openStream()) {
      return readBytes(inputStream);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
  
  private static byte[] readBytes(InputStream inputStream) {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    try {
      byte[] buffer = new byte[2048];

      int nRead;
      while ((nRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
        result.write(buffer, 0, nRead);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

    return result.toByteArray();
  }
}
