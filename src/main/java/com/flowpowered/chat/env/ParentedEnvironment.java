package com.flowpowered.chat.env;

import java.util.HashMap;
import java.util.Map;

public class ParentedEnvironment extends AbstractEnvironment {
    private final Map<String, Expression> vars = new HashMap<>();
    private final ParentedEnvironment parent;

    public ParentedEnvironment() {
        this.parent = null;
    }

    public ParentedEnvironment(ParentedEnvironment parent) {
        this.parent = parent;
    }

    public ParentedEnvironment(Map<String, Expression> vars) {
        this(vars, null);
    }

    public ParentedEnvironment(Map<String, Expression> vars, ParentedEnvironment parent) {
        this(parent);
        this.vars.putAll(vars);
    }

    protected Expression getRaw(String varName) {
        String[] parts = Environment.MEMBER_SEPARATOR_REGEX.split(varName, 2);
        Expression expr = vars.get(parts[0]);
        if (parts.length == 1) {
            return expr;
        }
        return getSub(expr, parts[1]);
    }

    @Override
    public Expression get(String varName) {
        return vars.containsKey(varName) ? getRaw(varName) : parent.get(varName);
    }

    protected boolean containsRaw(String varName) {
        String[] parts = Environment.MEMBER_SEPARATOR_REGEX.split(varName, 2);
        if (parts.length == 1) {
            return vars.containsKey(varName);
        }
        return hasSub(vars.get(parts[0]), parts[1]);
    }

    @Override
    public boolean contains(String varName) {
        return containsRaw(varName) || parent.contains(varName);
    }

    @Override
    public Expression put(String varName, Expression value) {
        checkVarName(varName); // Don't let them put anyting with member separator in it.
        return vars.put(varName, value);
    }

    @Override
    public Expression remove(String varName) {
        return vars.remove(varName);
    }

    public ParentedEnvironment child() {
        return new ParentedEnvironment(this);
    }

    public ParentedEnvironment copy() {
        return new ParentedEnvironment(vars, parent);
    }

    public ParentedEnvironment deepCopy() {
        return new ParentedEnvironment(vars, parent != null ? parent.deepCopy() : null);
    }

}
