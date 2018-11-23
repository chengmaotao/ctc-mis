$(function () {
	$.ajax({  
        type: "post",
        url: baseURL + 'ctc/datacount/getCtcDataCount?time=' + new Date().getTime(),
        async: false,
        success: function(msg){
        	if(msg.code == '0'){
        		console.log(msg.msg);
        		var res = msg.ctcDataCount;
        		$("#customerNum").text(res.customerCount);
        		$("#walletAddressNum").text(res.walletAddressCount);
        		$("#transactionAmountNum").text(res.transactionAmountCount);
        		$("#transactionNum").text(res.transactionCount);
        		$("#transactionAddressNum").text(res.transactionAddressCount);
        	}else{
        		alert(msg.msg);
        	}
        },
        error:function(msg){
        	alert("未知错误：请联系管理员");
        }
	});
});
