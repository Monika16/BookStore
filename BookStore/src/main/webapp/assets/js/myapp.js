$(function() {
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	
	//to tackle csrf token
	
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		//set token for ajax request
		$(document).ajaxSend(function(e,xhr,options){
			xhr.setRequestHeader(header,token);
		});
		
	}
	
	var $table = $('#productListTable');
	if ($table.length) {
	
		var jsonUrl = '';
		if (window.categoryID == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryID + '/products';
		}
	
		$table
				.DataTable({
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Rec', '5 Rec', '10 Rec', 'All Rec' ] ],
					pageLength : 3,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'author'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#36; ' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									} else {
										return data;
									}
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href ="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>'
											+ '&#160;';
									
									if(userRole == 'ADMIN'){
										str += '<a href ="'+ window.contextRoot +'/manage/'+ data +'/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
									}else {
										if (row.quantity < 1) {
											str += '<a href ="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										} else {	
											str += '<a href ="'+ window.contextRoot +'/cart/add/'+ data +'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
									}
	
									return str;
								}
							} ]
				});
	}
	
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}
	
	// data table for admin
	var $adminTable = $('#adminProductsTable');
	if ($adminTable.length) {
	
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
	
		$adminTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Rec', '30 Rec', '50 Rec', 'All Rec' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="adminDataTableImage"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'author'
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									} else {
										return data;
									}
								}
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#36; ' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '"/>';
									} else {
										str += '<input type="checkbox" value="'
												+ row.id + '"/>';
									}
									str += '<div class="slider"></div></label>';
									return str;
								}
	
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'+window.contextRoot+'/manage/' + data
											+ '/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';
	
									return str;
	
								}
							} ],
					initComplete : function() {
						var api = this.api();
						api.$('.switch input[type="checkbox"]')
								.on('change',function() {
	
											var checkbox = $(this);
											var checked = checkbox.prop('checked');
											var dMsg = (checked) ? 'You want to activate the product?'
													: 'You want to deactivate the product?';
											var value = checkbox.prop('value');
	
											bootbox.confirm({
												size : 'medium',
												title : 'Product activation and deactivation',
												message : dMsg,
												callback : function(confirmed) {
													if (confirmed) {
														console.log(value);
														var activationUrl = window.contextRoot
																			+ '/manage/product/'
																			+ value
																			+ '/activation';
													    $.post(activationUrl,function(data){
														       bootbox.alert({
																		size : 'medium',
																		title : 'Information',
																		message : data
																	  });
													    });
													} 
													else {
																checkbox.prop('checked',!checked);
													}
												}
										})
						});
					}
				});
	}
	
	
	var $categoryForm = $('#categoryForm');
	if($categoryForm.length){
		$categoryForm.validate({
				rules: {
					name : {
						   		required: true,
						   		minlength: 2
					       },
					description : {
						         required: true
							}
				},
				messages : {
					name:{
							required: 'Please add the Category Name!',
							minlength: 'Category name cannot be less than 2 characters'
					      },
					description:{
						          required: 'Please add description!'
					            }
				},
				errorElement: 'em',
				errorPlacement: function(error,element){
					
						error.addClass('help-block');
						error.insertAfter(element);
				}
		});
	}
	
	var $loginForm = $('#loginForm');
	if($loginForm.length){
		$loginForm.validate({
				rules: {
					username : {
						   		required: true,
						   		email: true
					       },
					password : {
						         required: true
							}
				},
				messages : {
					username:{
							required: 'Please enter the Username!',
							email: 'Please enter valid email address'
					      },
					password:{
						          required: 'Please enter the Password!'
					            }
				},
				errorElement: 'em',
				errorPlacement: function(error,element){
					
						error.addClass('help-block');
						error.insertAfter(element);
				}
		});
	}
	
	//handling click event for refresh cart event
	
	$('button[name="refreshCart"]').click(function(){
		
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);
		
		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();
		
		//refresh works only if current count has changed from the original count
		if(currentCount !== originalCount){
			
			if(currentCount < 1 || currentCount > 3){
				//reverting to your original count
				countElement.val(originalCount);
				bootbox.alert({
					size:'medium',
					title:'Error',
					message:'Product Count should be greater than 1 and less than 3'
				});
			}
			else{
				var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;
				window.location.href= updateUrl;
			}
		}
	});
	

});
