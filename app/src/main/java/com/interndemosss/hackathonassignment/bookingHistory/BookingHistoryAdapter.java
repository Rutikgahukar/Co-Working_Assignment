package com.interndemosss.hackathonassignment.bookingHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.interndemosss.hackathonassignment.R;

import java.util.List;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.ViewHolder> {

    private List<Booking> deskItemList;

    public BookingHistoryAdapter(List<Booking> deskItemList) {
        this.deskItemList = deskItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookinghistory_sample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking deskItem = deskItemList.get(position);
        holder.deskIdValueTextView.setText(deskItem.getWorkspaceId());
        holder.nameValueTextView.setText(deskItem.getWorkspaceName());
        holder.bookedOnValueTextView.setText(deskItem.getBookingDate());
    }

    @Override
    public int getItemCount() {
        return deskItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deskIdValueTextView;
        public TextView nameValueTextView;
        public TextView bookedOnValueTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deskIdValueTextView = itemView.findViewById(R.id.deskIdValueTextView);
            nameValueTextView = itemView.findViewById(R.id.nameValueTextView);
            bookedOnValueTextView = itemView.findViewById(R.id.bookedOnValueTextView);
        }
    }
}
