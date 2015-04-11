package com.garethahealy.fuse.aries.transactions.cli.connectionmanagers;

import java.sql.SQLException;
import java.util.Map;

import javax.resource.spi.ManagedConnectionFactory;

public interface DbManagedConnectionFactory {

    ManagedConnectionFactory getManagedConnectionFactory(Map<String, String> properties) throws SQLException;
}
