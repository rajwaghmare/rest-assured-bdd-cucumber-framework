#!/bin/bash

# Complete Push Script - Adds ALL files to GitHub

cd '/Volumes/Devil'\''s Macspace/Automation Workspace/API Automation Workspace/bdd_cucumber_api_automation_framework/bdd_cucumber_api_automation_framework' || exit 1

echo "=========================================="
echo "Complete GitHub Push - ALL FILES"
echo "=========================================="
echo ""

# Step 1: Verify we're in the right directory
echo "[1/5] Verifying project directory..."
if [ ! -f "pom.xml" ]; then
    echo "❌ ERROR: pom.xml not found! Wrong directory?"
    exit 1
fi
echo "✓ Project directory verified"
echo ""

# Step 2: Show current status
echo "[2/5] Current git status..."
git status --short | head -20
echo ""

# Step 3: Add ALL files (force add everything)
echo "[3/5] Adding ALL files to git..."
git add . -A
git add pom.xml
git add README.md
git add src/
git add .github/
echo "✓ All files staged"
echo ""

# Step 4: Show what will be committed
echo "[4/5] Files to be committed:"
git diff --cached --name-only | head -30
echo ""

# Step 5: Commit and push
echo "[5/5] Committing and pushing to GitHub..."
git commit -m "Complete project push: Add pom.xml, all source code, test files, and CI/CD pipeline

Include:
- pom.xml with all Maven dependencies
- All step definitions (add_user_step, GetUserStepDefinition, update_user_step, api_chain_step)
- Page Object Model (api_chain_page)
- All test utilities (BaseURIConfigJsonReader, SetHeaderConfig, UserPayloadReader)
- Test runners (TestRunner.java)
- All feature files (api_chaining.feature, add_user.feature, GetUserDetails.feature, update_user.feature)
- Test data & payloads (BaseURIConfig.json, UserConfig.json)
- GitHub Actions workflow (.github/workflows/ci.yml)
- Comprehensive documentation and setup guides"

echo ""
echo "Pushing to GitHub..."
git push -u origin main --force --verbose

echo ""
echo "=========================================="
echo "✓ Push Complete!"
echo "=========================================="
echo ""
echo "Next steps:"
echo "1. Go to: https://github.com/rajwaghmare/rest_assure_bdd_cucumber"
echo "2. Check the Code tab (files should now be visible)"
echo "3. Go to Actions tab (workflow should start automatically)"
echo "4. Wait 3-4 minutes for tests to complete"
echo "5. Configure GitHub Pages (Settings → Pages → Source: allure-pages)"
echo ""

