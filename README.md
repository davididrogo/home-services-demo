# 🛠️ Home Services Demo

A Spring Boot app for booking home service providers (plumbing, HVAC, electrical, etc.).  
Users request quotes, bookings are created, and pricing is driven by rules.  
MongoDB stores users/providers/bookings. Deployments run on Render.

- **Staging**: https://home-services-demo-staging.onrender.com
- **Prod (optional)**: https://home-services-demo.onrender.com

---

## ⚙️ Tech Stack

- **Java 21**, **Spring Boot**
- **Spring Data MongoDB** (MongoDB Atlas for cloud, Docker Mongo for local)
- **Render** for deployment
- CI: GitHub Actions (tests/build), Postman for API tests

---

## 🚀 Quick Start (Local)

### Prereqs
- JDK 17+
- Maven
- Docker (for local Mongo)

### 1) Start Mongo locally
```bash
docker compose up -d         # uses docker-compose.yml at repo root
# Mongo will listen on mongodb://localhost:27017
