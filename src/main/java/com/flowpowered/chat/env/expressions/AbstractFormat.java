package com.flowpowered.chat.env.expressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.flowpowered.chat.env.Environment;
import com.flowpowered.chat.env.Expression;

public abstract class AbstractFormat implements Expression {
    protected final String formatString;
    protected final List<String> argVarNames;

    public AbstractFormat(String formatString, String... argVarNames) {
        this(formatString, Arrays.asList(argVarNames));
    }

    public AbstractFormat(String formatString, List<String> argVarNames) {
        this.formatString = formatString;
        this.argVarNames = argVarNames;
    }

    public String getFormat() {
        return formatString;
    }

    public List<String> getArgVarNames() {
        return Collections.unmodifiableList(argVarNames);
    }

    @Override
    public String eval(Environment env) {
        Object[] args = new Object[argVarNames.size()];
        for (int i = 0; i < argVarNames.size(); ++i) {
            args[i] = env.eval(argVarNames.get(i));
        }
        return format(formatString, args);
    }

    protected abstract String format(String formatString, Object[] args);
}