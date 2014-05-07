package com.edsd.jira.plugins.simpleplugin.DAO.Impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.edsd.jira.plugins.simpleplugin.DAO.StudentDAO;
import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;
import com.edsd.jira.plugins.simpleplugin.logic.Student;
import java.util.Date;

public class StudentDAOImpl implements StudentDAO {

    private final ActiveObjects ao;

    public StudentDAOImpl(ActiveObjects ao) {
        this.ao = ao;
    }

    @Override
    public StudentEntity addStudent(final Student student) throws Exception {

        return ao.executeInTransaction(new TransactionCallback<StudentEntity>() {
            @Override
            public StudentEntity doInTransaction() {

                StudentEntity entity = ao.create(StudentEntity.class);

                entity.setName(student.getName());
                entity.setCreated(new Date(System.currentTimeMillis()));
                
                entity.save();

                return entity;
            }
        });
    }

    @Override
    public StudentEntity[] getStudents() throws Exception {

        return ao.executeInTransaction(new TransactionCallback<StudentEntity[]>() {
            @Override
            public StudentEntity[] doInTransaction() {

                return ao.find(StudentEntity.class);
            }
        });
    }

}
