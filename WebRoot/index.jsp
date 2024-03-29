<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>forret管理系统</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="adminlte/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="adminlte/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="adminlte/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="adminlte/dist/css/AdminLTE.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="adminlte/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="adminlte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.csss">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a class="logo"> <span class="logo-mini">系统</span> <span
				class="logo-lg">管理系统</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="push-menu"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->
						<li class="dropdown messages-menu">
							<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-envelope-o"></i> <span
								class="label label-success">4</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 4 messages</li>
								<li>
									<!-- inner menu: contains the messages -->
									<ul class="menu">
										<li>
											<!-- start message --> <a href="#">
												<div class="pull-left">
													<!-- User Image -->
													<img src="adminlte/dist/img/user2-160x160.jpg"
														class="img-circle" alt="User Image">
												</div> <!-- Message title and timestamp -->
												<h4>
													Support Team <small><i class="fa fa-clock-o"></i> 5
														mins</small>
												</h4> <!-- The message -->
												<p>Why not buy a new awesome theme?</p>
										</a>
										</li>
										<!-- end message -->
									</ul> <!-- /.menu -->
								</li>
								<li class="footer"><a href="#">See All Messages</a></li>
							</ul>
						</li>
						<!-- /.messages-menu -->

						<!-- Notifications Menu -->
						<li class="dropdown notifications-menu">
							<!-- Menu toggle button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-bell-o"></i> <span
								class="label label-warning">10</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 10 notifications</li>
								<li>
									<!-- Inner Menu: contains the notifications -->
									<ul class="menu">
										<li>
											<!-- start notification --> <a href="#"> <i
												class="fa fa-users text-aqua"></i> 5 new members joined
												today
										</a>
										</li>
										<!-- end notification -->
									</ul>
								</li>
								<li class="footer"><a href="#">View all</a></li>
							</ul>
						</li>
						<!-- Tasks Menu -->
						<li class="dropdown tasks-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="fa fa-flag-o"></i> <span
								class="label label-danger">9</span>
						</a>
							<ul class="dropdown-menu">
								<li class="header">You have 9 tasks</li>
								<li>
									<!-- Inner menu: contains the tasks -->
									<ul class="menu">
										<li>
											<!-- Task item --> <a href="#"> <!-- Task title and progress text -->
												<h3>
													Design some buttons <small class="pull-right">20%</small>
												</h3> <!-- The progress bar -->
												<div class="progress xs">
													<!-- Change the css width attribute to simulate progress -->
													<div class="progress-bar progress-bar-aqua"
														style="width: 20%" role="progressbar" aria-valuenow="20"
														aria-valuemin="0" aria-valuemax="100">
														<span class="sr-only">20% Complete</span>
													</div>
												</div>
										</a>
										</li>
										<!-- end task item -->
									</ul>
								</li>
								<li class="footer"><a href="#">View all tasks</a></li>
							</ul>
						</li>
						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<img src="resource/images/avatar.jpg" class="user-image"
								alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs">Forrst Woo</span>
						</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img
									src="resource/images/avatar.jpg" class="img-circle"
									alt="User Image">

									<p>
										Forrst Woo - 初级程序员 <small>2010-10-11</small>
									</p></li>
								<!-- Menu Body -->
								<li class="user-body">
									<div class="row">
										<div class="col-xs-4 text-center">
											<a href="#">Followers</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Sales</a>
										</div>
										<div class="col-xs-4 text-center">
											<a href="#">Friends</a>
										</div>
									</div> <!-- /.row -->
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="#" class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul>
						</li>
						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"><i
								class="fa fa-gears"></i></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="resource/images/avatar.jpg" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p>Forrst Woo</p>
						<!-- Status -->
						<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
					</div>
				</div>

				<!-- search form (Optional) -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- /.search form -->

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu" data-widget="tree">
					<li class="active treeview"><a href="#"> <i
							class="fa fa-dashboard"></i> <span>系统管理</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li class="active"><a href="getClassLib" target="menuFrame"><i
									class="fa fa-circle-o"></i>用户管理</a></li>
							<li><a href="index2.html"><i class="fa fa-circle-o"></i>组织管理</a></li>
							<li><a><i class="fa fa-circle-o"></i>权限管理</a></li>
							<li><a><i class="fa fa-circle-o"></i>在线用户</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
							<span>数据管理</span> <span class="pull-right-container"> <span
								class="label label-primary pull-right">4</span>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="getMData" target="menuFrame"><i
									class="fa fa-circle-o"></i>查询数据</a></li>
							<li><a href="insertOuZhi" target="menuFrame"><i
									class="fa fa-circle-o"></i>赛事赔率</a></li>
							<li><a href="insertYaPan" target="menuFrame"><i
									class="fa fa-circle-o"></i>亚盘</a></li>
							<li><a href="insertDaxiaoqiu" target="menuFrame"><i
									class="fa fa-circle-o"></i> 大小球</a></li>
							<li><a href="updateBiFa" target="menuFrame"><i
									class="fa fa-circle-o"></i>必发数据跟新</a></li>


							<li><a href="updateMEvent" target="menuFrame"><i
									class="fa fa-circle-o"></i> 事件统计</a></li>
						</ul></li>
					<li class="treeview"><a href="#"> <i class="fa fa-th"></i>
							<span>足球赛事初始化</span> <span class="pull-right-container"> <small
								class="label pull-right bg-green">new</small>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="initYingChao" target="menuFrame"><i
									class="fa fa-circle-o"></i>英超数据</a></li>
							<li><a href="initXiJia" target="menuFrame"><i
									class="fa fa-circle-o"></i>西甲数据</a></li>
							<li><a href="initYiJia" target="menuFrame"><i
									class="fa fa-circle-o"></i>意甲数据</a></li>
							<li><a href="initBiJia" target="menuFrame"><i
									class="fa fa-circle-o"></i>比甲数据</a></li>
							<li><a href="initPuChao" target="menuFrame"><i
									class="fa fa-circle-o"></i>葡超数据</a></li>
							<li><a href=initDeJia target="menuFrame"><i
									class="fa fa-circle-o"></i>白超数据</a></li>
							<li><a href="initFaJia" target="menuFrame"><i
									class="fa fa-circle-o"></i>法甲数据</a></li>
							<li><a href="initPuChao" target="menuFrame"><i
									class="fa fa-circle-o"></i>葡超数据</a></li>
							<li><a href="initHeJia" target="menuFrame"><i
									class="fa fa-circle-o"></i>克甲数据</a></li>
									<li><a href="initXiongJia" target="menuFrame"><i
									class="fa fa-circle-o"></i>匈甲数据</a></li>
							<li><a href="initOuGuan" target="menuFrame"><i
									class="fa fa-circle-o"></i>欧冠数据</a></li>
							<li><a href="initEurope" target="menuFrame"><i
									class="fa fa-circle-o"></i>欧洲数据</a></li>
							<li><a href="initConutry" target="menuFrame"><i
									class="fa fa-circle-o"></i>国家数据</a></li>
									<li><a href="initBaiChao" target="menuFrame"><i
									class="fa fa-circle-o"></i>白超数据</a></li>
						</ul></li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content container-fluid">
				<iframe id="menuFrame" name="menuFrame" src="welcome.jsp"
					style="height: 1000px" scrolling="yes" frameborder="0"
					height="100%" width="100%"></iframe>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs">Anything you want</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2016 <a href="#">Company</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active"><a href="#control-sidebar-home-tab"
					data-toggle="tab"><i class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:;"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<script src="adminlte/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="adminlte/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="adminlte/dist/js/adminlte.min.js"></script>
</body>
</html>
