package com.codeborne.pdftest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class InvalidPdfTest {
  @Test(expected = NoSuchFileException.class)
  public void throws_IOException_ifFailedToReadPdfFile() throws IOException {
    new PDF(new File("src/test/resources/invalid-file.pdf"));
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
