# NeuroDetect Frontend Test Suite

Automated frontend tests for the NeuroDetect React application using
**Selenium 4**, **TestNG 7**, and **Cucumber 7** with the Page Object Model.

---

## Prerequisites

| Tool | Version | Download |
|---|---|---|
| Java JDK | 11 or higher | https://adoptium.net |
| Maven | 3.8+ | https://maven.apache.org |
| Google Chrome | Latest | https://www.google.com/chrome |
| Node.js | 18+ | https://nodejs.org |

> **ChromeDriver** is managed automatically by WebDriverManager — you do not
> need to download or configure it manually.

---

## Project Structure

```
neurodetect-tests/
├── pom.xml                          ← Maven dependencies
├── testng-frontend.xml              ← TestNG suite configuration
└── src/test/
    ├── java/com/neurodetect/
    │   ├── pages/                   ← Page Object Model classes
    │   │   ├── NavbarPage.java
    │   │   ├── DashboardPage.java
    │   │   ├── AboutPage.java
    │   │   ├── InputPage.java
    │   │   ├── SupportedDisordersPage.java
    │   │   ├── TeamPage.java
    │   │   └── ContactPage.java
    │   ├── tests/                   ← TestNG test classes
    │   │   ├── SmokeTest.java
    │   │   ├── NavigationTest.java
    │   │   ├── DashboardTest.java
    │   │   ├── AboutPageTest.java
    │   │   ├── InputPageTest.java
    │   │   ├── SupportedDisordersTest.java
    │   │   ├── TeamPageTest.java
    │   │   └── ContactPageTest.java
    │   ├── stepdefinitions/         ← Cucumber step definitions
    │   │   ├── CucumberHooks.java
    │   │   ├── NavigationSteps.java
    │   │   ├── DashboardSteps.java
    │   │   ├── PredictPageSteps.java
    │   │   ├── ContactSteps.java
    │   │   └── DisordersAndTeamSteps.java
    │   ├── runners/
    │   │   └── CucumberTestRunner.java
    │   └── utils/
    │       ├── DriverManager.java   ← WebDriver lifecycle (ThreadLocal)
    │       ├── ConfigReader.java    ← Base URL, browser, headless settings
    │       ├── WaitUtils.java       ← Explicit wait helpers
    │       └── BaseTest.java        ← Parent class for all TestNG tests
    └── resources/
        └── features/               ← Gherkin BDD feature files
            ├── navigation.feature
            ├── dashboard.feature
            ├── predict_page.feature
            ├── supported_disorders.feature
            ├── team_page.feature
            └── contact_form.feature
```

---

## Step 1 — Start the React App

The tests run against the locally running frontend.
Make sure the app is running before executing any tests:

```bash
cd your-neurodetect-project
npm run dev
```

The app should be accessible at **http://localhost:5173**

> The backend (FastAPI) does NOT need to be running.
> These are frontend-only tests.

---

## Step 2 — Fill in the Locators

**This is the only manual step required before running the tests.**

Every Page Object file in `src/test/java/com/neurodetect/pages/` has locators
set to `null` with a `// TODO:` comment. You need to fill these in by inspecting
the live app in Chrome DevTools.

**How to find a locator:**
1. Open http://localhost:5173 in Chrome
2. Right-click the element you want → **Inspect**
3. In DevTools, right-click the highlighted HTML → **Copy** → choose one of:
   - **Copy selector** → use as `By.cssSelector("...")`
   - **Copy XPath**   → use as `By.xpath("...")`

**Example — before:**
```java
private final By navbar = null; // TODO: e.g. By.cssSelector("nav.navbar")
```

**Example — after:**
```java
private final By navbar = By.cssSelector("nav.navbar");
```

**Tips for good locators:**
- Prefer `By.cssSelector` over `By.xpath` — faster and more readable
- Use class names from the CSS files (e.g. `.navbar`, `.patient-section`, `.drop-zone`)
- Avoid locators based on text content for structural elements — use class/id
- For links, `By.linkText("Predict")` or `By.cssSelector("a[href='/analyze']")` both work
- For the hidden file input: `By.cssSelector("input[type='file']")`

