package com.garethahealy.fuse.aries.transactions.cli.connectionmanagers;

import java.sql.SQLException;
import java.util.Map;

import javax.resource.spi.ManagedConnectionFactory;

import org.tranql.connector.oracle.XAMCF;

public class OracleManagedConnectionFactory implements DbManagedConnectionFactory {

    //Oracle: SELECT formatid, globalid, branchid FROM SYS.DBA_PENDING_TRANSACTIONS
    //        http://docs.oracle.com/cd/B19306_01/server.102/b14237/statviews_4026.htm

    public ManagedConnectionFactory getManagedConnectionFactory(Map<String, String> properties) throws SQLException {
        XAMCF connectionFactory = new XAMCF();
        connectionFactory.setUserName(properties.get("username"));
        connectionFactory.setPassword(properties.get("password"));
        connectionFactory.setDatabaseName(properties.get("database"));
        connectionFactory.setServerName(properties.get("host"));

        return connectionFactory;
    }
}
