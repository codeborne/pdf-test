# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

`com.codeborne:pdf-test` — a small Java library for asserting on the contents of PDF files in tests. It exposes both a Hamcrest matcher API (`PDF.containsText(...)` etc.) and an AssertJ API (`Assertions.assertThat(pdf).containsText(...)`), plus AssertJ soft assertions via `PdfSoftAssertions`. PDF parsing is delegated to Apache PDFBox.

## Common commands

Build and run all checks (the `defaultTasks` are `check publishToMavenLocal`):

```
./gradlew
```

Other useful tasks:

```
./gradlew test                              # run tests only
./gradlew test --tests <FQCN>               # run a single test class
./gradlew test --tests <FQCN>.<method>      # run a single test method
./gradlew jacocoTestReport                  # coverage report at build/reports/jacoco/
./gradlew publishToMavenLocal               # install to ~/.m2 for local consumption
```

Java 17 is required (toolchain pinned in `gradle/compilation.gradle`).

## Architecture

The public surface is intentionally tiny — three packages under `src/main/java/com/codeborne/pdftest`:

- **`PDF`** is the central value object. Its constructors eagerly parse a PDF (from `File`, `URL`, `URI`, `byte[]`, or `InputStream`, optionally with a page range) and freeze the extracted state into `public final` fields: `text`, `numberOfPages`, document-info fields (`author`, `title`, `creator`, ...), and signature fields (`signed`, `signerName`, `signatureTime`). All later assertions read these fields — there is no lazy parsing. Any parse failure is rethrown as `IllegalArgumentException("Invalid PDF file: ...")`. URL reads use a 10 s connect/read timeout.
- **`matchers/`** holds Hamcrest `TypeSafeMatcher<PDF>` implementations, all extending `PDFMatcher`. `PDF` exposes them as static factories (`containsText`, `doesNotContainText`, `containsExactText`, `doesNotContainExactText`, `containsTextCaseInsensitive`, `matchesText`).
- **`assertj/`** mirrors the same checks as an AssertJ `PdfAssert extends AbstractAssert<PdfAssert, PDF>`, with `Assertions.assertThat(PDF)` as the entry point and `PdfSoftAssertions` for soft assertions (uses AssertJ's `proxy(...)`).

**Whitespace handling is the key invariant.** `Spaces.reduce(text)` collapses any run of whitespace (including ` ` non-breaking space and newlines) to a single space and trims. The `containsText` / `containsTextCaseInsensitive` / `matchesText` / `doesNotContainText` paths apply `Spaces.reduce` to BOTH the PDF text and the expected argument(s); the `*Exact*` variants do not. Both API surfaces (Hamcrest matchers and `PdfAssert`) must keep this behavior consistent — when adding a new matcher, decide explicitly whether it is an "exact" or whitespace-normalized variant and apply `Spaces.reduce` accordingly on both sides.

Adding a new check usually means: a new `PDFMatcher` subclass, a static factory on `PDF`, a method on `PdfAssert`, and tests under both `src/test/java/com/codeborne/pdftest/matchers/` and `src/test/java/com/codeborne/pdftest/assertj/`.

## Tests

JUnit 5 (Jupiter). Test PDFs live in `src/test/resources/` (`minimal.pdf`, `50quickideas.pdf`, `transaction.pdf`, `statement.pdf`, `file-with-images.pdf`, two `PO_*.pdf` purchase orders). Reuse these rather than committing new fixtures unless a new edge case really requires one.
