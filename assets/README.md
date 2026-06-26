# Assets

This directory contains static project assets used by the README, GitHub previews, release notes, and store/listing material.

## Structure

```text
assets/
|-- icons/         # Logo and branding assets
`-- screenshots/   # App preview screenshots
```

## Screenshots

Screenshots in `assets/screenshots/` are referenced by the root README.

| Filename | Content |
|----------|---------|
| `01_home.jpg` | Home/tracker entry screen |
| `02_habit_tracker.jpg` | Habit tracker with habit and mantra content |
| `03_create_streak.jpg` | Create habit/streak flow |
| `04_insights.png` | Insights, stats, and heatmap view |
| `05_settings.png` | Settings overview |
| `06_motivation.png` | Motivation and quote/mantra content |
| `07_backup.png` | Backup and restore controls |
| `08_notification.png` | Reminder notification |
| `09_about.png` | About and social links |

## Guidelines

- Use demo data only. Do not include personal habits, contacts, logs, or device identifiers.
- Prefer PNG or JPG for README screenshots.
- Use consistent device dimensions and crop style when adding a new screenshot set.
- Keep screenshots current when major UI surfaces change, especially Tracker, Focus, Launcher, Insights, and Settings.
- Optimize large images before committing when possible.

## Referencing Assets

```markdown
<p align="center">
  <img src="assets/screenshots/01_home.jpg" width="220" alt="Home screen" />
  <img src="assets/screenshots/04_insights.png" width="220" alt="Insights screen" />
</p>
```
