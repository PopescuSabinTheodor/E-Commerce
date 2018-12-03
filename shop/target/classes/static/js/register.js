$(document).ready(
	//Entry function
		function() {
			//Counts how many times the email field loses focus
			var focusCount = 0;
			$( "#email" ).blur(function() {
				focusCount++;
				//The whole form gets focused once and loses the focus so we need to validate the email field
				//when it loses the second focus on
				if(validateEmail($('#email').val())&& focusCount > 1) {
					//Get request checking if the email address is already in use
					$.ajax({
							type : "GET",
							dataType : "json",
							url : "/users/" + encodeURIComponent($('#email').val()),
							error : function() {
							},
							success : function(data){
								var emailNotValid = data.emailExists;
								$("#email-exists").hide();
								if (emailNotValid) {
									$("#email-exists").text("This email address is already in use").show();
								}
								else {
									$("email-exists").hide();	
								}
							}
					});
			}
			else if(focusCount > 1) {
				$("#error-email").text(
							"Please enter a valid email address.")
							.show();
			}
			});

			
			$('.registerbtn').click(submitForm);

			//Animation effect
			$('.form-group input').on(
					'focus blur',
					function(e) {
						$(this).parents('.form-group').toggleClass('active',
								(e.type === 'focus' || this.value.length > 0));
					}).trigger('blur');
		
			$(".registration-successful").hide();
			//Regex for validating the email format
			function validateEmail(email) {
				var re = /\S+@\S+\.\S+/;
				return re.test(email);
			}

		
			//Builds a User JSON
			function getUserInfo() {
				return {
					"firstName" : $('#first-name').val(),
					"lastName" : $('#last-name').val(),
					"emailAddress" : $('#email').val(),
					"password" : $('#pwd1').val(),
					"passwordVerify" : $('#pwd2').val()
				};
			}
			
			//Validates fields
			function validateInput(userInfo) {
				if (userInfo.firstName.length < 5) {
					$("#error-first-name").text(
							"First name should be at least 5 characters long.")
							.show();
					return false;
				} else if (userInfo.lastName.length < 5) {
					$(".error").hide();
					$("#error-last-name").text(
							"Last name should be at least 5 characters long.")
							.show();
					return false;
				} else if (!validateEmail(userInfo.emailAddress)) {
					$(".error").hide();
					$("#error-email").text(
							"Please enter a valid email address.")
							.show();
					return false;
				} else if (userInfo.password.length < 8) {
					$(".error").hide();
					$("#error-password").text(
							"The password must be at least 8 characters long")
							.show();
					return false;
				} else if (userInfo.password != userInfo.passwordVerify) {
					$(".error").hide();
					$("#error-passwordVerify").text(
							"The passwords must match")
							.show();
					return false;
				} else {
					$(".error").hide();
					return true;
				}
			}
			
			//POST request that submits the form
			function submitForm(data) {
				var userInfo = getUserInfo();
				if(validateInput(userInfo)) {
					$.ajax({
						type : "POST",
						url : "/signup",
						contentType : "application/json",
						data : JSON.stringify(getUserInfo()),
						error : function(data) {
						},
						success : function(data) {
							console.log("log....... ", data);
							$(".form-group").hide();
							$(".head").html('Welcome');
							$(".registerBtn").hide();
							$(".containerSignin").hide();
							$(".registration-successful").show();
							$("#registration-email").text("A verification email has been sent to " + data + ".").show();
						}

					});
				}

			}
		});