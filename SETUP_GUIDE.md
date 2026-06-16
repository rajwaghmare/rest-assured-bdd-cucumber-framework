# GitHub Actions CI/CD Setup Guide

This guide walks through setting up the CI/CD pipeline for the REST Assured BDD Cucumber Framework.

---

## Table of Contents

1. [Push Code to GitHub](#push-code-to-github)
2. [Configure GitHub Pages](#configure-github-pages)
3. [Verify Workflow Execution](#verify-workflow-execution)
4. [Accessing Allure Reports](#accessing-allure-reports)
5. [Troubleshooting](#troubleshooting)

---

## Push Code to GitHub

### Step 1: Configure Git User (if not done)

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Step 2: Add Remote Repository

From the project root directory:

```bash
git remote add origin https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git
```

Or if the remote already exists, verify it:

```bash
git remote -v
```

Expected output:
```
origin  https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git (fetch)
origin  https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git (push)
```

### Step 3: Stage All Changes

```bash
git add .
```

### Step 4: Commit All Changes

```bash
git commit -m "Initial commit: BDD Cucumber API Automation Framework with CI/CD pipeline"
```

### Step 5: Create or Checkout Main Branch

```bash
git branch -M main
```

(This renames the current branch to `main` if it isn't already)

### Step 6: Push to GitHub

```bash
git push -u origin main
```

You will be prompted for authentication. Use one of:
- **GitHub Token** (recommended): Generate at https://github.com/settings/tokens (Personal access tokens)
  - Scopes needed: `repo`, `workflow`
- **SSH Key** (if configured): No prompt needed

---

## Configure GitHub Pages

### Step 1: Enable GitHub Pages Publishing

1. Go to: **GitHub Repository → Settings → Pages**
2. Under "Source", select: **Deploy from a branch**
3. Select branch: **`allure-pages`** (this is where the workflow publishes reports)
4. Select folder: `/` (root)
5. Click **Save**

_Note: The `allure-pages` branch will be auto-created after the first workflow run._

### Step 2: Verify Pages is Publishing

After a successful workflow run:
1. Go to **Settings → Pages**
2. You should see: "Your site is published at: `https://<username>.github.io/rest_assure_bdd_cucumber/`"

---

## Verify Workflow Execution

### Step 1: Trigger a Workflow Run

The workflow is configured to run on:
- **Push** to `main` or `master` branch (and tags matching `v*` or `release/*`)
- **Pull requests** to `main` or `master`
- **Manual trigger** (via `workflow_dispatch`)

To manually trigger:

1. Go to: **GitHub Repository → Actions → CI → Run workflow → Run workflow**
2. Click the green "Run workflow" button

### Step 2: Monitor Workflow Progress

1. Go to: **GitHub Repository → Actions**
2. Click on the latest workflow run
3. Watch the build progress:
   - **Build job**: Runs tests, generates Allure report, uploads artifacts
   - **Deploy job**: Deploys report to `allure-pages` branch, posts PR comment (if PR run)

### Step 3: Check Workflow Logs

Click on each job/step to view logs:
- "Run tests (maven)" — test execution output
- "Generate Allure report (maven plugin)" — report generation
- "Deploy Allure report to custom branch" — pages deployment
- "Comment PR with Allure report URL" — PR comment posting

---

## Accessing Allure Reports

### From GitHub Pages (Published Site)

Once workflow completes and Pages is configured:

```
https://<username>.github.io/rest_assure_bdd_cucumber/
```

Example:
```
https://rajwaghmare.github.io/rest_assure_bdd_cucumber/
```

### From GitHub Artifacts (Quick Access)

If Pages is not immediately available or for specific run artifacts:

1. Go to: **GitHub Repository → Actions → [Latest Run Name]**
2. Scroll to "Artifacts" section at bottom
3. Download:
   - `allure-report` — Static HTML report
   - `allure-results` — Raw Allure results (XML/JSON)
4. Extract and open locally:
   ```bash
   unzip allure-report.zip
   open allure-report/index.html
   ```

### PR Comments with Report Link

When the workflow runs for a **pull request**:
- A comment is automatically posted on the PR with the link to the Allure report
- Message format:
  ```
  Allure report for this PR has been published.
  You can view the latest report at:
  https://rajwaghmare.github.io/rest_assure_bdd_cucumber/
  ```

---

## Workflow Configuration Details

### Trigger Conditions

The workflow **only runs** when certain files change (path filtering):

```yaml
paths:
  - 'src/**'            # Test source code
  - 'pom.xml'           # Dependencies
  - 'src/test/resources/**'  # Test resources, payloads, configs
  - 'README.md'         # Documentation
  - '.github/**'        # Workflow files
```

**Example**: If you update `.gitignore` alone, the workflow will NOT run. Add any path matching the above list to trigger it.

### Trigger on Tags

Workflow also runs on tags matching:
- `v*` (e.g., `v1.0.0`, `v2.1.0`)
- `release/*` (e.g., `release/1.0`)

To trigger via tag:

```bash
git tag v1.0.0
git push origin v1.0.0
```

### Jobs

**Build Job:**
- Runs on Ubuntu latest
- Java 21 with Temurin distribution
- Maven cache for faster builds
- Executes: `mvn -B clean test`
- Generates report: `mvn -B allure:report`
- Uploads artifacts: `target/site/allure`, `target/allure-results`

**Deploy Job:**
- Depends on Build job (runs after Build completes)
- Publishes to branch: `allure-pages`
- Posts PR comment if run is for a pull request
- URL in comment: `https://<owner>.github.io/<repo>/`

---

## Troubleshooting

### Issue: Build Job Fails (Tests Fail)

1. **Check test logs** in GitHub Actions:
   - Go to **Actions → [Run] → build → Run tests (maven)**
   - Scroll through output to find failure message

2. **Run locally to debug**:
   ```bash
   mvn clean test
   ```

3. **Common issues**:
   - API endpoint is down (check test data in `src/test/resources/test_data/BaseURIConfig.json`)
   - Wrong test configuration (verify `src/test/resources/cucumber.properties`)
   - Missing dependencies (run `mvn clean install`)

### Issue: Pages Branch Not Created

1. **Ensure Deploy job runs**:
   - The deploy job only runs for:
     - Pull requests, OR
     - Pushes to `main`/`master` branches
   
2. **Manually create branch** (if needed):
   ```bash
   git checkout --orphan allure-pages
   git rm -rf .
   echo "# Allure Reports" > README.md
   git add README.md
   git commit -m "Initial commit"
   git push origin allure-pages
   ```

3. **Then enable Pages** (see [Configure GitHub Pages](#configure-github-pages))

### Issue: Pages Not Showing Report

1. **Check Pages settings** are correct (Settings → Pages)
2. **Wait a few minutes** for GitHub Pages to rebuild
3. **Clear browser cache** (Ctrl+Shift+Delete / Cmd+Shift+Delete)
4. **Check artifact instead**:
   - Go to Actions run → Download `allure-report` artifact
   - Extract and open locally

### Issue: PR Comment Not Posted

1. **Ensure it's a PR run** (workflow only comments on PRs, not pushes)
2. **Check `GITHUB_TOKEN` permissions**:
   - Go to **Settings → Actions → General → Permissions**
   - Ensure "Read and write permissions" is selected (or "Default permissions")
3. **Check workflow logs** for "Comment PR with Allure report URL" step

### Issue: Test Configuration Not Found

If you see "Failed to read the request body from the JSON file" or similar:

1. **Verify config file exists**:
   ```
   src/test/resources/test_data/BaseURIConfig.json
   src/test/resources/payloads/UserConfig.json
   ```

2. **Check file content**:
   ```bash
   cat src/test/resources/test_data/BaseURIConfig.json
   ```

3. **Ensure paths in utility classes match** (check `BaseURIConfigJsonReader.java`, `UserPayloadReader.java`)

---

## Next Steps

1. **Push the code** (Steps in [Push Code to GitHub](#push-code-to-github))
2. **Enable GitHub Pages** (Steps in [Configure GitHub Pages](#configure-github-pages))
3. **Trigger a run** manually or wait for a push/PR
4. **View reports** at the GitHub Pages URL or via artifacts

---

## Support & Resources

- **GitHub Actions Docs**: https://docs.github.com/en/actions
- **Allure Documentation**: https://docs.qameta.io/allure/
- **REST Assured Docs**: https://rest-assured.io/
- **Cucumber Docs**: https://cucumber.io/docs/gherkin/

---

**Happy Testing!** 🚀

