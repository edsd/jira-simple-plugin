package com.edsd.jira.plugins.simpleplugin.logic.Impl;

import com.edsd.jira.plugins.simpleplugin.logic.Student;

public class StudentImpl implements Student {

    private String name;
    private String surname;
    private long projectId;

    public StudentImpl(String name, String surname, long projectId) {
        this.name = name;
        this.surname = surname;
        this.projectId = projectId;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public long getProjectId() {
        return projectId;
    }

}
