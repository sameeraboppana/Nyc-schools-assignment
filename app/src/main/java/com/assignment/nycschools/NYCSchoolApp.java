package com.assignment.nycschools;

import android.app.Application;

import com.assignment.nycschools.repository.SchoolsRepository;

public class NYCSchoolApp extends Application {
    private SchoolsRepository schoolsRepository;
    private static NYCSchoolApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.schoolsRepository = new SchoolsRepository();
    }

    public static NYCSchoolApp getInstance() {
        return instance;
    }

    public SchoolsRepository getSchoolsRepository() {
        return schoolsRepository;
    }
}
