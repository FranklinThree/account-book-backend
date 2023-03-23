CREATE DATABASE IF NOT EXISTS account_book DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use account_book;

-- 创建用户表
CREATE TABLE user (
    id INT(11) NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    wx_id VARCHAR(50) NOT NULL,
    qq_id VARCHAR(50) NOT NULL,
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


-- 创建账单表

CREATE TABLE bill (
    id INT(11) NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL,
    type_id INT(11) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

-- 创建账单类型表
CREATE TABLE bill_type (
    id INT(11) NOT NULL AUTO_INCREMENT,
#     user_id INT(11) NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

-- 创建图片资源表
CREATE TABLE picture (
    id INT(11) NOT NULL AUTO_INCREMENT,
    data MEDIUMBLOB NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

