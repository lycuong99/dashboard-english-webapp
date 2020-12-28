// Thao Tac Tren table

function deleteRow(trId) {
	let rowDel = $(document.getElementById(trId));
	let table = $("#dataTableStudent_FullCourse").DataTable();
	table.row(rowDel).remove().draw();
};

function createRowDataStudent(student)
{
let rowData = [
        student.id,
        student.name,
        student.nameParent,
        student.className,
        student.course,
        student.fee,
        student.bOd,
        student.dateRegis,
        student.dateStart,
        student.dateEnd,
        student.phone,
	    `<button class="btn delete" data-toggle="modal"
         			data-target="#delModal" data-delid="${student.id}"
         			data-delname="${student.name}">
         			<i class="fas fa-trash" data-toggle="tooltip"
         				title="Delete"></i>
         		</button>

         		<button class="btn button-style view" data-toggle="modal"
         			 data-student-id="${student.id}"
         			data-target="#modal-edit" type="button">
         			<i class="fas fa-pen" style="color: blue;"></i>
         		</button>`
	];
	return rowData;
}

function insertRowStudent(student)
{
    let rowData = createRowDataStudent(student);
	let trId = "tr_" + student.id
//	let row = $(document.getElementById(trId));
    let table = $("#dataTableStudent_FullCourse").DataTable();
    let rowNode= table.row.add(rowData).draw().node();
    $(rowNode).attr('id', trId);

}

function editRowStudent(student)
{

	let trId = "tr_" + student.id;
	let table = $("#dataTableStudent_FullCourse").DataTable();
	let rowData = createRowDataStudent(student);
	table.row($(document.getElementById(trId))).data(rowData).draw();
}
// Nap Data to modal update
function showDataToEditModal(student)
{
	let form = document.getElementById("student-edit-form");
	setDataToForm(form,"id", student.id);
	setDataToForm(form,"name", student.name);
	setDataToForm(form,"nameParent", student.nameParent);

	setDataToForm(form,"className", student.className);
	setDataToForm(form,"course", student.course);
	setDataToForm(form,"phone", student.phone);
//	setDataToForm(form,"note", student.note);
//	console.log("BOD:"+student.bOd);
	setDataToForm(form,"bOd", student.bOd);
	setDataToForm(form,"dateRegis", student.dateRegis);
	setDataToForm(form,"dateStart", student.dateStart);
	setDataToForm(form,"dateEnd", student.dateEnd);
}


//		--------------- VALIDATE ------------------
    function isValidateForm(formId)
    {
        var form = document.forms[formId];

        var inputName = form["name"];
        var inputPhone = form["phone"];

       	if (!inputName.checkValidity()) {
            alert("Vui lòng điền tên học sinh");
            return false;
        }

       	if (!inputPhone.checkValidity()) {
            alert("SĐT phải bao gồm 10 số theo format xxx-xxx-xxxx");
            return false;
        }

        return true;
    }


$(document)
.ready(
		function() {
			var token = $("meta[name='_csrf']").attr("content");
            const API_CONTEXT = '/full-courses-student-api';

// DELETE
		function delStudent(id) {
        		$.ajax({
        		headers: {
                        "X-CSRF-TOKEN": token
                        },
                type : 'DELETE',
                url : API_CONTEXT+"/delete/" + id,
                success : function(result, status) {
                    /* alert("modify success!"); */
                    deleteRow("tr_" + id);
                    $('#delModal')
                    .modal(
                            'hide');
                },
                error : function(e) {
                    alert("Modify not success!");
                }
        		});
        };

        $("#del_btn").click(
                function()
                {
                    console.log("delete");
                    let id = $("#delid").val();
                    console.log("delete"+ id);
                    delStudent(id);
                });

        $("#btn-modal-insert").click(function()
        {

         if(isValidateForm('student-create-form') == false) return;

        let formData = new FormData($('form#student-create-form')[0]);
        let data ={
                name: formData.get('name'),
                nameParent:formData.get('nameParent'),
                className:formData.get('className'),
                phone:formData.get('phone'),
                course:formData.get('course'),
                fee:formData.get('fee'),
                dateStart:formData.get('dateStart'),
                dateEnd:formData.get('dateEnd'),
                dateRegis:formData.get('dateRegis'),
                bOd:formData.get('bOd'),
                campus:formData.get('campus'),
                note:formData.get('note')
        };
        console.log(data);

        $.ajax({
            type : 'POST',
            headers: {
                     "X-CSRF-TOKEN": token
                    },
            url : API_CONTEXT+ "/insert",
            data: JSON.stringify(data),
            contentType : 'application/json',
            dataType: 'json',
            success : function(result, status) {
                /* alert("modify success!"); */
            insertRowStudent(result);

            $('#modal-insert').modal('hide');
            $("#student-create-form").trigger('reset');
            },
            error : function(e) {
                alert("Modify not success!");
            }
        });
            });

        $('#modal-edit').on('show.bs.modal', function(event) {
            modalLoading($('#modal-edit'), false);

            let editbutton = $(event.relatedTarget);
            let studentId = editbutton.data('student-id');

            console.log("get data");

            $.ajax({
                type : 'GET',
                headers: {
                                                    "X-CSRF-TOKEN": token
                                                },
                url : API_CONTEXT+ "/get/"+studentId,
                dataType: 'json',
                success : function(result, status) {
                    /* alert("modify success!"); */
                    console.log(result);
                showDataToEditModal(result);
                modalLoading($('#modal-edit'), true);
                    $('#modal-edit').modal('show');
                },
                error : function(e) {
                    alert("Cant load data!");
                }
            });

        });
        $("#btn-modal-update").click(function()
        {
            let formId = 'student-edit-form';
             if(isValidateForm('student-edit-form') == false) return;

            let formData = new FormData($('form#student-edit-form')[0]);
            let data ={
                   id:formData.get('id'),
                   name: formData.get('name'),
                   nameParent:formData.get('nameParent'),
                   className:formData.get('className'),
                   phone:formData.get('phone'),
                   course:formData.get('course'),
                   fee:formData.get('fee'),
                   dateStart:formData.get('dateStart'),
                   dateEnd:formData.get('dateEnd'),
                   dateRegis:formData.get('dateRegis'),
                   bOd:formData.get('bOd'),
                   campus:formData.get('campus'),
                   note:formData.get('note')
            };
            console.log(data);


            $.ajax({
                type : 'PUT',
                headers: {
                            "X-CSRF-TOKEN": token
                        },
                url : API_CONTEXT + "/update",
                data: JSON.stringify(data),
                contentType : 'application/json',
                dataType: 'json',
                success : function(result, status) {
                    /* alert("modify success!"); */
                    editRowStudent(result);
                    $('#modal-edit').modal('hide');
                    $("#student-edit-form").trigger('reset');
                },
                error : function(e) {
                    console.log(e);
                    alert("Modify not success!"+ e);
                }
            });
        });
    });

