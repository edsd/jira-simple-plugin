package com.edsd.jira.plugins.simpleplugin.entity;

import com.edsd.jira.plugins.simpleplugin.logic.Student;
import java.util.Date;
import net.java.ao.Entity;

public interface StudentEntity extends Entity, Student {

    public Date getCreated();

    public void setCreated(Date created);
}
