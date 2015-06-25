package com.codeborne.pdftest;

import org.junit.Test;

import static com.codeborne.pdftest.PDFMatchers.containsText;
import static org.junit.Assert.assertThat;

public class RealWorldExamplesTest {
  @Test
  public void bankStatement() throws Exception {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("statement.pdf"));
    assertThat(pdf, containsText("Выписка"));
    assertThat(pdf, containsText("Период: 18.06.2015 - 18.06.2015"));
    assertThat(pdf, containsText("Счёт: тудашный"));
    assertThat(pdf, containsText("Входящий остаток на 18.06.2015 6.40 RUB"));
    assertThat(pdf, containsText("Дата Плательщик / Получатель Операция Сумма (RUB)"));
    assertThat(pdf, containsText("18.06.2015 Solntsev Andrei \t 40820810590550000146 \t сюда. Интернет-банк -1.00"));
    assertThat(pdf, containsText("18.06.2015 Solntsev Andrei \t 40820810590550000146 \t туда. Интернет-банк 1.00"));
    assertThat(pdf, containsText("Исходящий остаток на 18.06.2015 6.40 RUB"));
    assertThat(pdf, containsText("Поступление 1.00 RUB"));
    assertThat(pdf, containsText("Списание -1.00 RUB"));
  }

  @Test
  public void bankTransaction() throws Exception {
    PDF pdf = new PDF(getClass().getClassLoader().getResource("transaction.pdf"));
    assertThat(pdf, containsText("24.06.2015"));
    assertThat(pdf, containsText("Поступ. в банк плат."));
    assertThat(pdf, containsText("Списано со сч. плат."));
    assertThat(pdf, containsText("0401060"));
    assertThat(pdf, containsText("ПЛАТЕЖНОЕ ПОРУЧЕНИЕ № 338"));
    assertThat(pdf, containsText("Дата"));
    assertThat(pdf, containsText("Вид платежа"));

    assertThat(pdf, containsText("Сумма прописью"));
    assertThat(pdf, containsText("Один рубль 00 копеек"));
    assertThat(pdf, containsText("ИНН"));
    assertThat(pdf, containsText("КПП"));
    assertThat(pdf, containsText("Solntsev Andrei"));
    assertThat(pdf, containsText("Плательщик"));
    assertThat(pdf, containsText("Сч.№"));
    assertThat(pdf, containsText("40820810590550000146"));
    assertThat(pdf, containsText("ПАО \"БАНК \"САНКТ-ПЕТЕРБУРГ\""));

    assertThat(pdf, containsText("Отметки банка"));
    assertThat(pdf, containsText("БИК 044030790"));
    assertThat(pdf, containsText("Исполнен 24.06.2015"));
    assertThat(pdf, containsText("Код операции 169765333614"));
  }
}
