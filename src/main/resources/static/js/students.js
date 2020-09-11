

function deleteRow(trId) {
	let rowDel = $(document.getElementById(trId));
	let table = $("#dataTableStudent").DataTable();
	table.row(rowDel).remove().draw();
	
};

function delStudent(id) {

		$.ajax({
			type : 'DELETE',
			url : "/student-api/delete/" + id,
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

function createRowStudent(student)
{
	let row = `<tr id="${'tr_'+student.id}">
		<td >${student.id}</td>
		<td >${student.name}</td>
		<td >${student.nameParent}</td>
		<td >${student.className}</td>
		<td >${student.bOd}</td>
		<td >${student.phone}</td>
		<td >${student.admissionDate}</td>
		<td style="wid 130px;">
				<button class="btn delete" data-toggle="modal"
			data-target="#delModal" th:data-delid="${student.id}"
			th:data-delname="${student.name}">
			<i class="fas fa-trash" data-toggle="tooltip"
				title="Delete"></i>
		</button>
		<form action="/courses" class="form-inline" style="display: inline;">
		<input type="hidden" name="id" th:value="${student.id}"/>
		<button class="btn button-style view" onclick=""  type="submit">
			<i class="far fa-eye"></i>
		</button>
		</form>	
		
		<button class="btn button-style view" data-toggle="modal"
			 th:data-student-id="${student.id}"
			data-target="#modal-edit" type="button">
			<i class="fas fa-pen" style="color: blue;"></i>
		</button>
	</tr>`;
	return row;
}

function insertRowStudent(student)
{
	let row =  createRowStudent(student);
	$("#dataTableStudent").find("tbody").prepend(row);
		
}
function editRowStudent(student)
{
	let row_old = $(document.getElementById("tr_"+student.id));
	let row_new =createRowStudent(student);
	row_old.replaceWith(row_new);
}

function setDatatoForm(form,name, value)
{
	if(value==null){
		 value="";
	};
	form.elements.namedItem(name).value = value;
}

function showDataToEditModal(student)
{
	let form = document.getElementById("student-edit-form");
	setDatatoForm(form,"id", student.id);
	setDatatoForm(form,"name", student.name);
	setDatatoForm(form,"className", student.className);
	setDatatoForm(form,"phone", student.phone);
	setDatatoForm(form,"nameParent", student.nameParent);
	setDatatoForm(form,"note", student.note);
	console.log("BOD:"+student.bOd);
	setDatatoForm(form,"bOd", student.bOd);
}




$(document)
.ready(
		function() {
			
		/* $("#dataTableStudent").DataTable(); */
			$("#del_btn").click(
					function()
					{
						console.log("delete");
						let id = $("#delid").val();
						console.log("delete"+ id);
						delStudent(id);
					});	
			
// Insert Student
			$("#btn-modal-insert").click(function()
					{
				let formData = new FormData($('form#student-create-form')[0]);
				let data ={
						name: formData.get('name'),
						nameParent:formData.get('nameParent'),
						className:formData.get('className'),
						phone:formData.get('phone'),
						bOd:formData.get('bOd'),
						campus:formData.get('campus'),
						note:formData.get('note')
				};
				console.log(data);
				
				
				$.ajax({
					type : 'PUT',
					url : "/student-api/insert/",
					data: JSON.stringify(data),
					contentType : 'application/json',
					dataType: 'json',
					success : function(result, status) {
						/* alert("modify success!"); */
					insertRowStudent(result);
						$('#modal-insert').modal('hide');
					},
					error : function(e) {
						alert("Modify not success!");
					}
				});
					});
			
			$('#modal-edit').on('show.bs.modal', function(event) {
				let editbutton = $(event.relatedTarget);
				let studentId = editbutton.data('student-id');
				var delmodal = $(this);
				
				console.log("get data");
				
				$.ajax({
					type : 'GET',
					url : "/student-api/get/"+studentId,
					dataType: 'json',
					success : function(result, status) {
						/* alert("modify success!"); */
						console.log(result);
					showDataToEditModal(result);
						$('#modal-edit').modal('show');
					},
					error : function(e) {
						alert("Cant load data!");
					}
				});

			});
			
			$("#btn-modal-update").click(function()
					{
				let formData = new FormData($('form#student-edit-form')[0]);
				let data ={
						id:formData.get('id'),
						name: formData.get('name'),
						nameParent:formData.get('nameParent'),
						className:formData.get('className'),
						phone:formData.get('phone'),
						bOd:formData.get('bOd'),
						campus:formData.get('campus'),
						note:formData.get('note')
				};
				console.log(data);
				
				
				$.ajax({
					type : 'POST',
					url : "/student-api/update",
					data: JSON.stringify(data),
					contentType : 'application/json',
					dataType: 'json',
					success : function(result, status) {
						/* alert("modify success!"); */
					editRowStudent(result);
						$('#modal-edit').modal('hide');
					},
					error : function(e) {
						console.log(e);
						alert("Modify not success!"+ e);
					}
				});
					});
		
		});


