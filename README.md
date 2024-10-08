# 🎉 Quiz App

## 📜 Overview
**Quiz App** is an engaging Android application that offers users an immersive experience to test their knowledge across a variety of topics. By dynamically fetching questions from the **Open Trivia Database API**, the app delivers real-time quizzes on various subjects, complete with a **30-second timer** for each question. It manages user scores, provides detailed feedback, and ensures a smooth experience with intelligent handling of Internet connection issues.

## 🌟 Features
- **🌐 Real-Time API Integration**: Fetches quiz questions dynamically from the Open Trivia Database API.
- **📚 Category & Difficulty Selection**: Choose from categories such as General Knowledge, Science, History, Sports, and select difficulty levels (Easy, Medium, Hard).
- **❓ Multiple Choice Questions**: Each question comes with multiple answer options.
- **⏳ Timer for Each Question**: A **30-second timer** displays for each question. The app automatically proceeds to the next question once time expires.
- **📊 Score Calculation**: Tracks correct answers and presents a detailed score summary at the end.
- **📝 Feedback**: Post-quiz feedback includes correct and incorrect answers along with the final score.
- **🎨 User Interface**: A clean, intuitive UI featuring a **Bottom Navigation Bar** for easy access to Home, Categories, and Settings.
- **🔌 Internet Connection Management**: Seamlessly handles connection status with notifications and pauses the quiz when the Internet is lost, ensuring smooth gameplay.

## 📱 App Screens
- **🏠 Splash Screen**: Welcoming screen with the option to start a new quiz.
- **📖 Category Selection Screen**: Displays the available quiz categories and difficulty levels.
- **🎚️ Difficulty Selection Screen**: Allows users to choose their preferred difficulty level (Easy, Medium, Hard) for the quiz.
- **🕹️ Quiz Screen**: Presents the question, multiple-choice answers, and a 30-second timer.
- **📈 Result Screen**: Shows a detailed performance summary, including the number of correct and incorrect answers and the final score.


## 💻 Tech Stack
- **🛠️ Language**: Kotlin
- **🏗️ Architecture**: MVVM (Model-View-ViewModel)
- **🌍 API Integration**: Open Trivia Database API
- **⚙️ Asynchronous Operations**: Kotlin Coroutines for smooth API calls and background processing
- **📱 UI Components**: Timer, Scoreboard
- **📶 Internet Connection Handling**: Monitors and manages connection issues in real time.

## 📋 Requirements
- **📱 Android SDK**: Version 21 or higher.
- **🌐 Internet Connection**: Needed to fetch quiz questions in real-time.
- **📏 Compatibility**: Works across various Android versions and screen sizes.

## 📸 Screenshots
Here are some snapshots of the **Quiz App** in action:

<div style="text-align: center;">

| Splash Screen | Category Selection | Difficulty Selection | Quiz Screen |
|---------------|--------------------|---------------------|-------------|
| <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/7f3089d1-7983-4d02-9ef4-475a0efb9ff4" width="200" height="400"/></div> | <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/71529425-42f6-4fa6-a076-64770e6f3574" width="200" height="400"/></div> | <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/8fe2bfae-b19e-45be-bfa9-1b40e4d7f154" width="200" height="400"/></div> | <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/94eea194-c550-4c4d-8804-7237f103b25b" width="200" height="400"/></div> |

| Winner Screen | Lose Screen | No Internet Connection |
|---------------|-------------|-----------------------|
| <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/bd869066-b280-42f5-9a13-da6796db6c22" width="200" height="400"/></div> | <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/2cfcc127-98b8-4501-b892-ccd7a4d4d238" width="200" height="400"/></div> | <div style="text-align: center;"><img src="https://github.com/user-attachments/assets/518313cb-8165-43ed-b4a8-f46ebd20adfa" width="200" height="400"/></div> |

</div>


## 🎓 Conclusion
The **Quiz App** provides a fun, dynamic, and interactive way for users to test their knowledge across various topics. By integrating the **Open Trivia Database API** and utilizing **Kotlin Coroutines** for seamless asynchronous operations, the app ensures a smooth, real-time quiz experience.

With its **MVVM architecture**, intuitive user interface, and intelligent Internet connection management, the app delivers efficient performance on a wide range of Android devices. Whether users are tackling general knowledge questions or diving into specific categories like science or history, the **Quiz App** offers an engaging, uninterrupted experience for all quiz enthusiasts.

🎊 **Join us in the quest for knowledge!** 🎊
