package com.codeborne.pdftest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.fail;

public class InvalidPdfTest {
  @Test
  public void throws_IOException_ifFailedToReadPdfFile() {
    File missingFile = new File("src/test/resources/invalid-file.pdf");
    assertThatThrownBy(() -> new PDF(missingFile))
      .isInstanceOf(NoSuchFileException.class)
      .hasMessage(missingFile.getAbsolutePath());
  }

  @Test
  public void throws_IllegalArgumentException_inCaseOfInvalidPdfFile() throws IOException {
    try {
      new PDF(new File("build.gradle"));
      fail("expected IllegalArgumentException");
    }
    catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage(), is("Invalid PDF file: " + System.getProperty("user.dir") + "/build.gradle"));
      assertThat(expected.getCause(), is(notNullValue()));
    }
  }
}
