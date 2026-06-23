# Security Policy

## Supported Versions

| Version | Supported          |
|---------|--------------------|
| 1.3.x   | ✅ Active support  |
| < 1.3   | ❌ No longer supported |

## Reporting a Vulnerability

If you discover a security vulnerability in Tsuzuku, **please do not open a public issue**.

Instead, report it responsibly via one of the following methods:

1. **Email**: Send a detailed report to **agupta07505@gmail.com**
2. **GitHub Private Advisory**: Use [GitHub Security Advisories](https://github.com/agupta07505/Tsuzuku/security/advisories/new) to privately report the issue

### What to Include

- A description of the vulnerability
- Steps to reproduce the issue
- The potential impact
- Any suggested fixes (if applicable)

### Response Timeline

- **Acknowledgment**: Within 48 hours
- **Initial Assessment**: Within 1 week
- **Fix & Disclosure**: We aim to release a patch within 2 weeks of confirmation

## Security Best Practices for Contributors

- Never commit API keys, passwords, or keystore files to the repository.
- Use `.env` files for sensitive configuration (see `.env.example`).
- Keystore credentials should be passed via GitHub Secrets in CI/CD workflows.
- Always use the latest dependencies to avoid known CVEs.

## Scope

This policy covers the Tsuzuku Android application source code and its build pipeline. Third-party dependencies are maintained by their respective projects.
