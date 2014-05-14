package com.edsd.jira.plugins.simpleplugin.resource;

import com.atlassian.jira.gadgets.system.AbstractResource;
import com.atlassian.jira.rest.v1.util.CacheControl;
import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.edsd.jira.plugins.simpleplugin.DAO.DAOFactory;
import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;
import com.edsd.jira.plugins.simpleplugin.logic.Student;
import com.edsd.jira.plugins.simpleplugin.logic.impl.StudentImpl;
import com.edsd.jira.plugins.simpleplugin.resource.xml.Mapper;
import com.edsd.jira.plugins.simpleplugin.resource.xml.XmlStudent;
import com.edsd.jira.plugins.simpleplugin.resource.xml.XmlStudents;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("project/{pid}/students")
@Produces({"application/json"})
public class StudentResource extends AbstractResource {

    @GET
    public Response getStudents(@PathParam("pid") String pid, @QueryParam("id") String id) throws Exception {

        long projectId = Long.parseLong(pid);

        List<XmlStudent> xmlStudents = Lists.newArrayList();

        for (StudentEntity student : DAOFactory.getInstance().getStudentDAO().getStudentsForProject(projectId)) {
            xmlStudents.add(Mapper.toXmlStudent(student));
        }

        return Response.ok(new XmlStudents(projectId, xmlStudents.size(), xmlStudents)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @POST
    public Response addStudent(@PathParam("pid") String pid, String request) throws Exception {

        long projectId = Long.parseLong(pid);

        JSONObject json = new JSONObject(request);
        String name = json.getString("name");
        String surname = json.getString("surname");
        Student student = new StudentImpl(name, surname, projectId);

        StudentEntity studentEntity = DAOFactory.getInstance().getStudentDAO().addStudent(student);

        return Response.ok(Mapper.toXmlStudent(studentEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRule(@PathParam("id") String idStr) throws Exception {

        long id = Long.parseLong(idStr);

        StudentEntity studentEntity = DAOFactory.getInstance().getStudentDAO().deleteStudent(id);

        return Response.ok(Mapper.toXmlStudent(studentEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRule(@PathParam("id") String idStr, String request) throws Exception {

        long id = Long.parseLong(idStr);

        JSONObject json = new JSONObject(request);

        String name;
        try {
            name = json.getString("name");
        } catch (JSONException ex) {
            name = null;
        }

        String surname;
        try {
            surname = json.getString("surname");
        } catch (JSONException ex) {
            surname = null;
        }

        Student student = new StudentImpl(name, surname, -1L);

        StudentEntity studentEntity = DAOFactory.getInstance().getStudentDAO().updateStudent(id, student);

        return Response.ok(Mapper.toXmlStudent(studentEntity)).cacheControl(CacheControl.NO_CACHE).build();
    }
}
