/*
 * #%L
 * GarethHealy :: Aries Transactions CLI
 * %%
 * Copyright (C) 2013 - 2018 Gareth Healy
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
package com.garethahealy.fuse.aries.transactions.cli;

import java.sql.SQLException;
import java.util.Map;

import javax.transaction.xa.XAException;

import com.garethahealy.fuse.aries.transactions.cli.connectionmanagers.DbManagedConnectionFactory;
import com.garethahealy.fuse.aries.transactions.cli.connectionmanagers.MySqlManagedConnectionFactory;
import com.garethahealy.fuse.aries.transactions.cli.parsers.DefaultCLIParser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.geronimo.connector.outbound.ConnectionManagerContainer;
import org.apache.geronimo.connector.outbound.connectionmanagerconfig.PartitionedPool;
import org.apache.geronimo.connector.outbound.connectionmanagerconfig.XATransactions;
import org.apache.geronimo.transaction.manager.GeronimoTransactionManager;
import org.apache.geronimo.transaction.manager.RecoverableTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("Starting...");

        DefaultCLIParser parser = new DefaultCLIParser(new DefaultParser());

        try {
            CommandLine commandLine = parser.parse(args, parser.getOptions());

            Map<String, String> databaseOptions = parser.getDatabaseOptions(commandLine);

            DbManagedConnectionFactory managedConnectionFactory = new MySqlManagedConnectionFactory();

            LOG.info("About to run Aries...");

            ConnectionManagerContainer connectionManagerContainer = getConnectionManagerContainer("datasource");
            connectionManagerContainer.doRecovery(managedConnectionFactory.getManagedConnectionFactory(databaseOptions));
        } catch (ParseException ex) {
            LOG.error("We hit a problem! {}", ExceptionUtils.getStackTrace(ex));

            parser.displayHelp(false);
        } catch (XAException ex) {
            LOG.error("We hit a problem! {}", ExceptionUtils.getStackTrace(ex));

            parser.displayHelp(false);
        } catch (SQLException ex) {
            LOG.error("We hit a problem! {}", ExceptionUtils.getStackTrace(ex));

            parser.displayHelp(false);
        }
    }

    public static ConnectionManagerContainer getConnectionManagerContainer(String dataSource) throws XAException {
        XATransactions xaTransactions = new XATransactions(true, false);
        PartitionedPool partitionedPool = new PartitionedPool(10, 0, 5000, 15, true, false, false, true, false);
        RecoverableTransactionManager recoverableTransactionManager = new GeronimoTransactionManager();
        ClassLoader loader = Application.class.getClassLoader();

        DefaultGenericConnectionManager manager = new DefaultGenericConnectionManager(xaTransactions, partitionedPool, recoverableTransactionManager,
                                                                                      dataSource, loader);

        return manager;
    }
}
