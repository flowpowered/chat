package com.flowpowered.chat;

import java.util.Map;

import com.flowpowered.chat.env.Environment;
import com.flowpowered.chat.env.EnvironmentExpression;
import com.flowpowered.chat.env.Expression;
import com.flowpowered.chat.env.ParentedEnvironment;

public class ChatMessage extends ParentedEnvironment implements EnvironmentExpression {
    public static final String DEFAULT_MAIN_VAR = "main";

    private String mainVar = DEFAULT_MAIN_VAR;

    public ChatMessage() {
    }

    public ChatMessage(ParentedEnvironment parent) {
        super(parent);
    }

    public ChatMessage(Map<String, Expression> vars) {
        super(vars);
    }

    public ChatMessage(Map<String, Expression> vars, ParentedEnvironment parent) {
        super(vars, parent);
    }

    public void setMainVar(String varName) {
        this.mainVar = varName;
    }

    public String getMainVar() {
        return mainVar;
    }

    public String eval() {
        return evalToString(getMainVar());
    }

    @Override
    public Object eval(Environment env) {
        return eval();
    }

    @Override
    public String toString() {
        return eval();
    }
}
