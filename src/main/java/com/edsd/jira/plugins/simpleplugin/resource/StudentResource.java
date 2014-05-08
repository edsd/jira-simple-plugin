package com.edsd.jira.plugins.simpleplugin.resource;

import com.atlassian.jira.gadgets.system.AbstractResource;
import com.atlassian.jira.rest.v1.util.CacheControl;
import com.edsd.jira.plugins.simpleplugin.DAO.DAOFactory;
import com.edsd.jira.plugins.simpleplugin.entity.StudentEntity;
import com.edsd.jira.plugins.simpleplugin.resource.xml.Mapper;
import com.edsd.jira.plugins.simpleplugin.resource.xml.XmlStudent;
import com.edsd.jira.plugins.simpleplugin.resource.xml.XmlStudents;
import com.google.common.collect.Lists;
import java.util.List;
import javax.ws.rs.GET;
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
}
