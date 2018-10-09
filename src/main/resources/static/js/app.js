$(document).ready(function(){
    var tabel = $('#courseTable')
        .DataTable({
            "sAjaxSource" : "/getcourses",
            "sAjaxDataProp" : "",
            "columns" : [
                {
                    "data": "courseid",
                    "visible": false
                },
                {
                    "data": "coursecode"
                },
                {
                    "data": "name"
                },
                {
                    "data": "createdBy"
                },
                {
                    "data": "reviewaction",
                    "width": "7%",
                    "orderable": false,
                    "searchable": false,
                    "render": function(data, type, row, meta){
                        const a='<a href="/coursestudents/'+row.courseid+'">Review</a>';
                        return a;
                    }
                },
                {
                    "data": "reviews",
                    "width": "9%",
                    "searchable": false,
                    "render": function(data, type, row, meta){
                        const a='<a href="/questions/'+row.courseid+'">View Reviews</a>';;
                        return a;
                    }
                },
                {
                    "data": "editaction",
                    "orderable": false,
                    "searchable": false,
                    "width": "9%",
                    "render": function(data, type, row, meta){
                        const a='<a href="/editcourse/'+row.courseid+'">Edit Question</a>';
                        return a;
                    }
                }
            ]
        })
});