package com.codeborne.pdfest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.codeborne.pdfest.PDFMatchers.containsText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CreatePdfTest {
  @Test
  public void fromFile() throws URISyntaxException, IOException {
    File file = new File(getClass().getClassLoader().getResource("50quickideas.pdf").toURI());
    assertThat(new PDF(file), containsText("50 Quick Ideas"));
  }
  
  @Test
  public void fromUrl() throws URISyntaxException, IOException {
    URL url = getClass().getClassLoader().getResource("50quickideas.pdf");
    assertThat(new PDF(url), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromUri() throws URISyntaxException, IOException {
    URI uri = getClass().getClassLoader().getResource("50quickideas.pdf").toURI();
    assertThat(new PDF(uri), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromInputStream() throws URISyntaxException, IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("50quickideas.pdf");
    assertThat(new PDF(inputStream), containsText("50 Quick Ideas"));
  }

  @Test
  public void fromBytes() throws URISyntaxException, IOException {
    byte[] pdf = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("50quickideas.pdf").toURI()));
    assertThat(new PDF(pdf), containsText("50 Quick Ideas"));
  }
}