import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;

public class Main {
    public static void main(final String[] args) {
        final PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(5432);
        ds.setUser("postgres");
        ds.setPassword("postgres");
        ds.setDatabaseName("issue");

        final Jdbi jdbi = Jdbi.create(ds);
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new PostgresPlugin());

        try (final var handle = jdbi.open()) {
            handle.registerRowMapper(FieldMapper.factory(User.class, "u"));

            final List<User> users = handle.select("select id uid, name uname, street ustreet, city ucity from users").mapTo(User.class).list();
            System.err.println(users);
        }
    }
}
