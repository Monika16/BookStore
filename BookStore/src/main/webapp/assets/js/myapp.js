$(function(){
	switch(menu) {
		
	   case 'About Us':
		   $('#about').addClass('active');
		   break;
	   case 'Contact Us':
		   $('#contact').addClass('active');
		   break;
	   case 'All Products':
		   $('#listProducts').addClass('active'); 
		   break;
	   default :
		   if(menu == "Home") break;
		   $('#listProducts').addClass('active');
	   	   $('#a_'+menu).addClass('active');
	       break;
	}	
})

var $table = $('#productListTable');
if($table.length){
	
	var jsonUrl ='';
	if(window.categoryID == ''){
		jsonUrl = window.contextRoot + '/json/data/all/products';
	}else{
		jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryID + '/products';
	}
	
	$table.DataTable ({
		lengthMenu: [[3,5,10,-1],['3 Rec','5 Rec','10 Rec','All Rec']],
		pageLength: 3,
		ajax: {
			url: jsonUrl,
			dataSrc: ''
		},
		columns: [
			{
				data: 'code',
				mRender: function(data,type,row){
					return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
				}
			},
			{
				data:'name'
			},
			{
				data:'author'
			},
			{
				data:'unitPrice',
				mRender: function(data,type,row){
					return '&#36; '+ data
				}
			},
			{
				data:'quantity'
			},
			{
				data: 'id',
				bSortable: false,
				mRender: function(data,type,row){
					var str ='';
					str += '<a href ="'+window.contextRoot +'/show/' +data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>'+'&#160;';
					str += '<a href ="'+window.contextRoot +'/cart/add/' +data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
					return str;
				}
			}
		]
	});
}