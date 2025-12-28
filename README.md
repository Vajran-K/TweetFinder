
---

How to Run Locally
Prerequisites

Java JDK 8+

Apache Maven

Check:

java -version
mvn -version

Steps
1. Clone the repo
git clone https://github.com/YOUR_USERNAME/rss-notifier.git
cd rss-notifier/demo

2. Create config file

Go to:

src/main/resources/


Create:

application.properties


Copy contents from:

application.properties.example


Fill in your values (RSS URL, email, app password).

3. Build the project
mvn clean compile

4. Run the app
mvn exec:java "-Dexec.mainClass=com.example.App"

Notes

application.properties is not committed (contains secrets)

Uses Gmail App Password, not your normal password