  
//////////////////////////////////////////////////        DATE FUNCTION         ////////////////////////////////////////////////
$(".inputRangeDate").daterangepicker({
    autoUpdateInput: false,
    locale: {
        cancelLabel: 'Clear'
    }
});

$(".inputRangeDate").on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
});

$(".inputRangeDate").on('cancel.daterangepicker', function(ev, picker) {
    $(this).val('');
});


function getSchedules(form) {
    let schedules = [];
    $.each($(`#${form}`).find("input[name='schedule']:checked"), function () {
        schedules.push(parseInt($(this).val()));
    });
    return schedules;
}

function getFormValue(formId, nameField) {
    let form = $(`#${formId}`);
    return form.find(`input[name="${nameField}"]`).val();
}

$("#calDateSelect").change(function () {
    var valSelect = $(this).val();
    if (valSelect == 1) {
        $("#select1").show();
        $("#select2").hide();
    } else {
        $("#select1").hide();
        $("#select2").show();
    }
});

$("#calDateSelect_edit").change(function () {
    var valSelect = $(this).val();
    if (valSelect == 1) {
        $("#select1_edit").show();
        $("#select2_edit").hide();
    } else {
        $("#select1_edit").hide();
        $("#select2_edit").show();
    }
});



function getStartDayReal(startDay, schedules) {
    if (schedules.includes(startDay)) {
        return startDay;
    } else {
        //return first day in schedule in next week after startDay
        if (startDay > schedules[schedules.length - 1]) {
            return schedules[0];
        }

        for (let i = 0; i < schedules.length; i++) {
            //return first day in schedule after startDay
            if (startDay < schedules[i]) {
                return schedules[i];
            }
        }

    }
}
function getEndDay(startDay, schedules, totalDay)
{
  let startIndex = schedules.indexOf(startDay);
  let endIndex = (startIndex + totalDay - 1)%schedules.length;
  console.log('getEndDay:'+ endIndex)
  return schedules[endIndex];
}
function getNoWeek(startDay, schedules, totalDay)
{
    let startIndex = schedules.indexOf(startDay);
    let noWeek = Math.floor((startIndex + totalDay - 1)/schedules.length);
    console.log('getNoWeek:'+ noWeek)
    return noWeek;
}



function getEndDate(form) {

     let startDate = form.find('.startDateSelect').data('daterangepicker').startDate.toDate();

    if(form.find('.startDateSelect').val()==""){
        return null;
    } 
     //get Day in Week of startDate
     let dayOfStartDate = startDate.getDay();
    //  console.log(dayOfStartDate);
 
     let totalLesson = parseInt(getFormValue(form.attr("id"), 'totalLesson'));
     let schedules = getSchedules(form.attr("id"));
     schedules.sort();
     let totalLessonInWeek = schedules.length;
    //  console.log('totalLesson:'+totalLesson);
    //  console.log(schedules);
     
     if (totalLessonInWeek > 0 && totalLesson > 0 && totalLesson >= totalLessonInWeek) {
         let startDay =  getStartDayReal(dayOfStartDate, schedules);
         let endDayInWeek = getEndDay(startDay, schedules, totalLesson);
         console.log(startDay + " --> " +endDayInWeek);
         let endDate;
 
         let noWeek = getNoWeek(startDay, schedules, totalLesson);
 
         // Case startDate in nextweek - dayOfStartDate
         if(dayOfStartDate > startDay)
         {
             noWeek++;
         }
         
         console.log("Week: "+noWeek);
 
         let starDateMoment = moment(startDate);          
         endDate = starDateMoment.add(endDayInWeek - dayOfStartDate + 7*noWeek, 'days');
 
         console.log(endDate.format('YYYY-MM-DD'));
         return endDate;
     }
      return null;
    }
//function change Endate
function updateEndDate(form) {
    let endDate = getEndDate(form);
    if(endDate!= null)
    {
        form.find('.endDateResult').text(endDate.format("DD/MM/YYYY"));
    }else{
     form.find('.endDateResult').text("Ngày kết thúc");
    }
}
///////////////////////        THAO TAC VOI ROW         ////////////////////////////


function deleteRow(trId, tableId) {       	
  	let rowDel = $(document.getElementById(trId));
  	let table = $("#"+tableId).DataTable();
  	table.row(rowDel).remove().draw();
  	
  };

