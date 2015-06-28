# PDF Test
PDF testing library

## How to use

```java
import static com.codeborne.pdftest.PDFMatchers.containsText;
import static org.junit.Assert.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
  }
}
```


## How to start

If you use **Maven**, add the following dependency to pom.xml:

```xml
  <dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>pdf-test</artifactId>
    <version>1.2</version>
  </dependency>
```

If you use **Gradle**, add the following dependency to build.gradle:

```groovy
  testCompile 'com.codeborne:pdf-test:1.1'
```

## How to contribute

You are welcome to suggest your features and fixes!

Just fork the [pdf-test](https://github.com/codeborne/pdf-test) and create pull request. 
Any contribution is important!

Become part of open-source community!
