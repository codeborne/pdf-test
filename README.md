# pdfest
PDF testing library

## How to use

```java
import static com.codeborne.pdfest.PDFMatchers.containsText;
import static org.junit.Assert.assertThat;

public class PDFContainsTextTest {
  @Test
  public void canAssertThatPdfContainsText() throws IOException {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("50quickideas.pdf"));
    assertThat(pdf, containsText("50 Quick Ideas to Improve your User Stories"));
  }
}
```
