[![Build Status](https://travis-ci.org/codeborne/pdf-test.svg?branch=master)](https://travis-ci.org/codeborne/pdf-test)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codeborne/pdf-test/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.codeborne/pdf-test)
[![Coverage Status](https://coveralls.io/repos/github/codeborne/pdf-test/badge.svg?branch=master)](https://coveralls.io/github/codeborne/pdf-test?branch=master)

# PDF Test
PDF testing library

Be sure that your code generates correct PDF!

## How to use

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


## How to start

If you use **Maven**, add the following dependency to pom.xml:

```xml
  <dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>pdf-test</artifactId>
    <version>1.5.2</version>
  </dependency>
```

If you use **Gradle**, add the following dependency to build.gradle:

```groovy
  testCompile 'com.codeborne:pdf-test:1.5.2'
```

## How to contribute

You are welcome to suggest your features and fixes!

Just fork the [pdf-test](https://github.com/codeborne/pdf-test) and create pull request. 
Any contribution is important!

** Become part of open-source community! **

# Thanks

Many thanks to these incredible tools that help us creating open-source software:

![Intellij IDEA](http://store.softline.ru/uploads/resizer/allsoft_2231598/2231598_Scale_120x120.png)

![YourKit Java profiler](http://selenide.org/images/yourkit.png)

# License
pdf-test is open-source project and distributed under [MIT](http://choosealicense.com/licenses/mit/) license
