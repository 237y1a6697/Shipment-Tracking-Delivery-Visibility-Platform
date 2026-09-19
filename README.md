# ShipTrack Pro

Full-stack shipment tracking and delivery visibility platform.

## Stack
Java 21, Spring Boot, Spring Security, JWT, PostgreSQL, React, Vite and Docker.

## Features
- Registration and login
- BCrypt password hashing
- JWT authentication
- Customer/Admin/Driver roles
- Shipment creation
- Shipment tracking by tracking number
- Shipment status lifecycle
- Dashboard statistics
- Dockerized PostgreSQL, backend and frontend

## Run
`docker compose up --build`

Open `http://localhost:5173`.

For local development, run PostgreSQL with Docker, then start the Spring Boot backend and React frontend separately.
