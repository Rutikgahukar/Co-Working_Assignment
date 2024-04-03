package com.interndemosss.hackathonassignment.calenderSlot;

import com.google.gson.annotations.SerializedName;
import com.interndemosss.hackathonassignment.calenderSlot.slots;

import java.util.List;

public class SlotResponce {
    @SerializedName("slots")
    private List<slots> slots;

    public List<com.interndemosss.hackathonassignment.calenderSlot.slots> getSlots() {
        return slots;
    }

    public void setSlots(List<com.interndemosss.hackathonassignment.calenderSlot.slots> slots) {
        this.slots = slots;
    }
}
