CREATE TABLE `role`
(
  `role_id` bigint(20) NOT NULL,
  `role`    varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `user`
(
  `user_id`    bigint(20) NOT NULL,
  `email`      varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name`       varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password`   varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active`     int(11)                                 DEFAULT NULL,
  `updated_at` bigint(20)                              DEFAULT NULL,
  `created_at` bigint(20)                              DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `user_role`
(
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `role`(`role_id`, `role`)
VALUES ('1', 'ADMIN');

INSERT INTO `user`(`user_id`, `email`, `name`, `password`, `active`, `updated_at`, `created_at`)
VALUES ('1', 'admin@c.com', 'admin', '$2a$10$xEZMWI2T4q4Wl38AmRJNI.HuunjxmyWOP6dsi3sX4/Hsbmd9wfaDK', '1', '0', '0');

INSERT INTO `user_role`(`user_id`, `role_id`)
VALUES ('1', '1');