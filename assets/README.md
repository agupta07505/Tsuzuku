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
| `01_home.jpg` | Home dashboard |
| `02_Habits.jpg` | Habits screen |
| `03_Launcher.jpg` | Tsuzuku launcher preview |
| `04_Activate_Laucnher.jpg` | Launcher activation flow |
| `05_Launcher_Allowed_Apps.jpg` | Launcher allowed-app selection |
| `06_Launcher_Focus_Settings.jpg` | Launcher focus settings |
| `07_Focus.jpg` | Focus mode |
| `08_Insights_1.jpg` | Insights overview |
| `09_Insights_2.jpg` | Insights heatmap and details |
| `10_Settings_1.jpg` | Settings overview |
| `11_Settings_2.jpg` | Settings customization and controls |
| `12_Settings_About_1.jpg` | About screen |
| `13_Settings_About_2.jpg` | About and social links |

## Guidelines

- Use demo data only. Do not include personal habits, contacts, logs, or device identifiers.
- Prefer PNG or JPG for README screenshots.
- Use consistent device dimensions and crop style when adding a new screenshot set.
- Keep screenshots current when major UI surfaces change, especially Tracker, Focus, Launcher, Insights, and Settings.
- Optimize large images before committing when possible.

## Referencing Assets

```markdown
<p align="center">
  <img src="assets/screenshots/01_home.jpg" width="220" alt="Home dashboard" />
  <img src="assets/screenshots/08_Insights_1.jpg" width="220" alt="Insights overview" />
</p>
```
