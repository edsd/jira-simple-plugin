package com.edsd.jira.plugins.simpleplugin.resource.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlStudent {

    @XmlElement
    public long id;
    @XmlElement
    public String name;
    @XmlElement
    public String surname;
    @XmlElement
    public long pid;
    @XmlElement
    public String created;

}
