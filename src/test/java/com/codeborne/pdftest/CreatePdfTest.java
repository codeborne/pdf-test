package com.codeborne.pdftest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.pdftest.PDF.containsText;
import static com.codeborne.pdftest.PDF.matchesText;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePdfTest {
  @Test
  public void fromFile() throws IOException {
    File file = new File("src/test/resources/50quickideas.pdf");
    assertThat(new PDF(file), containsText("50 Quick Ideas"));
  }
  
  @Test
  public void fromUrl() throws IOException {
    URL url = getClass().getClassLoader().getResource("50quickideas.pdf");
    assertThat(new PDF(url), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromUri() throws URISyntaxException, IOException {
    URI uri = getClass().getClassLoader().getResource("50quickideas.pdf").toURI();
    assertThat(new PDF(uri), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromInputStream() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("50quickideas.pdf");
    assertThat(new PDF(inputStream), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromBytes() throws URISyntaxException, IOException {
    byte[] pdf = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("50quickideas.pdf").toURI()));
    assertThat(new PDF(pdf), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromFileWithPageLimit() throws IOException {
    File file = new File("src/test/resources/50quickideas.pdf");
    assertThat(new PDF(file,1,2), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromUrlWithPageLimit() throws IOException {
    URL url = getClass().getClassLoader().getResource("50quickideas.pdf");
    assertThat(new PDF(url,1,2), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromUriWithPageLimit() throws URISyntaxException, IOException {
    URI uri = getClass().getClassLoader().getResource("50quickideas.pdf").toURI();
    assertThat(new PDF(uri,1,2), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromInputStreamWithPageLimit() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("50quickideas.pdf");
    assertThat(new PDF(inputStream,1,2), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromBytesWithPageLimit() throws URISyntaxException, IOException {
    byte[] pdf = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("50quickideas.pdf").toURI()));
    assertThat(new PDF(pdf,1,2), containsText("50 Quick Ideas"));
  }
}
