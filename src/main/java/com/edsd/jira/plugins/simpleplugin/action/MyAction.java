package com.edsd.jira.plugins.simpleplugin.action;

import com.atlassian.jira.project.Project;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.edsd.jira.plugins.simpleplugin.DAO.DAOFactory;
import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;
import com.edsd.jira.plugins.simpleplugin.logic.impl.StudentImpl;
import com.edsd.jira.plugins.simpleplugin.logic.Student;
import webwork.action.ServletActionContext;

public class MyAction extends JiraWebActionSupport {

    private Project project;
    private StudentEntity[] students;

    @Override
    public String execute() throws Exception {

        project = getSelectedProjectObject();

        request.setAttribute("com.atlassian.jira.projectconfig.util.ServletRequestProjectConfigRequestCache:project", project);

        students = DAOFactory.getInstance().getStudentDAO().getStudentsForProject(project.getId());

        return super.execute();

    }

    public String doAdd() throws Exception {

        String name = request.getParameterValues("name")[0];
        String surname = request.getParameterValues("surname")[0];
        long projectId = Long.parseLong(request.getParameterValues("pid")[0]);

        Student student = new StudentImpl(name, surname, projectId);

        DAOFactory.getInstance().getStudentDAO().addStudent(student);

        ServletActionContext.getResponse().sendRedirect("/secure/action.jspa");

        return NONE;
    }

    public Project getProject() {
        return project;
    }

    public StudentEntity[] getStudents() {
        return students;
    }

}
