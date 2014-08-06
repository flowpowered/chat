package com.flowpowered.chat.env.expressions;

import java.util.List;

public class FormatString extends AbstractFormat {

    public FormatString(String formatString, String... argVarNames) {
        super(formatString, argVarNames);
    }

    public FormatString(String formatString, List<String> argVarNames) {
        super(formatString, argVarNames);
    }

    @Override
    protected String format(String formatString, Object[] args) {
        return String.format(formatString, args);
    }

}
