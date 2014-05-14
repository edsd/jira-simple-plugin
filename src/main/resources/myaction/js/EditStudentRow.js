jQuery.namespace("JIRA.Admin.Student.EditStudentRow");

JIRA.Admin.Student.EditStudentRow = JIRA.RestfulTable.EditRow.extend({
    submit: function() {
        //Здесь можно добавить проверку введенных данных
        JIRA.RestfulTable.EditRow.prototype.submit.apply(this, arguments);
    },
    render: function() {

        var data = this.model.toJSON(),
                $el = this.$el;

        $el.html(JIRA.Templates.Student.editStudentRow({
            student: data
        }));

        return this;
    }
});

