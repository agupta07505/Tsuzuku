# Tsuzuku 🌿 — Off-Grid Daily Habits & Streak Engine

Tsuzuku (続ける — "to continue" or "to keep going") is a beautiful, offline-first, private-by-default habit tracker and streak engine built with modern Android paradigms.

Designed for distraction-free habit building, Tsuzuku features an elegant design, a dynamic theme switcher, local-only data persistence, and permanent notification check-ins to keep you motivated.

---

## ✨ Key Features

- **🎯 Streak Tracker & Habits Engine**: Plan, log, and maintain daily, weekly, or custom frequency habits with high-fidelity analytics and active visual trackers.
- **⚡ Offline-First Sandboxed Storage**: Zero tracking tags, zero analytics SDKs, and zero cloud uploads. Your progress resides strictly on your physical device.
- **🎨 M3 Japanese Slate & Botanical Themes**: Enjoy visually appealing, high-contrast dark themes ("Tsuzuku Green" and "Tsuzuku Blue") styled with responsive Material Design 3 layouts and spacious natural margins.
- **🔔 Resilient Habit Alarms**: Native low-draw local reminders help you check in with your goals on time.
- **✨ Micro-Reminders & Japanese Mantras**: Receive ambient inspiration quotes dynamically rendered directly into low-priority on-screen notifications.
- **📦 Data Portability**: Securely import or export your tracking statistics and database backups locally in standard JSON format whenever you like.

---

## 🛠️ The Tech Stack

- **Framework**: [Jetpack Compose](https://developer.android.com/compose) (100% Kotlin-based interactive UI)
- **Design Language**: [Material Design 3 (M3)](https://m3.material.io/)
- **Local Persistence**: State-managed asynchronous Room database
- **Theme Support**: Centralized theme palettes (including light, dark, emerald green, and deep aqua blue, customizable via settings)
- **Background Tasks**: Resilient AlarmManager scheduling with custom BroadcastReceivers

---

## 📦 How to Compile & Run

You can build the app directly using standard Gradle tasks.

### Local Development Setup

Ensure you have **JDK 17** set up in your local system environment.

1. **Clone the Repository**:
   ```bash
   git clone <your-repo-url>
   cd tsuzuku
   ```

2. **Grant Executable Permissions override**:
   ```bash
   chmod +x gradlew
   ```

3. **Build the Debug APK**:
   ```bash
   ./gradlew assembleDebug
   ```
   The compiled APK will be generated at:
   `app/build/outputs/apk/debug/app-debug.apk`

---

## 🚀 GitHub Actions CI/CD Pipeline

We have configured a fully automated GitHub Actions workflow inside `.github/workflows/android.yml`.

Whenever you **push** your commits to the `main` or `master` branches, or open a **pull request**, the workflow will automatically:
1. Verify code formatting and compile the project using **JDK 17 (Zulu distribution)**.
2. Gracefully restore the local security configuration (decoding the `debug.keystore.base64` if available).
3. Build and optimize a **Debug APK**.
4. Pack and upload the final compiled `.apk` as a downloadable GitHub actions build artifact named **`tsuzuku-debug-apk`**.

---

## 💡 Architecture Insights

### Where are the Daily Quotes (Mantras) fetched from?
Tsuzuku prioritizes speed, privacy, and full offline autonomy. Quotes are **not fetched from any internet API**. They are securely stored locally inside the application binary under **`app/src/main/java/com/example/notification/HabitNotificationHelper.kt`** inside a static structure:

```kotlin
val mantras = listOf(
    QuotePair("Consistency builds strength.", "続けることが、力になる。"),
    QuotePair("Small steps every day, big change always.", "毎日の小さな一歩が、大きな変化を生む。"),
    QuotePair("Fall seven times, stand up eight.", "七転び八起き。"),
    QuotePair("With patience, the grass becomes milk.", "忍耐があれば、草もミルクになる。"),
    ...
)
```

You can easily customize, append, or modify these inspirational pairs straight from this Kotlin class to suit your personal habit goals or add custom phrases!
