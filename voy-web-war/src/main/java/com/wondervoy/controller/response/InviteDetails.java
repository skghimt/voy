package com.wondervoy.controller.response;

import java.util.List;

/**
 * Created by ckzhang on 14-12-22.
 */
public class InviteDetails extends BaseRespones{

    private List<InviteDescResponse> invites;

    public List<InviteDescResponse> getInvites() {
        return invites;
    }

    public void setInvites(List<InviteDescResponse> invites) {
        this.invites = invites;
    }
}
