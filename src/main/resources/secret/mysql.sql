CREATE DATABASE IF NOT EXISTS account_book DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 创建用户表
CREATE TABLE user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,

    PRIMARY KEY (id)
);

-- 创建会话表
CREATE TABLE session (
    id VARCHAR(50) NOT NULL,
    data LONGTEXT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);