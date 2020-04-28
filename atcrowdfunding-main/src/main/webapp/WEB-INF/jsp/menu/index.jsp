<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- 静态包含 -->
<%@ include file="/WEB-INF/jsp/common/css.jsp"%>
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>

<body>

	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/WEB-INF/jsp/common/sidebar.jsp"></jsp:include>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 菜单树
						</h3>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/common/js.jsp"%>
	<script type="text/javascript">
		$(function() { //页面加载完成需要执行的事件处理
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
		
			initTree();
		});
		
		function initTree() {
			var settings = {
					data: {
						simpleData: {
							enable: true,
							idKey: 'id',
							pIdKey: 'pid'
						}
					},
					view: {
						addDiyDom: function(treeId, treeNode) {
							$("#"+treeNode.tId+"_ico").removeClass();
							$("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>");
						}
					}
			};
			
			var url = "${PATH}/menu/loadTree";
			var json = {};
			$.get(url, json, function(res) {
				var zNodes = res;
				zNodes.push({"id": 0,"name": "系统菜单","icon":""});
				$.fn.zTree.init($("#treeDemo"), settings, zNodes);
			});
		}
		
	</script>
</body>
</html>
