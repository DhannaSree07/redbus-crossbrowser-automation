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
5. 
 <img width="1695" height="852" alt="image" src="https://github.com/user-attachments/assets/2d18384e-2091-4198-bf28-893a7a2c5138" />

6. Log details with screenshot in **ExtentReport**
<img width="1892" height="889" alt="image" src="https://github.com/user-attachments/assets/1f5d4378-26c2-48a0-8537-81f31f85423f" />

<img width="1894" height="524" alt="image" src="https://github.com/user-attachments/assets/2ebbf264-4157-4779-833d-6b7237b50e81" />

<img width="1888" height="480" alt="image" src="https://github.com/user-attachments/assets/8e4993e8-d028-43a3-b098-c83b0f9b28f0" />

<img width="1909" height="889" alt="image" src="https://github.com/user-attachments/assets/35787f6a-f89d-436b-8e7b-b5e8ff6b62f4" />

<img width="1693" height="502" alt="image" src="https://github.com/user-attachments/assets/1697fd19-4bcf-4e7a-a29b-d4d8c2cee1d2" />

---

## 🔁 CI/CD with Jenkins

- 🏗️ Pipeline-ready via `Jenkinsfile`
- 🔄 Browser is selected dynamically (`chrome` & `edge`)
- 📧 Failure triggers email with build details
- 📊 HTML reports published as **post-build action**
- 
<img width="1914" height="585" alt="image" src="https://github.com/user-attachments/assets/5928a66d-7471-4c8f-aba4-58d23083e110" />


---

📂 GitHub Repository
🔗 https://github.com/DhannaSree07/redbus-crossbrowser-automation

🙋‍♀️ Author
Dhanna Sree
💼 Automation Test Engineer
🔗 LinkedIn Profile

