
---

## ▶️ How to Run

### Prerequisites
- Java 17 or higher
- Maven installed and added to PATH
- Internet connection (for RSS feed access)
- Email account with SMTP access (e.g., Gmail App Password)

### Steps

```bash
cd demo
mvn clean compile
mvn exec:java "-Dexec.mainClass=com.example.App"
