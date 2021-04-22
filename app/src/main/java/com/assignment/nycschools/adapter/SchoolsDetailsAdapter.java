package com.assignment.nycschools.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.nycschools.R;
import com.assignment.nycschools.model.SchoolDetails;

import java.util.ArrayList;
import java.util.List;

public class SchoolsDetailsAdapter extends RecyclerView.Adapter<SchoolsDetailsAdapter.ViewHolder> {
    private List<SchoolDetails> schoolDetailsList;
    private ClickListener listener;

    public SchoolsDetailsAdapter(ArrayList<SchoolDetails> schoolDetailsList, ClickListener listener) {
        this.schoolDetailsList = schoolDetailsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SchoolsDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_school, viewGroup, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolsDetailsAdapter.ViewHolder viewHolder, int i) {
        SchoolDetails schoolDetails = schoolDetailsList.get(i);
        viewHolder.tvTitle.setText(schoolDetails.school_name);
        viewHolder.tvWebsite.setText(schoolDetails.website);
        viewHolder.tvLocation.setText(schoolDetails.location);
        viewHolder.tvSchoolEmail.setText(schoolDetails.school_email);
        viewHolder.tvDescription.setText(schoolDetails.overview_paragraph);
    }

    @Override
    public int getItemCount() {
        return schoolDetailsList.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvSchoolEmail;
        private final TextView tvWebsite;
        private final TextView tvLocation;
        private final TextView tvDescription;
        private ClickListener clickListener;

        public ViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSchoolEmail = (TextView) itemView.findViewById(R.id.tvSchoolEmail);
            tvWebsite = (TextView) itemView.findViewById(R.id.tvWebsite);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
            tvDescription = (TextView) itemView.findViewById(R.id.tvOverView);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this::onClick);
            itemView.setOnLongClickListener(this::onLongClick);
        }

        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

}
