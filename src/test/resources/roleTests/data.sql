SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT,
    `address`     varchar(255) NOT NULL,
    `email`       varchar(255) NOT NULL,
    `firstname`   varchar(255) NOT NULL,
    `fullname`    varchar(255) NOT NULL,
    `lastname`    varchar(255) NOT NULL,
    `mobilephone` varchar(255) NOT NULL,
    `user_id`     bigint       NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKe07k4jcfdophemi6j1lt84b61` (`user_id`),
    CONSTRAINT `FKe07k4jcfdophemi6j1lt84b61` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role
-- ----------------------------
-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdhai` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role`
VALUES (1, 'ROLE_ADMIN');
INSERT INTO `role`
VALUES (2, 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill`
(
    `id`   bigint       NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_5ljf2l2h4odhtxrsuohlro4ir` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for skill_level
-- ----------------------------
DROP TABLE IF EXISTS `skill_level`;
CREATE TABLE `skill_level`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `level`      bigint NOT NULL,
    `skill_id`   bigint NOT NULL,
    `contact_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKntyx3u8wh8rbquuprmb2xtl45` (`skill_id`, `contact_id`),
    KEY `FKdmsnatu3tok59kjbks8qgsl07` (`contact_id`),
    CONSTRAINT `FKdmsnatu3tok59kjbks8qgsl07` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FKk8yy30pvpxxp3etl7olmfesh6` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       bigint       NOT NULL AUTO_INCREMENT,
    `email`    varchar(30)  NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, 'admin@owt.ch', '$2a$10$SbGVgg/YLbTUeS5HPt1c/uGe6C9h4/BVoA5wJ2f76/lGv1/kTVGgm');
INSERT INTO `user`
VALUES (2, 'user1@owt.ch', '$2a$10$PC.uL2YIcWNPAYo0VG2Qs.3FdBD/SpaF3cPsSUmKtnaibDyRl9THS');
INSERT INTO `user`
VALUES (3, 'user2@owt.ch', '$2a$10$sG/sGgW/0ECeG/v.h/rNJuTDXZLSXyRwvFEcyAoRTQ/VuQ5yFmBuu');
COMMIT;

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` int    NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
    CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
BEGIN;
INSERT INTO `user_roles`
VALUES (1, 1);
INSERT INTO `user_roles`
VALUES (2, 2);
INSERT INTO `user_roles`
VALUES (3, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
