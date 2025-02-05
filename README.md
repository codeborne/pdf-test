[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codeborne/pdf-test/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.codeborne/pdf-test)
[![Coverage Status](https://coveralls.io/repos/github/codeborne/pdf-test/badge.svg?branch=main)](https://coveralls.io/github/codeborne/pdf-test?branch=main)

# PDF Test
PDF testing library

Be sure that your code generates correct PDF!

## How to use (Hamcrest)

```java
import com.codeborne.pdftest.PDF;
import static com.codeborne.pdftest.PDF.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(new File("src/test/resources/50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
  }
}
```

## How to use (AssertJ)

```java
import com.codeborne.pdftest.PDF;
import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(new File("src/test/resources/50quickideas.pdf"));
    assertThat(pdf).containsExactText("50 Quick Ideas to Improve your User Stories");
  }

  @Test
  public void canUseSoftAssertions() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("minimal.pdf"));

    PdfSoftAssertions softly = new PdfSoftAssertions();
    softly.assertThat(pdf).containsExactText("one");
    softly.assertThat(pdf).containsExactText("two");
    softly.assertThat(pdf).containsExactText("three");
    softly.assertAll();
  }
}
```


## How to start

If you use **Maven**, add the following dependency to pom.xml:

```xml
  <dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>pdf-test</artifactId>
    <version>1.9.2</version>
    <scope>test</scope>
  </dependency>
```

If you use **Gradle**, add the following dependency to build.gradle:

```groovy
  testImplementation 'com.codeborne:pdf-test:1.9.2'
```

## How to contribute

You are welcome to suggest your features and fixes!

Just fork the [pdf-test](https://github.com/codeborne/pdf-test) and create pull request. 
Any contribution is important!

** Become part of open-source community! **

# Thanks

Many thanks to these incredible tools that help us to create open-source software:

![Intellij IDEA](https://cloud.google.com/tools/images/icon_IntelliJIDEA.png)

![YourKit Java profiler](https://selenide.org/images/yourkit.png)

# License
pdf-test is open-source project and distributed under [MIT](http://choosealicense.com/licenses/mit/) license
