# Assets

This directory holds all static assets for the Tsuzuku project.

## Directory Structure

```
assets/
├── screenshots/       ← App preview images for README & store listings
├── banners/           ← Feature graphics, promotional banners
└── icons/             ← Additional icons & branding assets
```

---

## 📸 Screenshots

Place app preview images in `screenshots/`. These are referenced by the README's Preview section.

### Naming Convention

| Filename                  | Content                              |
|---------------------------|--------------------------------------|
| `01_home.jpg`             | Home screen (empty state)            |
| `02_habit_tracker.jpg`    | Tracker with habit & mantra quote    |
| `03_create_streak.jpg`    | Create Streak dialog                 |
| `04_insights.png`         | Insights — heatmap & weekly activity |
| `05_settings.png`         | Settings overview                    |
| `06_motivation.png`       | Motivation section with mantras      |
| `07_backup.png`           | Backup & Sync (import/export)        |
| `08_notification.png`     | Lock screen notification with mantra |
| `09_about.png`            | About screen with social links       |

### Guidelines

- **Format**: PNG or WebP
- **Resolution**: 1080px wide recommended
- **Content**: Use sample/demo data — avoid personal information
- **Themes**: Showcase the app's signature dark themes

---

## 🖼️ Banners

Place feature graphics and promotional images in `banners/`.

| Filename                  | Content                         |
|---------------------------|---------------------------------|
| `feature_graphic.png`     | 1024×500 Play Store graphic     |
| `github_social.png`       | 1280×640 GitHub social preview  |

---

## How to Reference in README

```markdown
<p align="center">
  <img src="assets/screenshots/01_home.jpg" width="180" alt="Home Screen" />
  <img src="assets/screenshots/02_habit_tracker.jpg" width="180" alt="Habit Tracker" />
  <img src="assets/screenshots/03_create_streak.jpg" width="180" alt="Create Streak" />
</p>
```
