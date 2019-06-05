<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="navbar" class="navbar navbar-default">
	<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>
	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

		<div class="navbar-header pull-left">
			<a href="homeAdmin.html" class="navbar-brand"> <small> <i
					class="fa fa-home"></i> Hệ thống đặt sân trực tuyến
			</small>
			</a>
		</div>
		<div class="navbar-header "
			style="margin-left: 650px; color: white; margin-top: 10px; font-size: 14px;">
			<i class="fa fa-angle-double-left"></i> Chào mừng bạn đến với trang
			quản trị <i class="fa fa-angle-double-right"></i>
		</div>

		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue"><a data-toggle="dropdown" href="#"
					class="dropdown-toggle"> <img class="nav-user-photo"
						src="<c:url value='/template/admin/assets/avatars/vu1.jpg' />" alt="img" /> <span class="user-info">
							<small style="font-size: 14px;">Xin chào, Vũ</small>
					</span> <i class="ace-icon fa fa-caret-down"></i>
				</a>

					<ul
						class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

						<li><a href=""> <i class="ace-icon fa fa-user"></i>Hồ sơ
						</a></li>
						<li><a href=""> <i class="ace-icon fa fa-edit"></i>Đổi
								mật khẩu
						</a></li>

						<li class="divider"></li>

						<li><a href="../index.html"> <i
								class="ace-icon fa fa-power-off"></i> Đăng xuất
						</a></li>
					</ul></li>
			</ul>
		</div>

	</div>
</div>
<!-- the end nav -->
