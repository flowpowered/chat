package com.flowpowered.chat.env;

public abstract class AbstractEnvironment implements Environment {

    @Override
    public abstract Expression remove(String varName);

    @Override
    public abstract Expression put(String varName, Expression value);

    @Override
    public abstract boolean contains(String varName);


    @Override
    public Object eval(String varName) {
        // TODO: Prevent circular dependencies maybe?
        Expression expr = get(varName);
        if (expr != null) {
            return expr.eval(this);
        }
        return null;
    }

    @Override
    public String evalToString(String varName) {
        Object result = eval(varName);
        return result != null ? result.toString() : null;
    }

    protected static Expression getSub(Expression expr, String subName) {
        if (expr instanceof Environment) {
            return ((Environment) expr).get(subName);
        }
        return null;
    }

    protected static boolean hasSub(Expression expr, String subName) {
        if (expr instanceof Environment) {
            return ((Environment) expr).contains(subName);
        }
        return false;
    }

    protected static void checkVarName(String varName) {
        if (varName.contains(Environment.MEMBER_SEPARATOR)) {
            throw new IllegalArgumentException("Member separator in var name:" + varName);
        }
    }
}