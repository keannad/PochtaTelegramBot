package ru.bletenkov.pochtabot.repository;

import org.jooq.Configuration;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.db.tables.daos.MailPackageDao;
import ru.bletenkov.pochtabot.db.tables.pojos.MailPackage;

import static org.jooq.impl.DSL.using;
import static ru.bletenkov.pochtabot.db.tables.MailPackage.MAIL_PACKAGE;

@Repository
public class MailPackageRepository extends MailPackageDao {

    public MailPackageRepository(Configuration configuration) {
        super(configuration);
    }

    public MailPackage findByUserIdAndCode (Long userId, String code) {
        return using(configuration())
                .select(MAIL_PACKAGE.fields())
                .from(MAIL_PACKAGE)
                .where(MAIL_PACKAGE.USER_ID.eq(userId)
                        .and(MAIL_PACKAGE.CODE.equalIgnoreCase(code)))
                .fetchOneInto(MailPackage.class);
    }
}
