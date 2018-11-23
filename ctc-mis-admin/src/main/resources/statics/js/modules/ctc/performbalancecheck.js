$(function() {

	// 查看执行的状态
	$.ajax({
		type : "POST", //提交方式 
		url : baseURL + 'ctc/performbalancecheck/getRerformStatus',
		data : {

		}, //数据，这里使用的是Json格式进行传输 
		async : false,
		success : function(result) { //返回数据根据结果进行相应的处理 
			if (result == "0") {
				$("#performId").text("校验中……");
				$("#queryId").attr("disabled", true);
				$("#performId").attr("disabled", true);
				$("#resertId").attr("disabled", true);
				
			} else {
				$("#performId").text("校验");
				$("#queryId").attr("disabled", false);
				$("#performId").attr("disabled", false);
				$("#resertId").attr("disabled", false);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("未知错误：请联系管理员");
		}
	});


	// 开始执行
	$("#performId").click(function(){  
		
		if($(this).attr("disabled")){
			return;
		}
		
		$.ajax({
			type : "POST", //提交方式 
			url : baseURL + 'ctc/performbalancecheck/checkBalance',
			data : {

			}, //数据，这里使用的是Json格式进行传输 
			success : function(result) { //返回数据根据结果进行相应的处理 
				if (result == "0") {
					$("#performId").text("校验中……");
					$("#queryId").attr("disabled", true);
					$("#performId").attr("disabled", true);
					$("#resertId").attr("disabled", true);
				} else {
					alert("执行失败：请联系管理员");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("未知错误：请联系管理员");
			}
		});
	});

	var tindex = 0;
	// 查询
	$("#queryId").click(function(){  
		
			if($(this).attr("disabled")){
				return;
			}
		
			$.ajax({
				type : "POST", //提交方式 
				url : baseURL + 'ctc/performbalancecheck/queryAddressErrorBalance',
				data : {
					address:$("#addressId").val()
				}, //数据，这里使用的是Json格式进行传输 
				success : function(result) { //返回数据根据结果进行相应的处理 
					
					$("#addressErrorBalanceId").empty();
					if(result == null || result.length == 0){
						alert("缓存余额和链上余额都是一致的");
					}else{
						var tres = "";
						
						$.each(result, function(index,item){ 
							tres = tres + "<tr style='height: 40px'><td style='width: 400px'>"+item.address+"</td><td style='width: 300px'>"+item.rpcBalance+"</td><td style='width: 300px'>"+item.redisBalance+"</td></tr>";
					    }); 
			
						
						$("#addressErrorBalanceId").append(tres);
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("未知错误：请联系管理员");
				}
			});
			
			
			

	});
	
	

	// 重置
	$("#resertId").click(function(){  
		
		if($(this).attr("disabled")){
			return;
		}
		
		$.ajax({
			type : "POST", //提交方式 
			url : baseURL + 'ctc/performbalancecheck/resertAddressErrorBalance',
			data : {
				address:$("#addressId").val()
			}, //数据，这里使用的是Json格式进行传输 
			success : function(result) { //返回数据根据结果进行相应的处理 
				if(result == "0"){
					alert("重置成功");
				}else{
					alert("重置失败：请联系管理员");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("未知错误：请联系管理员");
			}
		});
	});


});