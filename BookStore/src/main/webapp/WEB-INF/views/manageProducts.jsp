<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>
				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="product">
						<div class="form-group">
							<label class="control-label col-md-4" for="name"> Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name" placeholder ="Product Name" class="form-control"/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="author">Author: </label>
							<div class="col-md-8">
								<sf:input type="text" path="author" id="author" placeholder ="Author Name" class="form-control"/>
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" placeholder ="Description" class="form-control"></sf:textarea>
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="price">Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="price" placeholder ="Unit Price" class="form-control"/>
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity: </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" rows="4" placeholder ="Quantity" class="form-control"/>
								
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
</div>