package com.edsd.jira.plugins.simpleplugin.resource.xml;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlStudents {

    @XmlElement
    public long pid;
    @XmlElement
    public long count;
    @XmlElement
    public List<XmlStudent> students;

    public XmlStudents(long pid, long count, List<XmlStudent> students) {
        this.pid = pid;
        this.count = count;
        this.students = students;
    }

}
