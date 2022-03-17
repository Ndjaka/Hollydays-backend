ALTER TABLE user
MODIFY COLUMN sex tinyint(1),
MODIFY COLUMN  email varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

ALTER TABLE likes
    MODIFY COLUMN like_type tinyint(1);