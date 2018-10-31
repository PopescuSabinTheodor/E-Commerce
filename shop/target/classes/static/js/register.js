$(document).ready(
		function() {
			$( "#pwd1" ).focus(function() {
				console.log("focus");
				$.ajax({
						type : "GET",
						dataType : "json",
						url : "/users/" + encodeURIComponent(userInfo.emailAddress),
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
			});
				
			$('.registerbtn').click(submitForm);
			$('.form-group input').on(
					'focus blur',
					function(e) {
						$(this).parents('.form-group').toggleClass('active',
								(e.type === 'focus' || this.value.length > 0));
					}).trigger('blur');
		
			$(".registration-successful").hide();
			function validateEmail(email) {
				var re = /\S+@\S+\.\S+/;
				return re.test(email);
			}

			function getUserInfo() {
				return {
					"firstName" : $('#first-name').val(),
					"lastName" : $('#last-name').val(),
					"emailAddress" : $('#email').val(),
					"password" : $('#pwd1').val(),
					"passwordVerify" : $('#pwd2').val()
				};
			}
			var userInfo = getUserInfo();
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