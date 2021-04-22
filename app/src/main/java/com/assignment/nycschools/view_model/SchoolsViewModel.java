package com.assignment.nycschools.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.assignment.nycschools.NYCSchoolApp;
import com.assignment.nycschools.model.SchoolDetails;

import java.util.List;

public class SchoolsViewModel extends AndroidViewModel {

    private LiveData<List<SchoolDetails>> schoolsDetailsLiveData;
    private LiveData<Boolean> showErrorDialog;

    public SchoolsViewModel(@NonNull Application application) {
        super(application);
        this.schoolsDetailsLiveData = ((NYCSchoolApp) application).getSchoolsRepository().getSchoolsDetails();
        this.showErrorDialog = ((NYCSchoolApp)application).getSchoolsRepository().getShowErrorDialog();
    }

    public LiveData<List<SchoolDetails>> getSchoolsDetailsLiveData() {
        return schoolsDetailsLiveData;
    }

    public LiveData<Boolean> getShowErrorDialog() {
        return showErrorDialog;
    }
}
