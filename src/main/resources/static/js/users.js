 (function($) {
    "use strict";

    // Add active state to sidbar nav links
    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
        $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
            if (this.href === path) {
                $(this).addClass("active");
            }
        });

    // Toggle the side navigation
    $("#sidebarToggle").on("click", function(e) {
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });

})(jQuery);

///////////
function createRowDataUser(user)
{
let rowData = [
	    user.username,
	    user.role,
	    `<button class="btn delete" data-toggle="modal"
         			data-target="#delModal" data-delid="${user.username}"
         			data-delname="${user.username}">
         			<i class="fas fa-trash" data-toggle="tooltip"
         				title="Delete"></i>
         		</button>`
	];
	return rowData;
}
function insertRowUser(user)
{
    let rowData = createRowDataUser(user);
	let trId = "tr_" + user.username
//	let row = $(document.getElementById(trId));
    let table = $("#dataTableUser").DataTable();
    let rowNode= table.row.add(rowData).draw().node();
    $(rowNode).attr('id', trId);
}

function deleteRow(trId, tableId) {
  	let rowDel = $(document.getElementById(trId));
  	let table = $("#"+tableId).DataTable();
  	table.row(rowDel).remove().draw();
  };

function delUser(id) {
	$.ajax({
		type : 'DELETE',
		url : "/user-api/delete/"+id,
        headers: {
                                									"X-CSRF-TOKEN": token
                                								},
		success : function(result, status) {
			/* alert("modify success!"); */
			deleteRow("tr_" + id, "dataTableUser");
			$('#delModal').modal('hide');
		},
		error : function(e) {
			alert("Modify not success!");
		}
	});

};

    //----------------DELETE---------------------
	$("#del_btn").click(function() {
		console.log("delete");
		let id = $("#delid").val();
		console.log("delete" + id);
		delUser(id);
	});


$(document)
.ready(
		function() {
		var token = $("meta[name='_csrf']").attr("content");
function validateForm()
 {
     let password = document.forms["password-form"]["newPassword"];
     let confirm = document.forms["password-form"]["confirmPassword"];

     console.log(password.value);
      console.log(confirm.value);
     if(password.value != confirm.value) {
     confirm.setCustomValidity("Mật Khẩu không khớp");
     return false;
      }
       confirm.setCustomValidity('');
      return true;

         let username =  document.forms["password-form"]["username"];

         $.ajax({
          					type : 'PUT',
          					headers: {
                                                    									"X-CSRF-TOKEN": token
                                                    								},
          					url : "/student-api/insert",
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

 }

 $("#btn-modal-insert").click(function()
 					{
 					console.log("Confirm");
                    let form =document.forms["user-create-form"];
                    let password = form["password"];
                    let confirm = form["confirm"];
                    let username =form["username"];
                    let role = form["campus"];

                     	if (!username.checkValidity()) {
                                                             alert("Username phải bắt đầu bằng chữ và chứa 3-16 kí tự");
                                                             return false;
                                                         }
                        if (!password.checkValidity()) {
                                                        alert("Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường, 1 số và ít nhất 8 kí tự");
                                                        return false;
                                                    }

                          if (!role.checkValidity()) {
                                                                                 alert("Vui lòng chọn cơ sở quản lí");
                                                                                 return false;
                                                                             }
                   if(password.value != confirm.value) {
                      //confirm.setCustomValidity("Mật Khẩu không khớp");
                      alert("Xác nhận mật khẩu không khớp");
                      return false;
                   }


                if (!$('#user-create-form').validate()){
                   // will also trigger unobtrusive validation only for this element if in place
                   // add your extra logic here to execute only when element is valid
                   return;
                }
 				let data ={
 						username: username.value,
 						role:role.value,
 						password:password.value,
 				};
 				console.log(data);


 				$.ajax({
 					type : 'PUT',
 					url : "/user-api/insert",
 					headers: {
                                            									"X-CSRF-TOKEN": token
                                            								},
 					data: JSON.stringify(data),
 					contentType : 'application/json',
 					dataType: 'json',
 					success : function(result, status) {
 						/* alert("modify success!"); */
 					    insertRowUser(result);
 						$('#modal-insert').modal('hide');
 						$("#user-create-form").trigger('reset');
 						  $(username).removeClass("is-invalid");
                         $(".input-group:has(.is-invalid)").removeClass('input-group-invalid');
 					},
 					error : function(xhr, status, error) {
 					alert("error"+error);
 					if(xhr.status == '400')
 					{
 					    $(username).addClass("is-invalid");
 					    $(".input-group:has(.is-invalid)").addClass('input-group-invalid');
 					}
// 					alert("Insert Error: "+ error);
                    return false;
 					}
 				});
 					});



		});