# 📘 Learning Management System (LMS)

A modern **JavaFX-based Learning Management System** designed to manage users, course data, and authentication workflows such as **email verification**, **OTP-based password reset**, and **secure login**.  
This project demonstrates clean MVC architecture, Java OOP concepts, and real-world UI design with FXML.

---

## 🚀 Features

- 🔐 **User Authentication**
  - Login / Logout functionality  
  - Forgot Password with Email Verification & OTP  
  - Password Reset

- 📩 **Email Verification**
  - SMTP-based OTP sending (via Gmail SMTP)
  - Secure OTP validation flow

- 🧩 **Modular Architecture**
  - MVC pattern (Model–View–Controller)
  - Reusable and maintainable structure

- 🎨 **Modern UI with JavaFX**
  - Responsive layouts using `AnchorPane`, `VBox`, and `HBox`
  - Consistent color palette and UX across forms

- 🧰 **Tools & Libraries**
  - JavaFX for GUI  
  - Jakarta Mail (for email sending)  
  - Scene Builder (FXML design)  

---

## 🧱 Project Structure

src/
├── com/pcl/lms/
│ ├── controller/ # All JavaFX controllers
│ ├── model/ # Data models (User, etc.)
│ ├── DB/ # Mock database / data access layer
│ ├── util/tools/ # Utility classes (VerificationCodeGenerator)
│ ├── env/ # Environment configs (ignored from git)
│ └── view/ # All FXML UI layouts


---

## ⚙️ How to Run

1. Clone this repository  
   ```bash
   git clone https://github.com/<your-username>/<your-repo-name>.git
   cd <your-repo-name>
2. Open the project in your IDE
   (Recommended: IntelliJ IDEA or Eclipse with JavaFX support)
3. Configure JavaFX SDK
5. Run the AppInitializer.java file

---

🔧 Environment Setup
  - To send emails using Gmail SMTP:
      - Enable Less Secure Apps or use an App Password
      - Add your credentials safely (not in public repo)
    
       private final String fromEmail = "your-email@gmail.com";
       private final String password = "your-app-password";

🧠 Concepts Demonstrated

  - Object-Oriented Programming (Encapsulation, Abstraction, Inheritance)
  - MVC Design Pattern
  - JavaFX Scene Navigation
  - Form Validation & User Feedback
  - Email Integration with Jakarta Mail API

💼 Ideal For

  - This project was developed as part of a portfolio and internship preparation, showcasing:
  - Clean JavaFX development
  - Good software architecture practices
  - Real-world authentication features

🧑‍💻 Author

  Daham Sandaruwan
  📍 Sri Lanka
  💼 Aspiring Java & MERN Full Stack Developer
  🔗 LinkedIn
   | GitHub
    
