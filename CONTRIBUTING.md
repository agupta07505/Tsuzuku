# Contributing to Tsuzuku

Thank you for helping improve Tsuzuku. This project is a Kotlin/Jetpack Compose Android app focused on private habit tracking, focus sessions, and a minimal launcher experience.

## Getting Started

1. Fork the repository and clone your fork.
2. Create a focused branch from `main` or `dev`.
3. Build the project locally before making changes.

```bash
git checkout -b feature/your-feature-name
./gradlew assembleDebug
```

On Windows:

```powershell
.\gradlew.bat assembleDebug
```

## Development Setup

- JDK 17
- Android Studio Ladybug or later
- Min SDK 24
- Target SDK 36
- Kotlin and Jetpack Compose
- Material 3 UI
- Room, DataStore, coroutines, Robolectric, and Roborazzi

## Areas of the App

- Habit tracker: habit creation, check-ins, streak calculations, and habit management.
- Insights: stats cards, weekly activity, top habits, and yearly heatmaps.
- Focus mode: timed sessions, phone-position discipline, DND integration, and focus statistics.
- Launcher: optional Android HOME launcher, allowed apps, widgets, quotes, and focus environment settings.
- Settings: themes, custom accent colors, reminders, backup/restore, privacy/about, and destructive reset flows.

## Code Style

- Follow standard Kotlin conventions.
- Prefer the existing Compose, ViewModel, repository, and Room patterns.
- Keep UI state predictable and testable.
- Keep features offline-first unless the change explicitly requires connectivity.
- Avoid committing generated build outputs, secrets, keystores, or personal device data.
- Add short comments only when they explain non-obvious behavior.

## Testing

Run the relevant tests before opening a pull request:

```bash
./gradlew testDebugUnitTest
```

For UI or screenshot-related changes, update or run the Roborazzi/Robolectric tests when applicable.

For launcher and focus changes, manually verify the affected Android permissions or system settings:

- Default launcher selection
- Notification permission on Android 13+
- Do Not Disturb policy access
- Foreground focus-session behavior
- Backup and restore using a local JSON file

## Commit Messages

Clear commit messages are preferred. Conventional Commits are welcome:

```text
feat: add launcher widget toggle
fix: preserve focus session after app backgrounding
docs: update setup instructions
test: add streak calculator coverage
```

## Pull Requests

- Keep the PR focused on one feature, fix, or documentation update.
- Fill out the PR template.
- Include screenshots or recordings for UI changes.
- Mention tested Android versions/devices when behavior depends on system APIs.
- Link related issues with `Closes #123` or `Fixes #123`.
- Update README, assets documentation, or templates when behavior changes.

## Reporting Bugs

Use the [bug report template](.github/ISSUE_TEMPLATE/bug_report.md). Include:

- Device model and Android version
- App version or commit SHA
- Screen or feature affected
- Steps to reproduce
- Expected and actual behavior
- Logs, crash output, screenshots, or recordings when available

## Suggesting Features

Use the [feature request template](.github/ISSUE_TEMPLATE/feature_request.md). Describe:

- The user problem
- The proposed solution
- Which area of the app it affects
- Any privacy, permission, or backup/restore implications

## Security

Do not open public issues for vulnerabilities. Follow [SECURITY.md](SECURITY.md) instead.

## License

By contributing, you agree that your contributions are licensed under the [MIT License](LICENSE).
