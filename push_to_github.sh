#!/bin/bash

# Script to push REST Assured BDD Cucumber project to GitHub and activate CI/CD

echo "=========================================="
echo "REST Assured BDD Cucumber - GitHub Push"
echo "=========================================="
echo ""

# Step 1: Configure Git User
echo "[1/6] Configuring Git user..."
git config --global user.name "Raj Waghmare"
git config --global user.email "rajwaghmare@example.com"
echo "✓ Git user configured"
echo ""

# Step 2: Verify or add remote
echo "[2/6] Setting up remote repository..."
if git remote | grep -q "^origin$"; then
    echo "✓ Remote 'origin' already exists"
    git remote -v
else
    echo "✓ Adding remote 'origin'..."
    git remote add origin https://github.com/rajwaghmare/rest_assure_bdd_cucumber.git
fi
echo ""

# Step 3: Stage all changes
echo "[3/6] Staging all changes..."
git add .
echo "✓ Files staged for commit"
echo ""

# Step 4: Show status
echo "[4/6] Checking git status..."
git status
echo ""

# Step 5: Commit
echo "[5/6] Creating commit..."
git commit -m "Initial commit: BDD Cucumber API Automation Framework with CI/CD pipeline

- Framework: REST Assured + Cucumber + JUnit 5
- Reporting: Allure Reports
- Page Object Model with ApiChainPage
- Configurable endpoints in BaseURIConfig.json
- GitHub Actions CI/CD pipeline configured
- Allure report deployment to GitHub Pages
- Automatic PR comments with report links"
echo "✓ Changes committed"
echo ""

# Step 6: Ensure main branch
echo "[6/6] Setting up main branch..."
git branch -M main
echo "✓ Branch set to 'main'"
echo ""

# Step 7: Push to GitHub
echo "=========================================="
echo "Ready to push to GitHub!"
echo "=========================================="
echo ""
echo "Execute the following command:"
echo "  git push -u origin main"
echo ""
echo "Note: You will be prompted for authentication:"
echo "  - Use GitHub Personal Access Token (recommended)"
echo "  - Or use SSH key if configured"
echo ""
echo "After push completes:"
echo "  1. Go to GitHub repository Settings → Pages"
echo "  2. Set source branch to 'allure-pages'"
echo "  3. Workflow will auto-create branch after first run"
echo "=========================================="

