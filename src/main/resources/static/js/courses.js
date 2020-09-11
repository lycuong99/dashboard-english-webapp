  function deleteRow(trId, tableId) {       	
  	let rowDel = $(document.getElementById(trId));
  	let table = $("#"+tableId).DataTable();
  	table.row(rowDel).remove().draw();
  	
  };

function delStudent(id) {
	$.ajax({
		type : 'DELETE',
		url : "/course-api/delete/" + id,
		success : function(result, status) {
			/* alert("modify success!"); */
			deleteRow("tr_" + id, "dataTableCourse");
			$('#delModal').modal('hide');
		},
		error : function(e) {
			alert("Modify not success!");
		}
	});

};
$(document).ready(function() {
	
	$("#del_btn").click(function() {
		console.log("delete");
		let id = $("#delid").val();
		console.log("delete" + id);
		delStudent(id);
	});
});