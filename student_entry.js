$(document).ready(function(){
    // newStudent
    $('button#register').click(function(e){  
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "http://localhost:7777/student",
            data: JSON.stringify({name: $('input#name').val(),
                                age: $('input#age').val(),
                                mobileNo: $('input#mobileNo').val(),
                                address: $('input#address').val() 
                                }),
            headers:{
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                    },
                    'dataType': 'json', 

            success: function() {
                $('form#registerform').append('<div class="alert alert-success"><strong>[SUCCESS]</strong> Student is REGISTERED</div>');                
            },
            error: function() {
                $('form#registerform').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Student registration FAILED!</div>');               
            }
        });   
    });
  
    //findAll
    $("button#viewall").click(function(){
        $('#stdlist').empty()
        $.ajax({
            type: "GET",
            url: "http://localhost:7777/student",
            success: function(studentArray) {
                var trHTML = '<tr><th>' + 'ID' + 
                            '</th><th>' + 'Name' +
                            '</th><th>' + 'Age' +
                            '</th><th>' + 'Mobile' +
                            '</th><th>' + 'Address' + 
                            '</th><th>' + 'Action' + '</th></tr>';
                $.each(studentArray, function(index, student) {
                    trHTML += '<tr><td>'   
                                + student.id + '</td><td>' 
                                + student.name + '</td><td>' 
                                + student.age + '</td><td>'
                                + student.mobileNo + '</td><td>'
                                + student.address + '</td><td>'
                                + '<button id="edit" type="button" class="btn-edit"  data-id="' + student.id + '" data-bs-toggle="modal" data-bs-target="#infoModal">Edit</button>' + '</td><td>'
                                + '<button id="delete" type="button" class="btn-del" data-id="' + student.id + '"><i class="fa fa-trash"></i></button>' + '</td></tr>';                         
                }); 
                $('#stdlist').empty().append(trHTML);

            },
            error: function() {
                $('#stdlist').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Student list LOADING FAILED!</div>');
            }
        });
    });

    //findById
    $("button#viewbyid").click(function(){
        $('#stdlist').empty()
        $.ajax({
            type: "GET",
            url: "http://localhost:7777/student/" + $('#findById').val(),
            success: function(student) {
                var trHTML = '<tr><th>' + 'ID' + 
                            '</th><th>' + 'Name' +
                            '</th><th>' + 'Age' +
                            '</th><th>' + 'Mobile' +
                            '</th><th>' + 'Address' + 
                            '</th><th>' + 'Action' + '</th></tr>';
                $(student).each(function(index, id) {
                    trHTML += '<tr><td>'   
                                + student.id + '</td><td>' 
                                + student.name + '</td><td>' 
                                + student.age + '</td><td>'
                                + student.mobileNo + '</td><td>'
                                + student.address + '</td><td>'
                                + '<button id="edit" type="button" class="btn-edit"  data-id="' + student.id + '" data-bs-toggle="modal" data-bs-target="#infoModal">Edit</button>' + '</td><td>'
                                + '<button id="delete" type="button" class="btn-del" data-id="' + student.id + '"><i class="fa fa-trash"></i></button>' + '</td></tr>';                         
                });
                $('#stdlist').empty().append(trHTML);
            },
            error: function() {
                $('#stdlist').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Student ID=' + $('#findById').val() + ' NOT FOUND!</div>');
            }
        })
    });

    //findByName
    $("button#viewbyname").click(function(){
        $('#stdlist').empty()
        $.ajax({
            type: "GET",
            url: "http://localhost:7777/student/name/" + $('#findByName').val(),
            success: function(studentArray) {
                var trHTML = '<tr><th>' + 'ID' + 
                            '</th><th>' + 'Name' +
                            '</th><th>' + 'Age' +
                            '</th><th>' + 'Mobile' +
                            '</th><th>' + 'Address' + 
                            '</th><th>' + 'Action' + '</th></tr>';
                $.each(studentArray, function(index, student) {
                    trHTML += '<tr><td>'   
                                + student.id + '</td><td>' 
                                + student.name + '</td><td>' 
                                + student.age + '</td><td>'
                                + student.mobileNo + '</td><td>'
                                + student.address + '</td><td>'
                                + '<button id="edit" type="button" class="btn-edit"  data-id="' + student.id + '" data-bs-toggle="modal" data-bs-target="#infoModal">Edit</button>' + '</td><td>'
                                + '<button id="delete" type="button" class="btn-del" data-id="' + student.id + '"><i class="fa fa-trash"></i></button>' + '</td></tr>';                         
                });
                if(studentArray == 0) {
                    $('#stdlist').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Student name=' + $('#findByName').val() + ' NOT FOUND!</div>');
                } else {
                    $('#stdlist').empty().append(trHTML);
                } 
                                   
            },
            error: function() {
                $('#stdlist').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Student name=' + $('#findByName').val() + ' NOT FOUND!</div>');
            }
        });
    });

    // editStudent
    // $('#infoModal').modal().on('shown.bs.modal', function(e) {
    //     var profileId= $(e.relatedTarget).attr('data-id');
    //     $(this).find('.update').text(profileId);
    // });    
    $(document).on('click', 'button#edit', function() {
        var profileId = $(this).attr('data-id');
        var profileName = $(this).closest('tr')[0].cells[1].textContent;
        var profileAge = $(this).closest('tr')[0].cells[2].textContent;
        var profileMobile = $(this).closest('tr')[0].cells[3].textContent;
        var profileAddr = $(this).closest('tr')[0].cells[4].textContent;
        var studentId = 'Student ID: ' + profileId;
        var row = $(this).parent().parent();
        $("input#uname").val(profileName);
        $("input#uage").val(profileAge);
        $("input#umobileNo").val(profileMobile);
        $("input#uaddress").val(profileAddr);
        $('div#studentId').empty().append(studentId);
        $(document).on('click', 'button#update', function() {
            var Id = profileId;
            row.remove();
            $.ajax({
                type: "PUT",
                url: "http://localhost:7777/student/" + Id,
                data: JSON.stringify({id: Id,
                                    name: $('input#uname').val(),
                                    age: $('input#uage').val(),
                                    mobileNo: $('input#umobileNo').val(),
                                    address: $('input#uaddress').val() 
                                    }),                              
                headers:{
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                        },
                success: function() {

                    var trHTML ='<tr><th>' + 'ID' + 
                                '</th><th>' + 'Name' +
                                '</th><th>' + 'Age' +
                                '</th><th>' + 'Mobile' +
                                '</th><th>' + 'Address' + 
                                '</th><th>' + 'Action' + 
                                '</th></tr>'+'<tr><td>'+ profileId + '</td><td>' 
                                + $('input#uname').val() + '</td><td>' 
                                + $('input#uage').val() + '</td><td>'
                                + $('input#umobileNo').val() + '</td><td>'
                                + $('input#uaddress').val() + '</td><td>'
                                + '<button id="edit" type="button" class="btn-edit"  data-id="' + student.id + '" data-bs-toggle="modal" data-bs-target="#infoModal">Edit</button>' + '</td><td>'
                                + '<button id="delete" type="button" class="btn-del" data-id="' + student.id + '"><i class="fa fa-trash"></i></button>' + '</td></tr>';                         

                    $('#stdlist').empty().append(trHTML);
                    $('#stdlist').append('<div class="alert alert-success"><strong>[SUCCESS]</strong> Student profile ID:' + profileId + ' is UPDATED!</div>');
                },

                error: function() {
                    $('#stdlist').empty().append().append(row);
                    // $('#stdlist').append(row);
                    $('#stdlist').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Failed to update student profile ID:' + profileId + '!</div>');
                    
                }
            }); 
        });        
    });

    // deleteStudent
    $(document).on('click', '.btn-del', function() {
        var Id = $(this).attr('data-id');
        var row = $(this).parent().parent();
        $.ajax({
            type: "DELETE",
            url: "http://localhost:7777/student/" + Id,
            data: Id,
            success: function() {
                $('#stdlist').append('<div class="alert alert-success"><strong>[SUCCESS]</strong> Student profile ID:' + Id + ' is DELETED!</div>');
                row.remove();
            },
            error: function() {
                $('#stdlist').append('<div class="alert alert-danger"><strong>[!ERROR!]</strong> Failed to delete student profile ID:' + Id + '!</div>');
              
            },            
        });
    });
})