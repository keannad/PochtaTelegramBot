CREATE SEQUENCE IF NOT EXISTS russianpost.hibernate_sequence;

CREATE TABLE IF NOT EXISTS russianpost.users(
    id bigint PRIMARY KEY NOT NULL,
    chat_id bigint NOT NULL,
    is_admin bool);

CREATE TABLE IF NOT EXISTS russianpost.mail_package(
    id bigint PRIMARY KEY NOT NULL,
    user_id bigint NOT NULL,
    code varchar(13) NOT NULL,
    is_dead bool,
    FOREIGN KEY (user_id) REFERENCES users(id));

CREATE TABLE IF NOT EXISTS russianpost.history_records(
    id bigint PRIMARY KEY NOT NULL,
    mail_package_id bigint NOT NULL,
    type_id INTEGER,
    type_name varchar(50),
    attribute_id INTEGER,
    attribute_name varchar(50),
    record_date timestamp,
    is_last bool,
    FOREIGN KEY (mail_package_id) REFERENCES mail_package(id));