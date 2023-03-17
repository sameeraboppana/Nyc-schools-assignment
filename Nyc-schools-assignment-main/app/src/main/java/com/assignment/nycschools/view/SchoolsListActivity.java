package com.assignment.nycschools.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.nycschools.R;
import com.assignment.nycschools.adapter.SchoolsDetailsAdapter;
import com.assignment.nycschools.model.SchoolDetails;
import com.assignment.nycschools.view_model.SchoolsViewModel;

import java.util.ArrayList;

public class SchoolsListActivity extends AppCompatActivity {

    private static final String TAG = SchoolsListActivity.class.getSimpleName();
    private RecyclerView schoolsRecyclerView;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private SchoolsDetailsAdapter adapter;
    private ArrayList<SchoolDetails> schoolDetailsList = new ArrayList<>();
    private SchoolsViewModel schoolsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        fetchSchools();
    }

    /**
     * initialization of views and others
     */
    private void initialization() {
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular);
        schoolsRecyclerView = (RecyclerView) findViewById(R.id.schools_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(SchoolsListActivity.this);
        schoolsRecyclerView.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        schoolsRecyclerView.setHasFixedSize(true);

        // adapter
        SchoolsDetailsAdapter.ClickListener listener = new SchoolsDetailsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //Navigating to the stats SAT page
                Intent intent = new Intent(SchoolsListActivity.this, SchoolDetailsActivity.class);
                intent.putExtra(SchoolDetailsActivity.SCHOOL_DBN, schoolDetailsList.get(position).dbn);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        };
        adapter = new SchoolsDetailsAdapter(schoolDetailsList, listener);
        schoolsRecyclerView.setAdapter(adapter);
        schoolsRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        // View Model
        schoolsViewModel = new ViewModelProvider(this).get(SchoolsViewModel.class);
    }

    /**
     * Loads the Schools details from API and updates the UI
     */
    private void fetchSchools() {
        schoolsViewModel.getShowErrorDialog().observe(this, showErrorPopUp -> {
            new AlertDialog.Builder(SchoolsListActivity.this)
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.error_desc)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        });
        schoolsViewModel.getSchoolsDetailsLiveData().observe(this, schoolsDetails -> {
            if (schoolsDetails != null) {
                progress_circular_movie_article.setVisibility(View.GONE);
                this.schoolDetailsList.addAll(schoolsDetails);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
