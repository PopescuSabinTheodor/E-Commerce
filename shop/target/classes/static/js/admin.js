$(document).ready(
		function(){
			$('.addbtn').click(submitForm);
			function submitForm() {
				var product = { "name" : $("#name").val(),
								"description" : $("#description").val(),
								"specifications" : $("#specifications").val(),
								"stock" : $("#stock").val(),
								"price" : $("#price").val(),
								"category" : $("#category").val()
							  };
					$.ajax({
						type : "POST",
						url : "/admin/add",
						contentType : "application/json",
						data: JSON.stringify(product),
						error : function(data) {
							console.log("nup");
						},
						success: function(data) {
							console.log("mearsa");
							
						}
					});
			}
	});