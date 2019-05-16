var NS = NS || {};
NS.Common = NS.Common || {};
NS.Common.ExportUtil = (function($) {
	function JSONToExcelConvertor(gridElementId, fileName, needPrintHiddenFildArr) {
        var footerRow = $("#" + gridElementId).datagrid('getFooterRows');
        var gridData = $("#"+gridElementId).datagrid('getData').rows;
        if (footerRow !=null) {
            for (var i in footerRow) {
                gridData.push(footerRow[i]);
            }
		}
        
		var jsonData = JSON.stringify(gridData);
        var arrData = typeof jsonData != 'object' ? JSON.parse(jsonData) : jsonData;
        var excel = '<table border="1">';      
        var row = "<tr >";  
        var titleData = JSON.parse(getGridTitle(gridElementId, setNeedPrintHiddenFilds(needPrintHiddenFildArr)));
        for (var key in titleData) {
        	 row += "<th style='font-size:15px;'>" + titleData[key] + '</th>';  
    	}
        excel += row + "</tr>";  
        for (var i = 0; i < arrData.length; i++) {  
             var row = "<tr>";  
             for (var key in titleData) {
        		 var value = arrData[i][key]; 
        		 var formatter = getGridFormatterByField(key, gridElementId);
        		 if (formatter != null) {
	        			 var  html = formatter(value, arrData[i], i);  
	        			 row += '<td algin="center">' + (html == null? "" : html) + '</td>';
        		 } else {
        			 row += '<td>' + (value == null ? "" : value) + '</td>';
        		 }
        	 }
             excel += row + "</tr>";  
         } 
         excel += "</table>";  
         var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";  
         excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';  
         excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';  
         excelFile += '; charset=UTF-8">';  
         excelFile += "<head>";  
         excelFile += "<!--[if gte mso 9]>";  
         excelFile += "<xml>";  
         excelFile += "<x:ExcelWorkbook>";  
         excelFile += "<x:ExcelWorksheets>";  
         excelFile += "<x:ExcelWorksheet>";  
         excelFile += "<x:Name>";  
         excelFile += "{worksheet}";  
         excelFile += "</x:Name>";  
         excelFile += "<x:WorksheetOptions>";  
         excelFile += "<x:DisplayGridlines/>"; 
         excelFile += "</x:WorksheetOptions>";  
         excelFile += "</x:ExcelWorksheet>";  
         excelFile += "</x:ExcelWorksheets>";  
         excelFile += "</x:ExcelWorkbook>";  
         excelFile += "</xml>";  
         excelFile += "<![endif]-->";  
         excelFile += "</head>";  
         excelFile += "<body>";  
         excelFile += excel;  
         excelFile += "</body>";  
         excelFile += "</html>";  
           
         if (navigator.msSaveBlob) {
        	 navigator.msSaveBlob(new Blob([excelFile], {type: 'text / csv; charset = utf-8;'}), fileName + ".xls");
         } else {
                var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);
                var link = document.createElement("a");
                link.href = uri;
                link.style = "visibility:hidden";
                link.download = fileName + ".xls";
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
		}
	};
	
	
	/**
	 * 设置需要打印出来的隐藏列字段
	 */
	function setNeedPrintHiddenFilds(needPrintHiddenFildArr) {
		var filds = needPrintHiddenFildArr || [];
		var needPrintHiddenFilds = {};
		for (var key in filds) {
			var fild = filds[key];
			needPrintHiddenFilds[fild] = fild;
		}
		
		return needPrintHiddenFilds;
	};
	
	
	/**
	 * 这里返回的是字段名称和表头文字的键值对
	 * 根据datagrid显示的表头，获取文字
	 */
	function getGridTitle(gridElementId, needPrintHiddenFilds) {
	    var titlename = "{";
	    var fields = $("#" + gridElementId).datagrid('getColumnFields');
	    
	    for (var i = 0; i < fields.length; i++) {
	        var option = $("#" + gridElementId).datagrid('getColumnOption', fields[i]);
	        if (option != null) { 
	        	var fild = option.field;
	        	var isPrintHiddenFild = needPrintHiddenFilds[fild] ? true : false;
	        	//过滤复选框和不需要打印的隐藏列
	        	if (fild != "checkItem" && (option.hidden != true || isPrintHiddenFild)) {
	        		titlename += "\"" + fild + "\":\"" + option.title + "\",";
	        	}
	        }
	    }
	    
	    titlename = titlename.substr(0, titlename.length - 1);
	    titlename += "}";
	    
	    return titlename;
	
	};
	
	
	function getGridFormatterByField(field, gridElementId) {
		var option = $("#" + gridElementId).datagrid('getColumnOption', field);
		if (option.formatter) {
			return option.formatter;
		}
		
		return null;
	};
	
	return {
		JSONToExcelConvertor : JSONToExcelConvertor
	};
})(jQuery);