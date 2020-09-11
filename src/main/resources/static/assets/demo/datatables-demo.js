// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTableStudent').DataTable( {
	  dom: '<"d-md-flex justify-content-between"fl<B>>rt<"d-md-flex justify-content-between"ip>',
      lengthChange: true,
      "lengthMenu": [[15, 25, 50, -1], [15, 25, 50, 'Show all']],
      "columnDefs": [ {
          "searchable": false,
          "orderable": false,
       
      }],
      buttons: [ {
          extend: 'copy',
          text: 'Sao Chép',
          className: 'btn-sm btn-secondary mx-auto',
          exportOptions: {
              columns: [0,1,2,3,4,5,6]
          }
      }, {
          extend: 'excel',
          text: 'Xuất Excel',
          className: 'btn-sm btn-success mx-auto',
          exportOptions: {
              columns: [0,1,2,3,4,5,6]
          }
      }],
	language: {
          search: '',
          searchPlaceholder: "Tìm kiếm: tên, sđt,...",
          "zeroRecords": "Không tìm thấy kết quả nào",
          "info": "Hiển thị _START_-_END_ trong tổng số _TOTAL_ mục",
          "infoEmpty": "Không tìm thấy mục nào",
          "infoFiltered": " (lọc từ _MAX_ mục)",
          "lengthMenu": "Hiển thị _MENU_ mục/trang",
          "processing": "Đang xử lý...",
          paginate: {
              first: 'Đầu',
              previous: 'Trước',
              next: 'Sau',
              last: 'Cuối'
          },
      }
  });
  
  $('#dataTableCourse').DataTable( {
	  dom: '<"d-md-flex justify-content-between"fl<B>>rt<"d-md-flex justify-content-between"ip>',
      lengthChange: true,
      "lengthMenu": [[15, 25, 50, -1], [15, 25, 50, 'Show all']],
      "columnDefs": [ {
          "searchable": false,
          "orderable": false,
       
      }],
      buttons: [ {
          extend: 'copy',
          text: 'Sao Chép',
          className: 'btn-sm btn-secondary mx-auto',
          exportOptions: {
              columns: [0,1,2,3,4,5,6]
          }
      }, {
          extend: 'excel',
          text: 'Xuất Excel',
          className: 'btn-sm btn-success mx-auto',
          exportOptions: {
              columns: [0,1,2,3,4,5,6]
          }
      }],
	language: {
          search: '',
          searchPlaceholder: "Tìm kiếm: tên, sđt,...",
          "zeroRecords": "Không tìm thấy kết quả nào",
          "info": "Hiển thị _START_-_END_ trong tổng số _TOTAL_ mục",
          "infoEmpty": "Không tìm thấy mục nào",
          "infoFiltered": " (lọc từ _MAX_ mục)",
          "lengthMenu": "Hiển thị _MENU_ mục/trang",
          "processing": "Đang xử lý...",
          paginate: {
              first: 'Đầu',
              previous: 'Trước',
              next: 'Sau',
              last: 'Cuối'
          },
      }
  });
  

});
