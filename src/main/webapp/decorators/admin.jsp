<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><dec:title default="Trang chủ"/></title>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/font-awesome/css/font-awesome.min.css'/>" />
<link rel="icon"
	href="<c:url value='/template/admin/assets/font-awesome/css/font-awesome.min.css'/>" />
<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/colorbox.min.css'/>" />

<!-- ace styles -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/ace.min.css'/>"
	class="ace-main-stylesheet" id="main-ace-style" />

<!-- datable -->
<link
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">
<!-- ace-extra.min.js -->
<script
	src="<c:url value='/template/admin/assets/js/ace-extra.min.js'/>"></script>
<!-- scripit để thêm nhiều file -->
</head>
<body class="no-skin">
	<!-- header -->
	<%@include file="/common/admin/menu.jsp"%>
	<!-- body -->
	<div class="main-content">
		<div class="main-content-inner">
			<dec:body />
		</div>
	</div>
	<!-- footer -->
	<%@include file="/common/admin/footer.jsp"%>

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
	<!-- ============================================================================================== -->
	<script
		src="<c:url value='/template/admin/assets/js/jquery.2.1.1.min.js'/>"></script>
	<script src="<c:url value='/template/admin/assets/js/jquery.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>

	<script type="text/javascript">
		window.jQuery
				|| document.write("<script src='assets/js/jquery.min.js'>"
						+ "<"+"/script>");
	</script>

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<!-- ace scripts -->
	<script
		src="<c:url value='/template/admin/assets/js/ace-elements.min.js'/>"></script>
	<script src="<c:url value='/template/admin/assets/js/ace.min.js'/>"></script>

	<!-- page specific plugin scripts -->
	<script
		src="<c:url value='/template/admin/assets/js/dataTables.tableTools.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/js/dataTables.colVis.min.js'/>"></script>
	<!-- Datatables -->
	<script
		src="<c:url value='/template/admin/vendors/datatables.net/js/jquery.dataTables.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/vendors/datatables.net-buttons/js/dataTables.buttons.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/vendors/datatables.net-buttons/js/buttons.flash.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/vendors/datatables.net-buttons/js/buttons.html5.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/vendors/datatables.net-buttons/js/buttons.print.min.js'/>"></script>

	<!-- Custom Theme Scripts -->
	<script src="<c:url value='/template/admin/assets/js/custom.min.js'/>"></script>
</body>
</html>