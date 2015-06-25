# PDF Test
PDF testing library

## How to use

```java
import static com.codeborne.pdf-test.PDFMatchers.containsText;
import static org.junit.Assert.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
  }
}
```
