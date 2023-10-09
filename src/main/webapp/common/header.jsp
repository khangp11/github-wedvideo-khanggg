<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="loader-wrapper">
	<div id="loader"></div>

	<div class="loader-section section-left"></div>
	<div class="loader-section section-right"></div>

</div>
<nav class="navbar navbar-expand-lg">
	<div class="container-fluid">
		<a class="navbar-brand" href="<c:url value='indexasm'/>"> <i class="fas fa-film mr-2"></i>
			VIDEO CLIP
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-bars"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto mb-2 mb-lg-0">
				<c:choose>
					<c:when test="${not empty sessionScope.currentUser}">
						<li class="nav-item">
						<a class="nav-link nav-link-1 active"
							aria-current="page"  data-toggle="modal" data-target="#chagePassModal">Welcome, ${sessionScope.currentUser.username}</a></li>
						</li>
						<li class="nav-item"><a class="nav-link nav-link-4"
							href="favorite">My Favorites</a></li>
						<li class="nav-item"><a class="nav-link nav-link-4"
							href="history">History</a></li>
						<li class="nav-item"><a class="nav-link nav-link-4"
							href="logout">Log out</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link nav-link-2"
							href="login">Login</a></li>
						<li class="nav-item"><a class="nav-link nav-link-3"
							href="register">Register</a></li>
						<li class="nav-item"><a class="nav-link nav-link-4"
							href="forgotpass">Forgot Password</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>
<!-- Modal -->
<div class="modal fade" id="chagePassModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Chage Password</h5>
        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       	<div class="form-group">
	       <input type="password" name="currentPass" id="currentPass" class="form-control rounded-0" placeholder="Current Password" required />
	       </div>
	       <div class="form-group">
	       <input type="password" name="newPass" id="newPass" class="form-control rounded-0" placeholder="New Password " required />
	       </div>
	       <h5 style="color: red" id="messageChagePass"></h5>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
        <button type="button" id="chagePassBtn" class="btn btn-warning">Submit</button>
      </div>
    </div>
  </div>
</div>
