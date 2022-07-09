package ru.bletenkov.pochtabot.repository;

import org.jooq.Configuration;
import org.springframework.stereotype.Repository;
import ru.bletenkov.pochtabot.db.tables.daos.UserDao;

@Repository
public class UserRepository extends UserDao {

    public UserRepository(Configuration configuration) {
        super(configuration);
    }
}
