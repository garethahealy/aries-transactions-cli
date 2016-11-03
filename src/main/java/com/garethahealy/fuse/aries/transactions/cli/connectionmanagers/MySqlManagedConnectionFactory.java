/*
 * #%L
 * GarethHealy :: Aries Transactions CLI
 * %%
 * Copyright (C) 2013 - 2016 Gareth Healy
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
