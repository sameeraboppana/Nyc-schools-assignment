package com.assignment.nycschools.view;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.ViewModelProvider;

import com.assignment.nycschools.R;
import com.assignment.nycschools.model.SchoolSATStats;
import com.assignment.nycschools.view_model.SchoolDetailsViewModel;

public class SchoolDetailsActivity extends AppCompatActivity {

    public static final String SCHOOL_DBN = "SCHOOL_DBN";
    private static final String TAG = SchoolDetailsActivity.class.getSimpleName();
    private ProgressBar progress_circular_movie_article;
    private SchoolDetailsViewModel schoolsViewModel;
    private String dbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_sat_stats);

        initialization();

        fetchSchools();
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        dbn = getIntent().getStringExtra(SCHOOL_DBN);
        progress_circular_movie_article = (ProgressBar) findViewById(R.id.progress_circular_movie_article);
        // View Model
        schoolsViewModel = new ViewModelProvider(this).get(SchoolDetailsViewModel.class);
    }

    /**
     * Loads the Schools details from API and updates the UI
     */
    private void fetchSchools() {
        schoolsViewModel.getShowErrorDialog().observe(this, showErrorPopUp -> {
            new AlertDialog.Builder(SchoolDetailsActivity.this)
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.error_desc)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        });
        schoolsViewModel.getSchoolsSatScoresLiveData().observe(this, schoolsDetails -> {
            if (schoolsDetails != null) {
                progress_circular_movie_article.setVisibility(View.GONE);
                for (SchoolSATStats stats : schoolsDetails) {
                    if (stats.dbn.equals(dbn)) {
                        updateUI(stats);
                        return;
                    }
                }
                Toast.makeText(SchoolDetailsActivity.this, R.string.no_stats, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void updateUI(SchoolSATStats satStats) {
        ((TextView) findViewById(R.id.tvTitle)).setText(satStats.school_name);
        ((TextView) findViewById(R.id.tv_num_of_sat_test_takers)).setText(String.format("%s%s", getString(R.string.stats), satStats.num_of_sat_test_takers));
        ((TextView) findViewById(R.id.tv_sat_critical_reading_avg_score)).setText(String.format("%s%s", getString(R.string.reading_label), satStats.sat_critical_reading_avg_score));
        ((TextView) findViewById(R.id.tv_sat_math_avg_score)).setText(String.format("%s%s", getString(R.string.amth_label), satStats.sat_math_avg_score));
        ((TextView) findViewById(R.id.tv_sat_writing_avg_score)).setText(String.format("%s%s", getString(R.string.writing_label), satStats.sat_writing_avg_score));
    }
}
