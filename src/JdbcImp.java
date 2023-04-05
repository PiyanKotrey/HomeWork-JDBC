import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class JdbcImp {
    public DataSource dataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("kotreyjr10");
        dataSource.setDatabaseName("Topics");
        return dataSource;
    }
}
