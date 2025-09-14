

# blog-website

A simple blog system that supports viewing blog posts, reading details, adding and deleting posts, as well as user registration and login functionality.

## Project Structure

* `backend/`: Spring Boot backend
* `frontend/`: Vue 3 frontend

## Environment Setup

### Backend

1. Use **JDK 17+**.

2. Database: **MySQL**. Execute the SQL scripts to create tables:

```sql
-- =========================================
-- Database Table Scripts
-- =========================================

-- Blog User Table
CREATE TABLE blog_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Blog Post Table
CREATE TABLE blog_post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    summary VARCHAR(255),
    content TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

3. Configure your database connection in `application.properties`.
4. Run the backend project (via IDE or `mvn spring-boot:run`).

### Frontend

1. Install **Node.js** (version 16+ recommended).
2. Install dependencies:

```bash
cd frontend
npm install
```

3. Start the frontend:

```bash
npm run dev
```

4. Open your browser and go to:

```
http://localhost:5175
```

## Features

* User registration and login
* View blog posts and details
* Add/Delete blog posts (after login)
* Blog management buttons displayed on the homepage



