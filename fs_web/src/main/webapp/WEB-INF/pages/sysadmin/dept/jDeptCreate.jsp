<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('deptAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">上级部门：</td>
	            <td class="tableContent">
					<s:select name="parent.id" list="#deptList" headerKey="" headerValue="--请选择--" listKey="id" listValue="deptName"></s:select>

					<%--<!-- struts的框架，下拉菜单--%>
						<%--name:下拉框的名称--%>
						<%--list:绑定的集合名称--%>
						<%--headerKey:代表的首先value--%>
						<%--headerValue:代表的首选文本--%>
						<%--listKey: value=# {id}--%>
						<%--listValue:中间文--%>
						<%--!!!  list="#deptList" 值栈 root,context域，context域需要加#--%>

					<%---->--%>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">部门名称：</td>
	            <td class="tableContent"><input type="text" name="deptName" value=""/></td>
	        </tr>		
		</table>
	</div>
 </form>
</body>
</html>