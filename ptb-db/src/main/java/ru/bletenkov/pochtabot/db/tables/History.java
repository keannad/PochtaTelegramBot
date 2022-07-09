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
import org.jooq.Row7;
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
import ru.bletenkov.pochtabot.db.tables.records.HistoryRecord;


/**
 * История движений почтовых отправлений
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class History extends TableImpl<HistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>pochtabot.history</code>
     */
    public static final History HISTORY = new History();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HistoryRecord> getRecordType() {
        return HistoryRecord.class;
    }

    /**
     * The column <code>pochtabot.history.id</code>. Идентификатор записи
     */
    public final TableField<HistoryRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "Идентификатор записи");

    /**
     * The column <code>pochtabot.history.mail_package_id</code>. Идентификатор почтового отправления
     */
    public final TableField<HistoryRecord, Long> MAIL_PACKAGE_ID = createField(DSL.name("mail_package_id"), SQLDataType.BIGINT.nullable(false), this, "Идентификатор почтового отправления");

    /**
     * The column <code>pochtabot.history.type_id</code>. Идентификатор состояния
     */
    public final TableField<HistoryRecord, Integer> TYPE_ID = createField(DSL.name("type_id"), SQLDataType.INTEGER, this, "Идентификатор состояния");

    /**
     * The column <code>pochtabot.history.type_name</code>. Наименование состояния
     */
    public final TableField<HistoryRecord, String> TYPE_NAME = createField(DSL.name("type_name"), SQLDataType.VARCHAR(50), this, "Наименование состояния");

    /**
     * The column <code>pochtabot.history.attribute_id</code>. Идентификатор атрибута состояния
     */
    public final TableField<HistoryRecord, Integer> ATTRIBUTE_ID = createField(DSL.name("attribute_id"), SQLDataType.INTEGER, this, "Идентификатор атрибута состояния");

    /**
     * The column <code>pochtabot.history.attribute_name</code>. Наименование атрибута состояния
     */
    public final TableField<HistoryRecord, String> ATTRIBUTE_NAME = createField(DSL.name("attribute_name"), SQLDataType.VARCHAR(50), this, "Наименование атрибута состояния");

    /**
     * The column <code>pochtabot.history.oper_date</code>. Дата изменения состояния
     */
    public final TableField<HistoryRecord, LocalDateTime> OPER_DATE = createField(DSL.name("oper_date"), SQLDataType.LOCALDATETIME(6), this, "Дата изменения состояния");

    private History(Name alias, Table<HistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private History(Name alias, Table<HistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("История движений почтовых отправлений"), TableOptions.table());
    }

    /**
     * Create an aliased <code>pochtabot.history</code> table reference
     */
    public History(String alias) {
        this(DSL.name(alias), HISTORY);
    }

    /**
     * Create an aliased <code>pochtabot.history</code> table reference
     */
    public History(Name alias) {
        this(alias, HISTORY);
    }

    /**
     * Create a <code>pochtabot.history</code> table reference
     */
    public History() {
        this(DSL.name("history"), null);
    }

    public <O extends Record> History(Table<O> child, ForeignKey<O, HistoryRecord> key) {
        super(child, key, HISTORY);
    }

    @Override
    public Schema getSchema() {
        return Pochtabot.POCHTABOT;
    }

    @Override
    public Identity<HistoryRecord, Long> getIdentity() {
        return (Identity<HistoryRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<HistoryRecord> getPrimaryKey() {
        return Keys.HISTORY_ID_PK;
    }

    @Override
    public List<UniqueKey<HistoryRecord>> getKeys() {
        return Arrays.<UniqueKey<HistoryRecord>>asList(Keys.HISTORY_ID_PK);
    }

    @Override
    public List<ForeignKey<HistoryRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<HistoryRecord, ?>>asList(Keys.HISTORY__MAIL_PACKAGE_ID__FK);
    }

    private transient MailPackage _mailPackage;

    public MailPackage mailPackage() {
        if (_mailPackage == null)
            _mailPackage = new MailPackage(this, Keys.HISTORY__MAIL_PACKAGE_ID__FK);

        return _mailPackage;
    }

    @Override
    public History as(String alias) {
        return new History(DSL.name(alias), this);
    }

    @Override
    public History as(Name alias) {
        return new History(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public History rename(String name) {
        return new History(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public History rename(Name name) {
        return new History(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, Long, Integer, String, Integer, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}