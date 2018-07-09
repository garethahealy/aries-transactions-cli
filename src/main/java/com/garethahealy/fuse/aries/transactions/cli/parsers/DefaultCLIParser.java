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
package com.garethahealy.fuse.aries.transactions.cli.parsers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class DefaultCLIParser {

    private CommandLineParser parser;

    public DefaultCLIParser(CommandLineParser parser) {
        this.parser = parser;
    }

    public Options getOptions() {
        //Setup the options we can use on the command line
        Option dbDriverOption = new Option("db", "database", true, "Comma-seperated database options, i.e.: option=value,option=value");

        Options options = new Options();
        options.addOption(dbDriverOption);

        return options;
    }

    public CommandLine parse(String[] args, Options options) throws ParseException {
        if (options == null || options.getOptions() == null || options.getOptions().size() <= 0) {
            throw new ParseException("Provided options is null or empty");
        }

        if (args == null || args.length <= 0) {
            throw new ParseException("Provided args is null or empty");
        }

        return parser.parse(options, args, true);
    }

    public void displayHelp(boolean throwException) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("aries-cli", getOptions());

        if (throwException) {
            throw new IllegalStateException("Missing arguments");
        }
    }

    public Map<String, String> getDatabaseOptions(CommandLine line) {
        Map<String, String> options = new HashMap<String, String>();

        String optionLine = line.getOptionValue("database");
        String[] optionLineSplit = optionLine.split(",");
        for (String current : optionLineSplit) {
            String[] keyValues = current.split("=");

            options.put(keyValues[0], keyValues[1]);
        }

        return options;
    }
}
