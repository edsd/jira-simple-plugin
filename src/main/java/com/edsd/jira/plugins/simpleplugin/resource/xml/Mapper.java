package com.edsd.jira.plugins.simpleplugin.resource.xml;

import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;

public class Mapper {

    public static XmlStudent toXmlStudent(StudentEntity entity) {
        
        XmlStudent student = new XmlStudent();
        
        student.id = entity.getID();
        student.pid = entity.getProjectId();
        student.name = entity.getName();
        student.surname = entity.getSurname();
        student.created = entity.getCreated().toString();
        
        return student;
    }
}
