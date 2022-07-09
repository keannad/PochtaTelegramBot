CREATE TABLE IF NOT EXISTS pochtabot."user"
(
    id          bigserial   NOT NULL,
    chat_id     bigint      NOT NULL,
    lang        varchar(3)  NOT NULL DEFAULT 'RUS',
    is_admin    boolean     NOT NULL DEFAULT FALSE,
    CONSTRAINT user_id_pk PRIMARY KEY (id)
)
    WITH (
        OIDS=FALSE
      );

COMMENT ON TABLE pochtabot."user" IS 'Зарегистрированные пользователи';
COMMENT ON COLUMN pochtabot."user".id IS 'Идентификатор пользователя';
COMMENT ON COLUMN pochtabot."user".chat_id IS 'Идентификатор чата пользователя';
COMMENT ON COLUMN pochtabot."user".is_admin IS 'Признак привилегированности пользователя';

CREATE TABLE IF NOT EXISTS pochtabot.mail_package
(
    id          bigserial                   NOT NULL,
    user_id     bigint                      NOT NULL,
    code        varchar(13)                 NOT NULL,
    is_dead     boolean                     NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT mail_package_id_pk PRIMARY KEY (id),
    CONSTRAINT user_id__fk FOREIGN KEY (user_id)
    REFERENCES pochtabot."user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE CASCADE
)
    WITH (
        OIDS=FALSE
      );

COMMENT ON TABLE pochtabot.mail_package IS 'Почтовые отправления';
COMMENT ON COLUMN pochtabot.mail_package.id IS 'Идентификатор почтового отправления';
COMMENT ON COLUMN pochtabot.mail_package.user_id IS 'Идентификатор пользователя/владельца';
COMMENT ON COLUMN pochtabot.mail_package.code IS 'Идентификационный код почтового отправления';
COMMENT ON COLUMN pochtabot.mail_package.is_dead IS 'Признак завершенного почтового отправления';
COMMENT ON COLUMN pochtabot.mail_package.created_at IS 'Дата создания почтового отправления';
COMMENT ON COLUMN pochtabot.mail_package.updated_at IS 'Дата обновления почтового отправления';

CREATE TABLE IF NOT EXISTS pochtabot.history
(
    id              bigserial NOT NULL,
    mail_package_id bigint NOT NULL,
    type_id         INTEGER,
    type_name       varchar(50),
    attribute_id    INTEGER,
    attribute_name  varchar(50),
    oper_date       TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT history_id_pk PRIMARY KEY (id),
    CONSTRAINT mail_package_id__fk FOREIGN KEY (mail_package_id)
    REFERENCES pochtabot.mail_package (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE CASCADE
)
    WITH (
        OIDS=FALSE
      );

COMMENT ON TABLE pochtabot.history IS 'История движений почтовых отправлений';
COMMENT ON COLUMN pochtabot.history.id IS 'Идентификатор записи';
COMMENT ON COLUMN pochtabot.history.mail_package_id IS 'Идентификатор почтового отправления';
COMMENT ON COLUMN pochtabot.history.type_id IS 'Идентификатор состояния';
COMMENT ON COLUMN pochtabot.history.type_name IS 'Наименование состояния';
COMMENT ON COLUMN pochtabot.history.attribute_id IS 'Идентификатор атрибута состояния';
COMMENT ON COLUMN pochtabot.history.attribute_name IS 'Наименование атрибута состояния';
COMMENT ON COLUMN pochtabot.history.oper_date IS 'Дата изменения состояния';

INSERT INTO pochtabot."user"(id, chat_id, is_admin) VALUES (1, 35517360, TRUE);