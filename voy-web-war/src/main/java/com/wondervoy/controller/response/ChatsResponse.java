package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class ChatsResponse extends BaseRespones{

    private long friendId;

    private List<Chat> chats;

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }
}
