/**
 * 
 */

$(function(){
	
	//登录
	$(".loginbtn").click(function(){
		$.post({
			url:'/login/login',
			dataType:'json',
			data:{
				username:$(".login_username").val(),
				password:$(".login_password").val()
			},
			success:function(data){
				//登录成功
				if(data.msg == 'success'){
					location.href="/hello";
				}
				if(data.msg == 'authenticated'){
					alert("您已登录，请注销后再登录");
				}
			}
		})
	});
	
	//注册
	$(".registerbtn").click(function(){
		$.post({
			url:'/register/regist',
			dataType:'json',
			data:{
				username:$(".register_username").val(),
				password:$(".register_password").val()
			},
			success:function(data){
				if(data.flag == 'success'){
					alert("success");
				}
				console.log(data);
			}
		})
	});
})