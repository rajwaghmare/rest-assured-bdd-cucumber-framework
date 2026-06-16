# ⚡ Quick Push Commands - Copy & Execute

## 🎯 One-Liner Push (For Experienced Users)

```bash
cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework' && \
git config --global user.name "Raj Waghmare" && \
git config --global user.email "rajwaghmare@example.com" && \
git add . && \
git commit -m "Initial commit: REST Assured BDD Cucumber Framework with CI/CD pipeline" && \
git branch -M main && \
git push -u origin main
```

---

## 📋 Step-by-Step Push (For Beginners)

### Command 1: Navigate to Project
```bash
cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework'
```
_Press Enter_

### Command 2: Configure Git (One-time setup)
```bash
git config --global user.name "Raj Waghmare"
git config --global user.email "rajwaghmare@example.com"
```
_Press Enter_

### Command 3: Check Status
```bash
git status
```
_You should see: "On branch main" or "On branch master"_

### Command 4: Stage Files
```bash
git add .
```
_No output means success_

### Command 5: Commit
```bash
git commit -m "Initial commit: REST Assured BDD Cucumber Framework with CI/CD pipeline"
```
_Should show: X files changed, Y insertions..._

### Command 6: Set Main Branch
```bash
git branch -M main
```
_No output means success_

### Command 7: Verify Remote
```bash
git remote -v
```
_Should show: origin  https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git_

_If not shown, run:_
```bash
git remote add origin https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git
```

### Command 8: Push to GitHub
```bash
git push -u origin main
```

**WAIT FOR AUTHENTICATION PROMPT:**
- Username: `rajwaghmare`
- Password: **PASTE YOUR PERSONAL ACCESS TOKEN** (not GitHub password!)

_You should see: Master/Main -> main_

---

## ✅ Post-Push Verification

### Check 1: Verify Files on GitHub
```
Go to: https://github.com/rajwaghmare/rest_assure_bdd_cucumber
Should show pom.xml, src/, .github/, README.md, etc.
```

### Check 2: View Workflow Run
```
Go to: Repository → Actions tab
Should show "CI" workflow running
Wait 2-3 minutes for completion
```

### Check 3: Configure GitHub Pages
```
Go to: Repository → Settings → Pages
Set:
  - Source: Deploy from a branch
  - Branch: allure-pages (wait ~1 min for it to appear)
  - Folder: / (root)
Click: Save
```

### Check 4: View Allure Report
```
After Pages config, wait 2 minutes
Go to: https://rajwaghmare.github.io/rest_assure_bdd_cucumber/
Should see Allure dashboard with test report
```

---

## ⚠️ If Push Fails

### Error: "403 Forbidden"
**Cause**: Using GitHub password instead of token  
**Fix**: Use Personal Access Token (generate at https://github.com/settings/tokens)

### Error: "Repository not found"
**Cause**: Repository doesn't exist or URL is wrong  
**Fix**: Verify repo exists at https://github.com/rajwaghmare/rest_assure_bdd_cucumber

### Error: "fatal: not a git repository"
**Cause**: Not in project directory  
**Fix**: Run: `cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework'`

---

## 🔐 GitHub Personal Access Token Setup

1. Go to: https://github.com/settings/tokens
2. Click: "Generate new token (classic)"
3. Fill:
   - Token name: `GitHub Actions`
   - Expiration: 90 days
   - Scopes: Select `repo` + `workflow`
4. Click: "Generate token"
5. **COPY THE TOKEN** (you won't see it again)
6. During git push, paste this token as password

---

## ✨ Success Indicators

### Successful Push Should Show:
```
Counting objects: ...
Compressing objects: ...
Writing objects: ...
remote: ... Resolving deltas
* [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

### Successful Workflow Should Show:
- Green checkmarks (✓) on both `build` and `deploy-pages` jobs
- Elapsed time: ~3-4 minutes

### Successful Pages Deployment Should Show:
```
Your site is published at: https://rajwaghmare.github.io/rest_assure_bdd_cucumber/
```

---

## 📱 Important URLs After Deployment

| Resource | URL |
|----------|-----|
| Repository | https://github.com/rajwaghmare/rest_assure_bdd_cucumber |
| Allure Report | https://rajwaghmare.github.io/rest_assure_bdd_cucumber/ |
| GitHub Actions | https://github.com/rajwaghmare/rest_assure_bdd_cucumber/actions |
| Settings | https://github.com/rajwaghmare/rest_assure_bdd_cucumber/settings |
| Pages Settings | https://github.com/rajwaghmare/rest_assure_bdd_cucumber/settings/pages |

---

## 📖 Full Documentation

- **SETUP_GUIDE.md** - Detailed GitHub configuration
- **CI_CD_EXECUTION_GUIDE.md** - Step-by-step with explanations
- **FINAL_DEPLOYMENT_CHECKLIST.md** - Pre-push verification
- **README_CI_CD_SUMMARY.md** - Features overview
- **README.md** - Project documentation

---

## 🎯 TL;DR (For Impatient Users)

```bash
# Copy entire block and paste in terminal:
cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework' && git add . && git commit -m "Initial commit: BDD Cucumber with CI/CD" && git branch -M main && git push -u origin main
```

When prompted:
- Username: `rajwaghmare`
- Password: *Paste your Personal Access Token*

Then:
1. Wait 3-4 minutes
2. Go to Settings → Pages
3. Set source to `allure-pages` branch
4. Visit: https://rajwaghmare.github.io/rest_assure_bdd_cucumber/

✅ Done!

---

Generated: June 16, 2026

