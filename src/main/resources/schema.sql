-- Tabla: users
CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP,
    modified TIMESTAMP,
    last_login TIMESTAMP,
    token VARCHAR(255) NOT NULL,
    is_active BOOLEAN
);

-- Tabla: phones
CREATE TABLE phones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20),
    citycode VARCHAR(10),
    contrycode VARCHAR(10),
    user_id VARCHAR(36),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);