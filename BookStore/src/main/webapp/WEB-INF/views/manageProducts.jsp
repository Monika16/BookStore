<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
	<div class="row">
		<c:if test="${not empty message }">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="product" 
							action="${contextRoot}/manage/products" method="POST"
							enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4" for="name"> Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder ="Product Name" class="form-control"/>
								<sf:errors path="name" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="author">Author: </label>
							<div class="col-md-8">
								<sf:input type="text" path="author" id="author" placeholder ="Author Name" class="form-control"/>
								<sf:errors path="author" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4" placeholder ="Description" class="form-control"></sf:textarea>
								<sf:errors path="description" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="price">Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="price" placeholder ="Unit Price" class="form-control"/>
								<sf:errors path="unitPrice" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity: </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder ="Quantity" class="form-control"/>
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Image: </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control"/>
								<sf:errors path="file" cssClass="help-block" element="em"></sf:errors>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Category: </label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId" path="categoryId"
									items= "${categories}"
									itemLabel="name"
									itemValue="id"
								/>
								<c:if test="${product.id == 0}">
									<div class="text-right">
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
									</div>
								</c:if>
							</div>
						</div>
						
						<div class="form-group">
							
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value ="Submit" class="btn btn-primary"/>
							
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="active"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<h3>Available Products:</h3>
			<hr/>
			
		</div>
		<div class="col-xs-12">
			<div class="container-fluid">
				<div class="table-responsive">
					<table id="adminProductsTable" class="table table-striped table-borderd">
						<thead>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Author</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Author</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th>Active</th>
								<th>Edit</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	
	</div>

	<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">Add New Category</h4>
				</div>
				<div class="modal-body">
					<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category" 
									method="POST" class="form-horizontal">
						<div class="form-group">
							<label for="cat_name" class="control-label col-md-4">Category Name:</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="cat_name" class="form-control"></sf:input>
							</div>
						</div>
						<div class="form-group">
							<label for="cat_description" class="control-label col-md-4">Category Desc:</label>
							<div class="col-md-8">
								<sf:textarea rows="5" path="description" id="cat_description" class="form-control"></sf:textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>
					</sf:form>
			   </div>
			</div>
		</div>
	</div>
</div>