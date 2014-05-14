package com.edsd.jira.plugins.simpleplugin.DAO;

import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;
import com.edsd.jira.plugins.simpleplugin.logic.Student;

public interface StudentDAO {

    public StudentEntity addStudent(Student student) throws Exception;

    public StudentEntity[] getStudents() throws Exception;

    public StudentEntity[] getStudentsForProject(long projectId) throws Exception;

    public StudentEntity deleteStudent(long id) throws Exception;

    public StudentEntity updateStudent(long id, Student student) throws Exception;

}
