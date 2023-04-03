import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class JdbcImp {
    public DataSource dataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("kotdraxler@2020");
        dataSource.setDatabaseName("Topics");
        return dataSource;
    }
}
