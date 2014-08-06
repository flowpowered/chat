package com.flowpowered.chat.env;

import java.util.regex.Pattern;

public interface Environment {
    public static final String MEMBER_SEPARATOR = ".";
    public static final Pattern MEMBER_SEPARATOR_REGEX = Pattern.compile("\\.");

    Expression remove(String varName);

    Expression put(String varName, Expression value);

    boolean contains(String varName);

    Expression get(String varName);

    Object eval(String varName);

    String evalToString(String varName);

}