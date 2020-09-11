

function deleteRow(trId) {
	$(document.getElementById(trId)).remove();
};

function delBook(id) {

	var isDelete = confirm("Do you want to delete this book? ");
	if (isDelete == true) {
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
	}

};


$(document)
.ready(
		function() {
			

			$("#del_btn").click(
					function()
					{
						console.log("delete");
						let id = $("#delid").value();
						console.log("delete"+ id);
						delBook(id);
					});	
		});
