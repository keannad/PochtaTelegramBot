/*
 * This file is generated by jOOQ.
 */
package ru.bletenkov.pochtabot.db.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import ru.bletenkov.pochtabot.db.Keys;
import ru.bletenkov.pochtabot.db.Pochtabot;
import ru.bletenkov.pochtabot.db.tables.records.MailPackageRecord;


/**
 * Почтовые отправления
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MailPackage extends TableImpl<MailPackageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>pochtabot.mail_package</code>
     */
    public static final MailPackage MAIL_PACKAGE = new MailPackage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MailPackageRecord> getRecordType() {
        return MailPackageRecord.class;
    }

    /**
     * The column <code>pochtabot.mail_package.id</code>. Идентификатор почтового отправления
     */
    public final TableField<MailPackageRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "Идентификатор почтового отправления");

    /**
     * The column <code>pochtabot.mail_package.user_id</code>. Идентификатор пользователя/владельца
     */
    public final TableField<MailPackageRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false), this, "Идентификатор пользователя/владельца");

    /**
     * The column <code>pochtabot.mail_package.code</code>. Идентификационный код почтового отправления
     */
    public final TableField<MailPackageRecord, String> CODE = createField(DSL.name("code"), SQLDataType.VARCHAR(13).nullable(false), this, "Идентификационный код почтового отправления");

    /**
     * The column <code>pochtabot.mail_package.is_dead</code>. Признак завершенного почтового отправления
     */
    public final TableField<MailPackageRecord, Boolean> IS_DEAD = createField(DSL.name("is_dead"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "Признак завершенного почтового отправления");

    /**
     * The column <code>pochtabot.mail_package.created_at</code>. Дата создания почтового отправления
     */
    public final TableField<MailPackageRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "Дата создания почтового отправления");

    /**
     * The column <code>pochtabot.mail_package.updated_at</code>. Дата обновления почтового отправления
     */
    public final TableField<MailPackageRecord, LocalDateTime> UPDATED_AT = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(6), this, "Дата обновления почтового отправления");

    private MailPackage(Name alias, Table<MailPackageRecord> aliased) {
        this(alias, aliased, null);
    }

    private MailPackage(Name alias, Table<MailPackageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Почтовые отправления"), TableOptions.table());
    }

    /**
     * Create an aliased <code>pochtabot.mail_package</code> table reference
     */
    public MailPackage(String alias) {
        this(DSL.name(alias), MAIL_PACKAGE);
    }

    /**
     * Create an aliased <code>pochtabot.mail_package</code> table reference
     */
    public MailPackage(Name alias) {
        this(alias, MAIL_PACKAGE);
    }

    /**
     * Create a <code>pochtabot.mail_package</code> table reference
     */
    public MailPackage() {
        this(DSL.name("mail_package"), null);
    }

    public <O extends Record> MailPackage(Table<O> child, ForeignKey<O, MailPackageRecord> key) {
        super(child, key, MAIL_PACKAGE);
    }

    @Override
    public Schema getSchema() {
        return Pochtabot.POCHTABOT;
    }

    @Override
    public Identity<MailPackageRecord, Long> getIdentity() {
        return (Identity<MailPackageRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<MailPackageRecord> getPrimaryKey() {
        return Keys.MAIL_PACKAGE_ID_PK;
    }

    @Override
    public List<UniqueKey<MailPackageRecord>> getKeys() {
        return Arrays.<UniqueKey<MailPackageRecord>>asList(Keys.MAIL_PACKAGE_ID_PK);
    }

    @Override
    public List<ForeignKey<MailPackageRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MailPackageRecord, ?>>asList(Keys.MAIL_PACKAGE__USER_ID__FK);
    }

    private transient User _user;

    public User user() {
        if (_user == null)
            _user = new User(this, Keys.MAIL_PACKAGE__USER_ID__FK);

        return _user;
    }

    @Override
    public MailPackage as(String alias) {
        return new MailPackage(DSL.name(alias), this);
    }

    @Override
    public MailPackage as(Name alias) {
        return new MailPackage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MailPackage rename(String name) {
        return new MailPackage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MailPackage rename(Name name) {
        return new MailPackage(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, String, Boolean, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}