---

## Step 3 — Run the Tests

### Run the full suite
```bash
mvn test
```

### Run only smoke tests (quick sanity check)
```bash
mvn test -Dgroups=smoke
```

### Run only the Cucumber BDD tests
```bash
mvn test -Dtest=CucumberTestRunner
```

### Run in headless mode (no browser window)
```bash
mvn test -Dheadless=true
```

### Run with Firefox instead of Chrome
```bash
mvn test -Dbrowser=firefox
```

### Run a specific TestNG test class
```bash
mvn test -Dtest=InputPageTest
mvn test -Dtest=NavigationTest
mvn test -Dtest=ContactPageTest
```

---

## Test Reports

After running, reports are generated in the `target/` folder:

| Report | Location |
|---|---|
| TestNG HTML Report | `target/surefire-reports/index.html` |
| Cucumber HTML Report | `target/cucumber-reports/cucumber.html` |
| Cucumber JSON Report | `target/cucumber-reports/cucumber.json` |
| Cucumber Timeline | `target/cucumber-reports/timeline/` |

Open the HTML files directly in your browser.

---

## Test Coverage Summary

### Smoke Tests (9 tests)
- App loads at localhost:5173
- Page title contains "NeuroDetect"
- Navbar is visible
- All 6 routes load without error (`/`, `/about`, `/analyze`, `/disorders`, `/team`, `/contact`)
- `/result` without state redirects to `/analyze`

### Navigation Tests (14 tests)
- All 6 nav links navigate to correct routes
- Logo click navigates home from any page
- Logo text reads "NeuroDetect"
- Active class is applied to the correct link on each page
- Hamburger button appears on narrow viewports (≤900px)
- Hamburger button opens and closes the menu
- Clicking a link on mobile closes the menu

### Dashboard Tests (14 tests)
- Hero title, description, and buttons visible
- Start Prediction → /analyze, Learn How It Works → /about
- 3 stat cards with correct values
- Workflow section with 5 steps
- 4 disorder cards (Epilepsy, ASD, Alzheimer's, Schizophrenia)
- View Detailed Disorder Profiles → /disorders
- Why EEG section with 4 cards

### About Page Tests (8 tests)
- Page title visible
- Problem Statement section visible with content
- Project Objective section visible
- 6 technology cards
- Architecture diagram with 5 nodes

### Input Page Tests (17 tests)
- Both cards rendered
- Upload blocked for 7 incomplete patient data combinations (@DataProvider)
- Clear Error button works
- .pdf, .txt, .png files rejected
- .csv, .edf, .set files accepted
- File info card shows name, size, timestamp
- Optional phone and remarks fields present

### Supported Disorders Tests (9 tests)
- 4 profile cards rendered
- Each card has Symptoms, Early Detection, and EEG Role sections

### Team Page Tests (7 tests)
- 4 team cards with names, GitHub links, LinkedIn links, avatars

### Contact Page Tests (9 tests)
- Info card and form visible
- Required field attributes present
- Form submission shows success state
- "Send another message" resets the form

### BDD Cucumber Tests (features)
- `navigation.feature` — 12 scenarios
- `dashboard.feature` — 16 scenarios
- `predict_page.feature` — 14 scenarios
- `supported_disorders.feature` — 9 scenarios
- `team_page.feature` — 7 scenarios
- `contact_form.feature` — 6 scenarios

**Total: ~142 test cases across TestNG + Cucumber**

---

## Troubleshooting

**"Element not found" / NoSuchElementException**
→ The locator is null or incorrect. Fill in / fix the locator in the Page Object.

**"Connection refused" at localhost:5173**
→ The React app isn't running. Run `npm run dev` first.

**ChromeDriver version mismatch**
→ WebDriverManager handles this automatically. If it fails, try:
```bash
mvn test -Dwdm.chromeDriverVersion=LATEST
```

**Tests pass but nothing happens visually**
→ You may be running in headless mode. Set `-Dheadless=false` to see the browser.

**Cucumber steps not found (Undefined step)**
→ Check the `glue` path in `CucumberTestRunner.java` matches your package:
`glue = "com.neurodetect.stepdefinitions"`
