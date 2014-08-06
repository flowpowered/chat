package com.flowpowered.chat.env.expressions;

import java.util.List;

public class MessageFormat extends AbstractFormat {

    public MessageFormat(String formatString, String... argVarNames) {
        super(formatString, argVarNames);
    }

    public MessageFormat(String formatString, List<String> argVarNames) {
        super(formatString, argVarNames);
    }

    @Override
    protected String format(String formatString, Object[] args) {
        return java.text.MessageFormat.format(formatString, args);
    }

}
