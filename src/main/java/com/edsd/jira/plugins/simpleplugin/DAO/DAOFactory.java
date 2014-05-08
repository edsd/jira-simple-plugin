package com.edsd.jira.plugins.simpleplugin.DAO;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.edsd.jira.plugins.simpleplugin.DAO.impl.StudentDAOImpl;

public class DAOFactory {

    private static StudentDAO studentDAO = null;
    private static DAOFactory instance = null;
    private static ActiveObjects ao;

    public DAOFactory(ActiveObjects ao) {
        DAOFactory.ao = ao;
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory(ao);
        }
        return instance;
    }

    public StudentDAO getStudentDAO() {
        if (studentDAO == null) {
            studentDAO = new StudentDAOImpl(ao);
        }

        return studentDAO;
    }
}
