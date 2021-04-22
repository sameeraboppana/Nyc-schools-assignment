package com.assignment.nycschools.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.assignment.nycschools.model.SchoolDetails;
import com.assignment.nycschools.model.SchoolSATStats;
import com.assignment.nycschools.retrofit.ApiRequest;
import com.assignment.nycschools.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolsRepository {
    private static final String TAG = SchoolsRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    final MutableLiveData<List<SchoolDetails>> schoolsData = new MutableLiveData<>();
    final MutableLiveData<List<SchoolSATStats>> statsData = new MutableLiveData<>();
    final MutableLiveData<Boolean> showErrorDialog = new MutableLiveData<>();

    public SchoolsRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        fetchRemoteDataSilently();
    }

    private void fetchRemoteDataSilently() {
        apiRequest.getSchoolsDetails()
                .enqueue(new Callback<List<SchoolDetails>>() {
                    @Override
                    public void onResponse(Call<List<SchoolDetails>> call, Response<List<SchoolDetails>> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                        if (response.body() != null) {
                            schoolsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SchoolDetails>> call, Throwable t) {
                        schoolsData.setValue(new ArrayList<>());
                        showErrorDialog.setValue(true);
                    }
                });

        apiRequest.getSchoolsSatStats()
                .enqueue(new Callback<List<SchoolSATStats>>() {
                    @Override
                    public void onResponse(Call<List<SchoolSATStats>> call, Response<List<SchoolSATStats>> response) {
                        Log.d(TAG, "onResponse response:: " + response);

                        if (response.body() != null) {
                            statsData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SchoolSATStats>> call, Throwable t) {
                        statsData.setValue(new ArrayList<>());
                    }
                });
    }

    public LiveData<List<SchoolDetails>> getSchoolsDetails() {
        return schoolsData;
    }

    public LiveData<List<SchoolSATStats>> getSchoolsSatStats() {
        return statsData;
    }

    public MutableLiveData<Boolean> getShowErrorDialog() {
        return showErrorDialog;
    }
}
