package com.interndemosss.hackathonassignment.calenderSlot;

import com.google.gson.annotations.SerializedName;

public class slots {
    public slots(String slotName, int slotId, boolean slotActive) {
        this.slotName = slotName;
        this.slotId = slotId;
        this.slotActive = slotActive;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public boolean isSlotActive() {
        return slotActive;
    }

    public void setSlotActive(boolean slotActive) {
        this.slotActive = slotActive;
    }

    @SerializedName("slot_name")
    private String slotName;

    @SerializedName("slot_id")
    private int slotId;

    @SerializedName("slot_active")
    private boolean slotActive;
}
