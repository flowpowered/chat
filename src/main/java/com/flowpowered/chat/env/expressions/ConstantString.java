package com.flowpowered.chat.env.expressions;

import com.flowpowered.chat.env.Environment;
import com.flowpowered.chat.env.Expression;

public class ConstantString implements Expression {
    private final String value;

    public ConstantString(String value) {
        this.value = value;
    }

    @Override
    public String eval(Environment env) {
        return value;
    }

}
