/**
 * jeDate 演示
 */


/*  jeDate("#beginDateId",{ format: "YYYY-MM-DD hh:mm:ss" });
  
  jeDate("#endDateId",{ format: "YYYY-MM-DD hh:mm:ss" });*/
  
  var start = {
			format : "YYYY-MM-DD",
			minDate: '2017-01-01',
			maxDate: '2099-06-16', //最大日期
			choosefun : function(elem, datas) {
				end.minDate = datas; // 开始日选好后，重置结束日的最小日期
			}
		};

		var end = {
			format : "YYYY-MM-DD",
			minDate: '2017-01-01',
			maxDate: '2099-06-16', //最大日期
			choosefun : function(elem, datas) {
				start.maxDate = datas; // 将结束日的初始值设定为开始日的最大日期
			}
		};

		jeDate("#beginDateId",start);
		jeDate("#endDateId",end);



