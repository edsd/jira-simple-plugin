jQuery(function() {

    var $table = AJS.$("#project-config-students-table");

    var $project = AJS.$("meta[name=projectId]").attr("content");
    
    function getResourceURL() {
        return contextPath + "/rest/simple-api/1.0/project/" + $project + "/students";
    }

    function getStudent(callback) {
        JIRA.SmartAjax.makeRequest({
            url: getResourceURL(),
            complete: function(xhr, status, response) {
                if (response.successful) {
                    callback(response.data.students);
                } else {
                    $table.trigger("serverError",
                            [JIRA.SmartAjax.buildSimpleErrorContent(response)]);
                }
            }
        });
    }

    getStudent(function(students) {

        JIRA.Admin.StudentTable = new JIRA.RestfulTable({
            el: $table,
            url: getResourceURL(),
            editable: true,
            entries: students,
            noEntriesMsg: 'There are currently no students for this project.',
            views: {
                editRow: JIRA.Admin.Student.EditStudentRow,
                row: JIRA.Admin.Student.StudentRow
            }

        });

        jQuery(".jira-restfultable-init").remove();

        JIRA.userhover($table);
    });
});