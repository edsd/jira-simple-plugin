package com.edsd.jira.plugins.simpleplugin.DAO.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.edsd.jira.plugins.simpleplugin.DAO.StudentDAO;
import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;
import com.edsd.jira.plugins.simpleplugin.logic.Student;
import java.util.Date;
import net.java.ao.Query;

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
                entity.setSurname(student.getSurname());
                entity.setProjectId(student.getProjectId());
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

    @Override
    public StudentEntity[] getStudentsForProject(final long projectId) throws Exception {
        return ao.executeInTransaction(new TransactionCallback<StudentEntity[]>() {
            @Override
            public StudentEntity[] doInTransaction() {

                return ao.find(StudentEntity.class, Query.select().order("NAME").where("PROJECT_ID=?", projectId));
            }
        });
    }

}
