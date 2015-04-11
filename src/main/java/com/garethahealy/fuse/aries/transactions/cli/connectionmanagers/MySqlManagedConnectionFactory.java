package com.garethahealy.fuse.aries.transactions.cli.connectionmanagers;

import java.sql.SQLException;
import java.util.Map;

import javax.resource.spi.ManagedConnectionFactory;

import org.tranql.connector.mysql.XAMCF;

public class MySqlManagedConnectionFactory implements DbManagedConnectionFactory {

    public ManagedConnectionFactory getManagedConnectionFactory(Map<String, String> properties) throws SQLException {
        XAMCF connectionFactory = new XAMCF();
        connectionFactory.setUserName(properties.get("username"));
        connectionFactory.setPassword(properties.get("password"));
        connectionFactory.setDatabaseName(properties.get("database"));
        connectionFactory.setServerName(properties.get("host"));

        return connectionFactory;
    }
}
