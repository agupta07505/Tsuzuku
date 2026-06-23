# Contributing to Tsuzuku 🌿

Thank you for your interest in contributing to Tsuzuku! Every contribution helps make this habit tracker better for everyone.

## 🏁 Getting Started

1. **Fork** the repository and clone your fork locally.
2. Create a new branch from `main` for your work:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Follow the [build instructions](README.md#-how-to-compile--run) to set up the project.

## 🔧 Development Setup

- **JDK**: Version 17 (Zulu recommended)
- **IDE**: Android Studio Ladybug or later
- **Min SDK**: 24 (Android 7.0)
- **Compose**: 100% Jetpack Compose UI — no XML layouts

## 📋 Contribution Guidelines

### Code Style
- Follow standard [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html).
- Use meaningful variable and function names.
- Keep functions focused and concise.
- Add KDoc comments for public APIs and complex logic.

### Commits
- Write clear, descriptive commit messages.
- Use [Conventional Commits](https://www.conventionalcommits.org/) format when possible:
  ```
  feat: add weekly streak summary view
  fix: resolve notification not firing on Android 14+
  docs: update README with new screenshots
  ```

### Pull Requests
- Keep PRs focused on a single concern.
- Fill out the PR template completely.
- Include screenshots or recordings for any UI changes.
- Ensure the project builds without errors before submitting.
- Link related issues using `Closes #123` or `Fixes #123`.

## 🐛 Reporting Bugs

Use the [Bug Report](https://github.com/agupta07505/Tsuzuku/issues/new?template=bug_report.md) issue template. Include:
- Device model and Android version
- Steps to reproduce
- Expected vs. actual behavior
- Screenshots or screen recordings if applicable

## 💡 Suggesting Features

Use the [Feature Request](https://github.com/agupta07505/Tsuzuku/issues/new?template=feature_request.md) issue template. Describe:
- The problem your feature would solve
- Your proposed solution
- Any alternatives you've considered

## ⭐ Reviewing the App / Leaving Feedback

Use the [App Review / Feedback](https://github.com/agupta07505/Tsuzuku/issues/new?template=app_review.md) issue template. You can share:
- An overall rating of your experience (out of 5 stars)
- Detailed thoughts and reviews of the app
- What features you found most helpful
- Suggestions for general user experience improvements

## 🧪 Testing

- Run existing tests before submitting:
  ```bash
  ./gradlew testDebugUnitTest
  ```
- Add tests for new features when possible.
- Visual regression tests use [Roborazzi](https://github.com/takahirom/roborazzi).

## 🔒 Security

If you discover a security vulnerability, **do not** open a public issue. Instead, follow the instructions in [SECURITY.md](SECURITY.md).

## 📜 License

By contributing to Tsuzuku, you agree that your contributions will be licensed under the [MIT License](LICENSE).

---

Thank you for helping make Tsuzuku better! 🎋
