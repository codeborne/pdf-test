# PDF Test
PDF testing library

Be sure that your code generates correct PDF!

## How to use

```java
import com.codeborne.pdftest.PDF;
import static com.codeborne.pdftest.PDF.*;
import static org.junit.Assert.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(new File("src/test/resources/50quickideas.pdf"));
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
    <version>1.3</version>
  </dependency>
```

If you use **Gradle**, add the following dependency to build.gradle:

```groovy
  testCompile 'com.codeborne:pdf-test:1.3'
```

## How to contribute

You are welcome to suggest your features and fixes!

Just fork the [pdf-test](https://github.com/codeborne/pdf-test) and create pull request. 
Any contribution is important!

Become part of open-source community!
