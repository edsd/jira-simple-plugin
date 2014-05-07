package com.edsd.jira.plugins.simpleplugin.logic.Impl;

import com.edsd.jira.plugins.simpleplugin.logic.Student;

public class StudentImpl implements Student {

    private String name;

    public StudentImpl(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
