/*
 * This file is generated by jOOQ.
 */
package ru.bletenkov.pochtabot.db;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import ru.bletenkov.pochtabot.db.tables.History;
import ru.bletenkov.pochtabot.db.tables.MailPackage;
import ru.bletenkov.pochtabot.db.tables.User;
import ru.bletenkov.pochtabot.db.tables.records.HistoryRecord;
import ru.bletenkov.pochtabot.db.tables.records.MailPackageRecord;
import ru.bletenkov.pochtabot.db.tables.records.UserRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * pochtabot.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<HistoryRecord> HISTORY_ID_PK = Internal.createUniqueKey(History.HISTORY, DSL.name("history_id_pk"), new TableField[] { History.HISTORY.ID }, true);
    public static final UniqueKey<MailPackageRecord> MAIL_PACKAGE_ID_PK = Internal.createUniqueKey(MailPackage.MAIL_PACKAGE, DSL.name("mail_package_id_pk"), new TableField[] { MailPackage.MAIL_PACKAGE.ID }, true);
    public static final UniqueKey<UserRecord> USER_ID_PK = Internal.createUniqueKey(User.USER, DSL.name("user_id_pk"), new TableField[] { User.USER.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<HistoryRecord, MailPackageRecord> HISTORY__MAIL_PACKAGE_ID__FK = Internal.createForeignKey(History.HISTORY, DSL.name("mail_package_id__fk"), new TableField[] { History.HISTORY.MAIL_PACKAGE_ID }, Keys.MAIL_PACKAGE_ID_PK, new TableField[] { MailPackage.MAIL_PACKAGE.ID }, true);
    public static final ForeignKey<MailPackageRecord, UserRecord> MAIL_PACKAGE__USER_ID__FK = Internal.createForeignKey(MailPackage.MAIL_PACKAGE, DSL.name("user_id__fk"), new TableField[] { MailPackage.MAIL_PACKAGE.USER_ID }, Keys.USER_ID_PK, new TableField[] { User.USER.ID }, true);
}
