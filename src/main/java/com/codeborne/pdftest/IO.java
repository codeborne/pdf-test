package com.codeborne.pdftest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IO {
  static byte[] readFile(File pdfFile) {
    try {
      return Files.readAllBytes(Paths.get(pdfFile.getAbsolutePath()));
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  static URL toURL(URI uri) {
    try {
      return uri.toURL();
    }
    catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  static byte[] readBytes(URL url) {
    try (InputStream inputStream = url.openStream()) {
      return readBytes(inputStream);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  static byte[] readBytes(InputStream inputStream) {
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
