# CI/CD Pipeline Setup - Complete Execution Guide

## Project Ready ✅

All files are prepared. The project contains:

- ✅ Maven pom.xml with all dependencies configured
- ✅ GitHub Actions workflow `.github/workflows/ci.yml`
- ✅ Page Object Model (ApiChainPage.java)
- ✅ Step definitions (api_chain_step.java)
- ✅ Feature files (api_chaining.feature, add_user.feature, etc.)
- ✅ Configuration files (BaseURIConfig.json, UserConfig.json)
- ✅ Allure integration setup
- ✅ Complete README.md
- ✅ SETUP_GUIDE.md with troubleshooting

---

## Execution Steps (Follow in Order)

### ⚠️ Prerequisites

Before proceeding, ensure you have:

1. **Git installed** and configured:
   ```bash
   git --version
   git config --global user.name "Your Name"
   git config --global user.email "your.email@example.com"
   ```

2. **GitHub account** with the repository ready:
   - Repository: https://github.com/rajwaghmare/rest_assure_bdd_cucumber
   - Must be initialized/empty or existing

3. **GitHub authentication ready**:
   - **Option A (SSH - Recommended for future)**:
     ```bash
     ssh -T git@github.com
     ```
   - **Option B (Personal Access Token)**:
     1. Go to: https://github.com/settings/tokens
     2. Create new token "GitHub Actions"
     3. Select scopes: `repo`, `workflow`
     4. Copy token (you'll use this as password during push)

---

### Step 1: Navigate to Project Directory

```bash
cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework'
```

Verify you are in the correct directory:

```bash
pwd
# Should output the project path
ls -la | grep "pom.xml"
# Should show pom.xml
```

---

### Step 2: Check Git Status

```bash
git status
```

Expected output should show:
- Current branch (could be `master`, `main`, or something else)
- Untracked/modified files related to your work (or "nothing to commit" if all pushed before)

---

### Step 3: Stage All Files

```bash
git add .
```

---

### Step 4: View Changes to Commit

```bash
git diff --cached --stat
```

This shows all files that will be committed. Should include:
- `.github/workflows/ci.yml`
- `src/test/java/pages/ApiChainPage.java`
- `src/test/java/stepdefinitions/api_chain_step.java`
- Updated configuration files
- `SETUP_GUIDE.md`
- `push_to_github.sh`

---

### Step 5: Commit Changes

```bash
git commit -m "Initial commit: REST Assured BDD Cucumber Framework with CI/CD pipeline

- Framework: REST Assured + Cucumber + JUnit 5 + Allure
- Page Object Model: ApiChainPage
- API Chaining: Create, Retrieve, Update, Delete operations
- GitHub Actions CI/CD: automated testing + Allure report generation + GitHub Pages deployment
- PR Comments: automatic links to generated Allure reports"
```

---

### Step 6: Ensure Main Branch

```bash
git branch -M main
```

(This renames your current branch to `main`)

---

### Step 7: Add Remote (if not already added)

Check if remote exists:

```bash
git remote -v
```

If `origin` is NOT listed, add it:

```bash
git remote add origin https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git
```

If remote exists but URL is different, update it:

```bash
git remote set-url origin https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git
```

---

### Step 8: Push to GitHub

```bash
git push -u origin main
```

**Authentication prompt:**
- **If using SSH**: Should auto-authenticate (if key is configured)
- **If using HTTPS**: 
  - Username: `rajwaghmare` (or your GitHub username)
  - Password: Paste your Personal Access Token (from Prerequisites)

**Expected output:**
```
Counting objects: ...
Compressing objects: ...
Writing objects: ...
Total X (delta Y), reused Z (delta W)
remote: Create pull request for `main`:
remote:   https://github.com/rajwaghmare/rest_assure_bdd_cucumber/pull/new/main
...
* [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

---

### Step 9: Verify Push on GitHub

1. Go to: https://github.com/rajwaghmare/rest_assure_bdd_cucumber
2. Verify files are visible:
   - `.github/workflows/ci.yml`
   - `src/test/java/`
   - `pom.xml`
   - `README.md`
   - `SETUP_GUIDE.md`

---

### Step 10: Configure GitHub Pages

⚠️ **IMPORTANT: Do this after the first workflow run completes**

1. Go to: **Repository → Settings → Pages**
2. Under "Source", choose: **Deploy from a branch**
3. Select branch: **`allure-pages`** (will auto-create after first run)
   - If not visible yet, trigger a run first (see Step 11)
4. Select folder: `/` (root)
5. Click **Save**

You should see:
```
Your site is published at: https://rajwaghmare.github.io/rest_assure_bdd_cucumber/
```

---

### Step 11: Trigger First Workflow Run

#### Option A: Via Push (Automatic)

If you pushed code with changes in monitored paths:
- Workflow should auto-start

To verify:
1. Go to: **Repository → Actions**
2. Should see "CI" workflow running

#### Option B: Manual Trigger

1. Go to: **Repository → Actions**
2. Click **CI** workflow
3. Click **Run workflow**
4. Click **Run workflow** button
5. Wait for job to complete

---

### Step 12: Monitor Workflow Execution

1. Go to: **Repository → Actions → [Latest Run]**
2. Watch progress:
   - **build**: Test execution + Allure report generation (~2-3 min)
   - **deploy-pages**: Report deployment to `allure-pages` branch (~10-30 sec)

3. Check logs:
   - Click on each step to see output
   - Look for errors in "Run tests (maven)" step

---

### Step 13: Access Allure Report

#### Online (via GitHub Pages)

```
https://rajwaghmare.github.io/rest_assure_bdd_cucumber/
```

#### Offline (via Artifacts)

1. Go to: **Actions → [Latest Run]**
2. Scroll to "Artifacts"
3. Download `allure-report`
4. Extract and open `index.html`

---

### Step 14: Check PR Comments (if push to main was a PR merge)

If you create a pull request:
1. Go to: **Pull requests → [Your PR]**
2. Scroll to comments
3. Should see bot comment with Allure report link

---

## Troubleshooting

### Issue: Push Rejected

**Error**: `fatal: unable to access 'https://github.com/..': The requested URL returned error: 403`

**Solution**:
- Ensure Personal Access Token is used (not GitHub password)
- Token must have `repo` scope
- Re-verify token from https://github.com/settings/tokens

---

### Issue: Workflow Never Starts

**Possible causes**:
1. Pushed to wrong branch (ensure you push to `main`)
2. Changes not in monitored paths (verify `.github/workflows/ci.yml` was pushed)
3. Repo settings disabled Actions

**Solution**:
1. Go to: **Settings → Actions → General**
2. Ensure "Actions permissions" is set to "Allow all actions and reusable workflows"
3. Manually trigger: **Actions → CI → Run workflow**

---

### Issue: Workflow Fails at "Run tests"

**Common causes**:
- API endpoint unreachable
- Test data configuration missing
- Dependencies not resolved

**Solution**:
1. Check test logs: **Actions → build → Run tests (maven)**
2. Scrutinize error message
3. Common fixes:
   ```bash
   # Run locally to debug
   mvn clean test
   ```

---

### Issue: Allure Report Not Deploying

**If deploy-pages job is skipped**:
- Ensure you pushed to `main` (deploy only runs for main/master pushes)
- Or manually trigger: **Actions → CI → Run workflow**

**If Pages branch not created**:
- Create manually:
  ```bash
  git checkout --orphan allure-pages
  git rm -rf .
  echo "# Allure Reports" > README.md
  git add README.md
  git commit -m "Initial commit"
  git push origin allure-pages
  ```

---

## Quick Reference Commands

```bash
# Navigate to project
cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework'

# Configure git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Add remote
git remote add origin https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git

# Stage, commit, push
git add .
git commit -m "Your message"
git branch -M main
git push -u origin main

# Check status
git status

# View logs
git log --oneline -5
```

---

## Files Summary

| File | Purpose |
|------|---------|
| `.github/workflows/ci.yml` | GitHub Actions workflow configuration |
| `src/test/java/pages/ApiChainPage.java` | Page Object Model for API chaining |
| `src/test/java/stepdefinitions/api_chain_step.java` | Cucumber step definitions |
| `src/test/resources/features/api_chaining.feature` | BDD scenarios |
| `src/test/resources/test_data/BaseURIConfig.json` | API endpoints configuration |
| `pom.xml` | Maven project configuration |
| `README.md` | Project overview |
| `SETUP_GUIDE.md` | Detailed setup instructions |
| `push_to_github.sh` | Automated push script |

---

## Support

For issues or questions:
1. Check [SETUP_GUIDE.md](./SETUP_GUIDE.md) troubleshooting section
2. Review workflow logs in GitHub Actions
3. Consult documentation:
   - [GitHub Actions](https://docs.github.com/en/actions)
   - [Allure Reports](https://docs.qameta.io/allure/)
   - [REST Assured](https://rest-assured.io/)

---

## Next Steps After Initial Setup

1. ✅ Push code to GitHub
2. ✅ Verify workflow runs
3. ✅ View Allure reports
4. 📝 Create pull requests (reports will be commented automatically)
5. 🚀 Integrate with your CI/CD pipeline (Jenkins, GitLab CI, etc.)

---

**Happy Testing!** 🎉

Generated: June 16, 2026

