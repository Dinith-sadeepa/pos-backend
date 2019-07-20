package lk.ijse.absd.pos.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
    public static DataSource getConnection() throws NamingException {
        Context initialContext = new InitialContext();
        return (DataSource) initialContext.lookup("java:comp/env/pos-servlet");
    }
}
