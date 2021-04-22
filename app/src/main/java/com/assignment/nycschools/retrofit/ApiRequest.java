package com.assignment.nycschools.retrofit;

import com.assignment.nycschools.model.SchoolDetails;
import com.assignment.nycschools.model.SchoolSATStats;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("resource/s3k6-pzi2.json")
    Call<List<SchoolDetails>> getSchoolsDetails();

    @GET("resource/f9bf-2cp4.json")
    Call<List<SchoolSATStats>> getSchoolsSatStats();
}
