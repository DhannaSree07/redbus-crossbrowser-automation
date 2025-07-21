# 🚌 Redbus Cross-Browser Automation Framework

This is a Selenium-based automation framework built to test Redbus web flows across multiple browsers. It integrates modern tools like TestNG, Cucumber, ExtentReports, and Jenkins to simulate a real-world automation setup.

---

## 🔧 Tech Stack

- **Language**: Java
- **UI Automation**: Selenium WebDriver
- **Test Frameworks**: TestNG + Cucumber
- **Build Tool**: Maven
- **Reporting**: ExtentReports (with screenshots)
- **CI/CD**: Jenkins
- **Browser Support**: Chrome, Edge, Firefox
- **Version Control**: Git + GitHub

---
## 🧠 Features That Impress

- ✅ **Hybrid TestNG + Cucumber** structure  
- 🌐 **Cross-browser testing / Parallel execution support** with runtime parameters  
- 📸 Screenshot capture for every step — both **PASS** and **FAIL**  
- 📊 Separate browser-wise **HTML ExtentReports** integration 
- 💥 Dynamic logs like:
  > `✔ The best bus is selected for your travel at: Rs. 450 by VRL Travels at 7:15 PM`
- ✅ Clean folder structure and reusable methods
- 📁 **Jenkinsfile included** — just push & build 🚀  
- 💼 GitHub-ready structure — ignore noisy folders, keep only what matters!

---

## 📌 Project Overview

This project is a **real-time automation framework** built to simulate a user journey on [Redbus.in](https://www.redbus.in) — one of India’s leading bus booking platforms.

✅ Searching buses  
✅ Applying filters (ratings, time, A/C buses)  
✅ Selecting the **lowest-priced best option**  
✅ Capturing screenshots on every step  
✅ Generating stunning **ExtentReports with step-wise logs and screenshots**  

It’s fully integrated with **Jenkins Pipeline**, **supports cross-browser execution** (Chrome & Edge), and includes **email notifications** on failure — making it **CI/CD ready**!

---

## 💡 How it works

1. Open [Redbus.in](https://www.redbus.in)  
2. Enter source/destination, select date  
3. Filter based on:
   - ✅ Ratings > 3
   - ✅ Type contains “A/C”
   - ✅ Departure between 3PM and 10PM
4. Get lowest price among filtered options  
5. Log details with screenshot in **ExtentReport**

---

## 🔁 CI/CD with Jenkins

- 🏗️ Pipeline-ready via `Jenkinsfile`
- 🔄 Browser is selected dynamically (`chrome` & `edge`)
- 📧 Failure triggers email with build details
- 📊 HTML reports published as **post-build action**

---

📂 GitHub Repository
🔗 https://github.com/DhannaSree07/redbus-crossbrowser-automation

🙋‍♀️ Author
Dhanna Sree
💼 Automation Test Engineer
🔗 LinkedIn Profile

