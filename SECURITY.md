# Security Policy

## Supported Versions

| Version | Supported |
|---------|-----------|
| 2.x | Active support |
| < 2.0 | Best effort only |

## Reporting a Vulnerability

Please do not open a public issue for security problems.

Report vulnerabilities by email to **agupta07505@gmail.com** or through GitHub private security advisories:

https://github.com/agupta07505/Tsuzuku/security/advisories/new

Include as much detail as you can:

- A description of the vulnerability
- Steps to reproduce
- Android version and device/emulator details
- Potential impact
- Screenshots, logs, or a proof of concept if safe to share
- Suggested fixes, if you have them

## Response Timeline

- Acknowledgment: within 48 hours
- Initial assessment: within 7 days
- Fix target: within 14 days after confirmation, when practical

## Scope

This policy covers:

- Tsuzuku Android app source code
- Local habit and focus-session storage
- Backup and restore behavior
- Notification, focus-service, and launcher permission flows
- Build and release pipeline configuration

Third-party dependencies are maintained by their respective projects.

## Contributor Security Checklist

- Never commit passwords, API keys, signing credentials, or private keystores.
- Do not commit `local.properties`, generated APKs, or personal backup JSON files.
- Keep release signing values in GitHub Secrets or local Gradle properties.
- Treat exported backup files as user data.
- Be careful with launcher, notification, foreground service, and DND permission changes.
