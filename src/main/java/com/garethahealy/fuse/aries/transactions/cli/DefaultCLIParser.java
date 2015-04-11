package com.garethahealy.fuse.aries.transactions.cli;

import java.io.IOException;

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
        Option dbDriverOption = new Option("db", "datasource", true, "Database datasource to use");

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

    public String getDataSourceOption(CommandLine line) {
        return line.getOptionValue("datasource");
    }
}
