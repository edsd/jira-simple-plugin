jQuery.namespace("JIRA.Admin.Student.StudentRow");

JIRA.Admin.Student.StudentRow = JIRA.RestfulTable.Row.extend({
    initialize: function() {
        JIRA.RestfulTable.Row.prototype.initialize.apply(this, arguments);
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
