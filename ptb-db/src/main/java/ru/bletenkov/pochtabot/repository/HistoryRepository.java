package ru.bletenkov.pochtabot.repository;

import org.jooq.Configuration;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.db.tables.daos.HistoryDao;
import ru.bletenkov.pochtabot.db.tables.pojos.History;

import static org.jooq.impl.DSL.using;
import static ru.bletenkov.pochtabot.db.tables.History.HISTORY;

@Repository
public class HistoryRepository extends HistoryDao {

    public HistoryRepository(Configuration configuration) {
        super(configuration);
    }

    public History getLastRecord(Long packageId) {
        return using(configuration())
                .select(HISTORY.fields())
                .from(HISTORY)
                .where(HISTORY.MAIL_PACKAGE_ID.eq(packageId))
                .orderBy(HISTORY.OPER_DATE.desc())
                .limit(1)
                .fetchOneInto(History.class);
    }
}
