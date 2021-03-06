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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.geronimo.connector.outbound.GenericConnectionManager;
import org.apache.geronimo.connector.outbound.connectionmanagerconfig.PartitionedPool;
import org.apache.geronimo.connector.outbound.connectionmanagerconfig.XATransactions;
import org.apache.geronimo.transaction.manager.RecoverableTransactionManager;

public class DefaultGenericConnectionManager extends GenericConnectionManager {

    private static final long serialVersionUID = 1L;

    public DefaultGenericConnectionManager(XATransactions xaTransactions, PartitionedPool partitionedPool, RecoverableTransactionManager recoverableTransactionManager,
                                           String dataSource, ClassLoader loader) {
        super(xaTransactions, partitionedPool, null, null, recoverableTransactionManager, dataSource, loader);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }
}
