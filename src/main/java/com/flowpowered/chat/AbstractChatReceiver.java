package com.flowpowered.chat;

import java.util.Set;

import com.flowpowered.chat.env.expressions.ConstantString;
import com.flowpowered.permissions.PermissionDomain;

public abstract class AbstractChatReceiver implements ChatReceiver {

    protected abstract PermissionDomain getDefaultPermissionDomain();

    @Override
    public boolean hasPermission(String permission) {
        return hasPermission(permission, getDefaultPermissionDomain());
    }

    @Override
    public boolean isInGroup(String group) {
        return isInGroup(group, getDefaultPermissionDomain());
    }

    @Override
    public Set<String> getGroups() {
        return getGroups(getDefaultPermissionDomain());
    }

    @Override
    public void sendMessage(String message) {
        sendMessage(ChatMessage.wrap(message));
    }

    @Override
    public void sendMessage(ChatReceiver from, String message) {
        sendMessage(from, ChatMessage.wrap(message));
    }

    @Override
    public void sendMessage(ChatReceiver from, ChatMessage message) {
        message.put(ChatMessage.SENDER_VAR, new ConstantString(from.getName()));
        sendMessage(message);
    }

}
