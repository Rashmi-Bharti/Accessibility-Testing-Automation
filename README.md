This project contains an **Automated Accessibility Testing Framework** using Selenium and TestNG.

## 📌 Features
- ✅ Automates accessibility tests using axe-core
- ✅ Runs tests on Chrome (headless mode supported)
- ✅ Generates detailed accessibility violation reports

## 🛠️ Setup Instructions
### 🔹 Prerequisites:
- Install **Java 8+**
- Install **Maven**
- Install **Chrome & ChromeDriver**

### 🔹 Clone Repository:

git clone https://github.com/Rashmi-Bharti/Accessibility-Testing-Automation.git
cd AccessibilityTesting


## 🏆 Accessibility Testing Automation 🚀
🔍 Automate Accessibility Testing using Selenium, TestNG, and axe-core to identify WCAG violations and improve web usability.


🚀 Features
✅ Automated Accessibility Scans using axe-core
🔍 Validates ARIA Labels, Contrast, and Screen Reader Compatibility
🖥 Cross-Browser Support (Chrome, Firefox)
📊 Detailed Test Reports in JSON & HTML formats
🛠 Easily Configurable via Maven & TestNG

📂 Project Structure

Accessibility-Testing-Automation/
│── 📂 src
│   ├── 📂 main/
│   │   ├── 📂 java/config/              # Browser setup configurations
│   │   ├── 📂 java/org/apache/maven/    # Main Java files
│   ├── 📂 test/
│   │   ├── 📂 java/tests/               # Test Cases
│   │   ├── 📂 resources/axe.min.js      # JS file for Accessibility Testing
│── 📜 pom.xml                           # Maven Dependencies
│── 📜 README.md                         # Project Documentation


## 🛠 Setup & Installation

1️⃣ Clone the Repository
git clone https://github.com/Rashmi-Bharti/Accessibility-Testing-Automation.git
cd Accessibility-Testing-Automation

2️⃣ Install Dependencies
mvn clean install

3️⃣ Run Accessibility Tests
mvn test

## 📊 Test Reports

📁 Accessibility Violations Logged in:
target/surefire-reports/AmazonAccessibilityTest.json
📑 Detailed HTML Reports:
target/surefire-reports/emailable-report.html

## 📌 Tech Stack
🔹 Java ☕
🔹 Maven 🏗
🔹 Selenium WebDriver 🌐
🔹 TestNG 🛠
🔹 axe-core (Deque's Accessibility Library) 🔍

## 🤝 Contributing
🚀 Want to improve this project? Here's how you can contribute:

🔥 Fork this repository
🛠 Enhance test cases or add new features
📩 Submit a Pull Request
