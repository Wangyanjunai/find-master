<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
		<div class="container">
			<div class="row clearfix">
					<div class="col-md-12 column">
						    <form method="post" enctype="multipart/form-data" id="form1" action="/find/dynamic/fileUpload.do">  
						        <table>
							         <tr>
							            <td>请选择上传文件：</td>  
							            <td><input id="upfile" type="file" name="upfile" accept=".xls,.xlsx" /></td>  
							            <td><input type="submit" value="提交" οnclick="checkData();" /></td>  
							         </tr>  
						        </table>
						 </form>
					</div>
				</div>
			</div>
    	</div>
    </div>
	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">  
	         //JS校验form表单信息
	         function checkData() {  
	            var fileDir = $("#upfile").val();
	            alert('fileDir='+fileDir); 
	            var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
	            if("" == fileDir) {
	                alert("选择需要导入的Excel文件！");
	                return false;  
	            }  
	            if(".xls" != suffix && ".xlsx" != suffix){  
	                alert("选择Excel格式的文件导入！");  
	                return false;  
	            }
	            return true;  
	         }  
	</script>
</body>
</html>