function delStudent(id) {
	$.ajax({
	headers: {
    									"X-CSRF-TOKEN": token
    								},
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


function createRowDataCourse(course)
{
let rowData = [
	    course.id,
	    course.name,
	    course.fee,
	    course.totalLesson,
	    course.dateRegis,
	    course.dateStart,
	    course.dateEnd,
	    `<button class="btn delete" data-toggle="modal"
                                             data-target="#delModal" data-delid="${course.id}"
                                             data-delname="${course.name}">
                                         <i class="fas fa-trash" data-toggle="tooltip"
                                            title="Delete"></i>
                                     </button>

                                     <button class="btn button-style view" data-toggle="modal"
                                             data-course-id="${course.id}"
                                             data-target="#modal-edit" type="button">
                                         <i class="fas fa-pen" style="color: blue;"></i>
                                     </button>`
	];
	return rowData;
}

function insertRowCourse(course)
{
//	let row =  createRowStudent(student);
//	$("#dataTableStudent").find("tbody").prepend(row);
    let rowData = createRowDataCourse(course);
	let trId = "tr_" + course.id
//	let row = $(document.getElementById(trId));
    let table = $("#dataTableCourse").DataTable();
    let rowNode= table.row.add(rowData).draw().node();
    $(rowNode).attr('id', trId);

}
function editRowCourse(course)
{
	let trId = "tr_" + course.id;
	let table = $("#dataTableCourse").DataTable();
	let rowData = createRowDataCourse(course);
	table.row($(document.getElementById(trId))).data(rowData).draw();
}


function showDataToEditModal(course)
{
    $("#course-edit-form").trigger('reset');

    const dateFormat = "DD/MM/YYYY";
	let form = document.getElementById("course-edit-form");
	 $(form).find('.endDateResult').text("Ngày kết thúc");

	setDataToForm(form,"id", course.id);
	setDataToForm(form,"name", course.name);
    setDataToForm(form,"name", course.name);
    setDataToForm(form,"classCourse", course.classCourse);
    setDataToForm(form,"fee", course.fee);
    setDataToForm(form,"note", course.note);
    setDataToForm(form,"dateRegis", course.dateRegis);
    setDataToForm(form,"totalLesson", course.totalLesson);

    let schedules = course.schedules;

     $(form).find("input[name='schedule']").removeAttr('checked');
        $.each($(form).find("input[name='schedule']:checked"), function () {
            $(this).prop('checked',false);
        });

     if(course.schedules === null || course.schedules.length == 0)
     {
           $("#calDateSelect_edit").val(1).change();
          $(form).find('.inputRangeDate').data('daterangepicker').startDate = moment(course.dateStart, dateFormat);
          $(form).find('.inputRangeDate').data('daterangepicker').endDate = moment(course.dateEnd, dateFormat);
          $(form).find('.inputRangeDate').val(course.dateStart + ' - ' +  course.dateEnd);
     }else
     {
     $("#calDateSelect_edit").val(2).change();
     $(form).find("input[name='startDate']").data('daterangepicker').startDate = moment(course.dateStart, dateFormat);
     $(form).find("input[name='startDate']").data('daterangepicker').endDate = moment(course.dateStart, dateFormat);
     $(form).find("input[name='startDate']").val(course.dateStart);
     $(form).find('.endDateResult').text(course.dateEnd);

     for(let i = 0; i< schedules.length; i++)
     {
        $(form).find("input[name='schedule']")[schedules[i]].checked = true;
     }

     }

}

//		--------------- VALIDATE ------------------
    function isValidateForm(formId)
    {
        var form = document.forms[formId];

        var inputName = form["name"];
        var inputFee = form["fee"];
        let inputTotalLesson = form["totalLesson"];
        let inputSelect = form["inputSelect"];
        let dateRange = form["dateRange"];



       	if (!inputName.checkValidity()) {
            alert("Vui lòng điền tên khóa học");
            return false;
        }
        if (!inputFee.checkValidity()) {
            alert("Học Phí > 0!");
            return false;
            }
        if (!inputTotalLesson.checkValidity()) {
            alert("Số buổi học > 0!");
            return false;
            }

           if(inputSelect.value == 1)
           {
                if(dateRange.value == ""){
                alert("Vui lòng điền ngày bắt đầu và ngày kết thúc!");
                return false;
                }
           }else  if(inputSelect.value == 2)
            {
                let form = $(document.getElementById(formId));
                if(getEndDate(form) === null)
                {
                alert("Vui lòng điền đầy đủ tổng số buổi học, lịch học và ngày bắt đầu! ");
                return false;
                }
            }

        return true;
    }

$(document).ready(function() {
var token = $("meta[name='_csrf']").attr("content");
	/*    DATE     */
	 //change totalLesstion
    $('input[name="totalLesson"]').blur(function()
        {
            updateEndDate($(this).closest("form"));
        }
    );
    //change schedule
    $('input[name="schedule"]').change(
        function()
        {
            updateEndDate($(this).closest("form"));
        }
    );

    $('.startDateSelect').on('apply.daterangepicker', function (ev, picker) {
        isClick = 0;
        $(this).val(picker.startDate.format('DD/MM/YYYY'));
        updateEndDate($(this).closest("form"));
    
    });

    $('.startDateSelect').on('input', function()
    {
        console.log('change');
    });
    
    /*   CRUD   */
    //----------------DELETE---------------------
	$("#del_btn").click(function() {
		console.log("delete");
		let id = $("#delid").val();
		console.log("delete" + id);
		delStudent(id);
	});
	
	//-----------------INSERT-----------------------
	$('#btn-modal-insert').click(
		    function () {
		    //-------------------- VALID FORM -----------------------
            if(isValidateForm('course-create-form') == false) return;
            let insertModal  = $('#modal-insert');
		    let studentId = $('#studentId').val();
		        let form = $('#course-create-form');
		        let name = form.find('input[name="name"]').val();
		        let classCourse = form.find('input[name="classCourse"]').val();
		        let fee = form.find('input[name="fee"]').val();
		        let totalLesson = form.find('input[name="totalLesson"]').val();
		        let note = form.find('input[name="note"]').val();
		        console.log("NOTE"+ note);
		        let startDate;
		        let endDate;
		        let schedules;
		        let dateRegis = form.find('input[name="dateRegis"]').val();
		        let isHaveSchedule = false;
		        
		        let valueSelect = $("#calDateSelect").val();

		        let courseData = {
		            studentId: studentId,
		            name: name,
		            classCourse: classCourse,
		            fee:fee,
		            totalLesson:totalLesson,
		            dateRegis: dateRegis,
                    note: note
		        }
		        if (valueSelect == 1) {
		        	courseData.schedules = null;
		        	courseData.dateEnd = insertModal.find('.inputRangeDate').data('daterangepicker').endDate.format('DD/MM/YYYY');
		        	courseData.dateStart = insertModal.find('.inputRangeDate').data('daterangepicker').startDate.format('DD/MM/YYYY');

		        } else if (valueSelect == 2) {
		        	courseData.schedules = getSchedules('course-create-form');
		        	courseData.dateStart = form.find('.startDateSelect').val();
                    courseData.dateEnd = getEndDate(form).format('DD/MM/YYYY');
		        }


		        $.ajax({
                		type : 'PUT',
                		headers: {
                        									"X-CSRF-TOKEN": token
                        								},
                		url : "/course-api/insert",
                		data: JSON.stringify(courseData),
                		contentType : 'application/json',
                        dataType: 'json',
                		success : function(result, status) {
                			/* alert("modify success!"); */
                			insertRowCourse(result);
                			$('#modal-insert').modal('hide');

                			//reset form
                			$("#course-create-form").trigger('reset');
                			$(form).find('.endDateResult').text("Ngày kết thúc");
                		},
                		error : function(e) {
                			alert("Modify not success!");
                			console.log(e);
                		}
                	});
		    }
		);


		$('#modal-edit').on('show.bs.modal', function(event) {
            modalLoading($('#modal-edit'), false);

            let editbutton = $(event.relatedTarget);
            let courseId = editbutton.data('course-id');

            console.log("get data");

            $.ajax({
                type : 'GET',
                headers: {
                									"X-CSRF-TOKEN": token
                								},
                url : "/course-api/get/"+courseId,
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
               //-------------------- VALID FORM -----------------------
                          if(isValidateForm('course-edit-form') == false) return;

                           let editModal  = $('#modal-edit');

               		        let studentId = $('#studentId').val();
               		        let form = $('#course-edit-form');
               		         let id = form.find('input[name="id"]').val();
               		        let name = form.find('input[name="name"]').val();
               		        let classCourse = form.find('input[name="classCourse"]').val();
               		        let fee = form.find('input[name="fee"]').val();
               		        let totalLesson = form.find('input[name="totalLesson"]').val();
               		        let note = form.find('input[name="note"]').val();
               		        console.log("NOTE"+ note);
               		        let startDate;
               		        let endDate;
               		        let schedules;
               		         let dateRegis = form.find('input[name="dateRegis"]').val();
               		        let isHaveSchedule = false;

               		        let valueSelect = $("#calDateSelect_edit").val();

               		        let courseData = {
               		        id:id,
               		            studentId: studentId,
               		            name: name,
               		            classCourse: classCourse,
               		            fee:fee,
               		            totalLesson:totalLesson,
               		            dateRegis: dateRegis,
                                   note: note
               		        }
               		        if (valueSelect == 1) {
               		        	courseData.schedules = null;
               		        	courseData.dateEnd = form.find('.inputRangeDate').data('daterangepicker').endDate.format('DD/MM/YYYY');
               		        	courseData.dateStart = form.find('.inputRangeDate').data('daterangepicker').startDate.format('DD/MM/YYYY');

               		        } else if (valueSelect == 2) {
               		        	courseData.schedules = getSchedules('course-edit-form');
               		        	courseData.dateStart = form.find('.startDateSelect').val();
                                courseData.dateEnd = getEndDate(form).format('DD/MM/YYYY');
               		        }


               		        $.ajax({
               		        headers: {
                            									"X-CSRF-TOKEN": token
                            								},
                               		type : 'POST',
                               		url : "/course-api/update",
                               		data: JSON.stringify(courseData),
                               		contentType : 'application/json',
                                       dataType: 'json',
                               		success : function(result, status) {
                               			/* alert("modify success!"); */
                               			editRowCourse(courseData);
                               			$('#modal-edit').modal('hide');

                               		},
                               		error : function(e) {
                               			alert("Modify not success!");
                               			console.log(e);
                               		}
                               	});
                    });
});