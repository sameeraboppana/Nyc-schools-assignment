package com.assignment.nycschools.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.assignment.nycschools.NYCSchoolApp;
import com.assignment.nycschools.model.SchoolSATStats;

import java.util.List;

public class SchoolDetailsViewModel extends AndroidViewModel {

    private LiveData<Boolean> showErrorDialog;
    private LiveData<List<SchoolSATStats>> schoolsSatScoresLiveData;

    public SchoolDetailsViewModel(@NonNull Application application) {
        super(application);
        this.schoolsSatScoresLiveData = ((NYCSchoolApp)application).getSchoolsRepository().getSchoolsSatStats();
        this.showErrorDialog = ((NYCSchoolApp)application).getSchoolsRepository().getShowErrorDialog();
    }

    public LiveData<List<SchoolSATStats>> getSchoolsSatScoresLiveData() {
        return schoolsSatScoresLiveData;
    }

    public LiveData<Boolean> getShowErrorDialog() {
        return showErrorDialog;
    }
}
