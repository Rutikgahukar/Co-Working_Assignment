package com.interndemosss.hackathonassignment.bookingHistory;

import com.google.gson.annotations.SerializedName;

public class Booking {
    @SerializedName("workspace_name")
    private String workspaceName;

    @SerializedName("workspace_id")
    private int workspaceId;

    public String getWorkspaceName() {
        return workspaceName;
    }

    public void setWorkspaceName(String workspaceName) {
        this.workspaceName = workspaceName;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @SerializedName("booking_date")
    private String bookingDate;
}
