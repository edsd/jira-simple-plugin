jQuery.namespace("JIRA.Admin.Student.StudentRow");

JIRA.Admin.Student.StudentRow = JIRA.RestfulTable.Row.extend({
    initialize: function() {
        JIRA.RestfulTable.Row.prototype.initialize.apply(this, arguments);
        
        this.events["click .project-config-student-delete"] = "_delete";
        this.delegateEvents();
    },
    _delete: function(e) {
        if (!confirm('Want to delete the student?'))
            return;
        this.model.destroy({data: this.model.toJSON()});
        e.preventDefault();
    },
    render: function() {

        var data = this.model.toJSON(),
                id = this.model.get("id"),
                $el = this.$el;

        $el.attr("id", "student-" + id + "-row").attr("data-id", id);

        $el.html(JIRA.Templates.Student.studentRow({
            student: data
        }));

        return this;
    }

});
