<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>FPT Polytechnic</title>
<style>
body {
  font-family: "Lato", sans-serif;
}

.sidebar {
  height: 100%;
  width: 0; 
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}
.sidebar a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidebar a:hover {
  color: #f1f1f1;
}

.sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

.openbtn {
  font-size: 20px;
  cursor: pointer;
  background-color: white;
  color: black;
  padding: 10px 15px;
  border: none;
}



#main {
  transition: margin-left .5s;
  padding: 16px;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidebar {padding-top: 15px;}
  .sidebar a {font-size: 18px;}
}
</style>
</head>
	<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/all.min.css">
    <link rel="stylesheet" href="css/all.min2.css">
    <link rel="stylesheet" href="css/templatemo-style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<body class="container">
<main>
	<div id="loader-wrapper">
        <div id="loader"></div>
        <div class="loader-section section-left"></div>
        <div class="loader-section section-right"></div>
    </div>
    <div id="mySidebar" class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <a href="http://localhost:8080/ASMJAVA/admin">Home</a>
  <a href="<c:url value='/videoi?action=view'/>">View</a>
  <a href="<c:url value='/videoi?action=add'/>">Edit</a>
 
</div>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
              <div class="col-md-3 p-0">
                 <button class="openbtn" onclick="openNav()">☰</button>             	
            </div>  

         <div class="col-md-6 p-3">
                
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
             
                <li class="nav-item">
                    <a class="nav-link nav-link-3" href="video.jsp"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-4" href="http://localhost:8080/ASMJAVA/admin">Home</a>
                </li>
                <li class="nav-item">
                	<a class="nav-link nav-link-4" href="video?action=view">View</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-4" href="video?action=add">Edit</a>
                </li>
            </ul>
            </div>
        </div>
    </nav>
    		<div class="row d-flex flex-row-reverse bd-highlight">
        <div class="col-6">
            <select ng-model="dkOrder"  class="w-100 form-select w-25 border border-1 border-dark rounded-0">
                  <option selected disabled>Mặc định</option>
                <c:forEach items="${videos}"  var="item">
                	<option value="${item.href}">${item.title}</option>
                </c:forEach>            
              </select>
        </div>
        <div class="col-6">
            <select ng-model="dkFilter"  class="w-100  form-select w-25 border border-1 border-dark rounded-0">
                <option>Tất cả</option>
               
              </select>
        </div>
    </div>
    
	<div class="container row">
		
		<div class="row col-md-12 p-0">
					<table class="table">
						<thead>
							<tr>	
								<th scope="col">Title</th>
								<th scope="col">Description</th>
								<th scope="col">Link</th>
								<th scope="col">Poster</th>	
								<th scope="col">Action</th>
								
							</tr>
						</thead>
						<tbody>							
								<c:forEach items="${videos}" var="item">
									<tr>
										<td>${item.title}</td>
										<td>${item.descripition}</td>
										<td><a href="<c:url value='/video?action=watch&id=${item.href}'/>">${item.href}</a></td>
										<td>${item.href}</td>
										<td>
											<img alt="" src="${item.poster}" width="140px" height="100px">
												
										</td>
										<td><a href="<c:url value='videoi?action=edit&href=${item.href}'/>" class="btn btn-success">Edit</a></td>
									<td><button class="btn btn-danger" onclick="deleteVideo('${item.href}')">Delete</button></td>	
																	
									</tr>									
								</c:forEach>							
						</tbody>
					</table>
				</div>
	</div>
	</div>
	
    <footer class="tm-bg-gray pt-5 pb-3 tm-text-gray tm-footer">
        <div class="container-fluid tm-container-small">
            <div class="row">
                <div class="col-lg-6 col-md-12 col-12 px-5 mb-5">
                    <h3 class="tm-text-primary mb-4 tm-footer-title">About Catalog-Z</h3>
                    <p>Catalog-Z is free <a rel="sponsored" href="https://v5.getbootstrap.com/">Bootstrap 5</a> Alpha 2 HTML Template for video and photo websites. You can freely use this TemplateMo layout for a front-end integration with any kind of CMS website.</p>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-12 px-5 mb-5">
                    <h3 class="tm-text-primary mb-4 tm-footer-title">Our Links</h3>
                    <ul class="tm-footer-links pl-0">
                        <li><a href="#">Advertise</a></li>
                        <li><a href="#">Support</a></li>
                        <li><a href="#">Our Company</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-6 col-12 px-5 mb-5">
                    <ul class="tm-social-links d-flex justify-content-end pl-0 mb-5">
                        <li class="mb-2"><a href="https://facebook.com"><i class="fab fa-facebook"></i></a></li>
                        <li class="mb-2"><a href="https://twitter.com"><i class="fab fa-twitter"></i></a></li>
                        <li class="mb-2"><a href="https://instagram.com"><i class="fab fa-instagram"></i></a></li>
                        <li class="mb-2"><a href="https://pinterest.com"><i class="fab fa-pinterest"></i></a></li>
                    </ul>
                    <a href="#" class="tm-text-gray text-right d-block mb-2">Terms of Use</a>
                    <a href="#" class="tm-text-gray text-right d-block">Privacy Policy</a>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-7 col-12 px-5 mb-3">
                    Copyright 2020 Catalog-Z Company. All rights reserved.
                </div>
                <div class="col-lg-4 col-md-5 col-12 px-5 text-right">
                    Designed by <a href="https://templatemo.com" class="tm-text-gray" rel="sponsored" target="_parent">TemplateMo</a>
                </div>
            </div>
        </div>
    </footer>
    </main>
    
    <script src="js/plugins.js"></script>
    	 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
        </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
        </script>
       <script>
function openNav() {
  document.getElementById("mySidebar").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginLeft= "0";
}
</script>	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	function deleteVideo(href){	
		$.ajax({			
			url: '/ASMJAVA/videoi?action=delete&href=' + href
		}).then(function(){
			window.location.href = "/ASMJAVA/videoi?action=view";
		}).fail(function(){	
			alert("loi");
		});
	};
</script>
<script>
	var titleOrigin, hrefOrigin, descripitionOrigin, posterOrigin;
	$(document).ready(funtion(){
		titleOrigin = $('#title').val();
		hrefOrigin = $('#href').val();
		descripitionOrigin = $('#descripition').val();
		posterOrigin = $('#poster').val();
	});
	$('#resetBtn').click(function(){
		 $('#title').val(titleOrigin);
		 $('#href').val(hrefOrigin);
		 $('#descripition').val(descripitionOrigin);
		 $('#poster').val(posterOrigin);
	});
	
	$('#submitBtn').click(function(){
		var fromData = {
				'title': $('#title').val(),
				'descripition': $('#descripition').val(),
				'href': $('#href').val(),
				'poster': $('#poster').val()
		};
		$.ajax({
			url: '/ASMJAVA/admin/videoi?action=add',
			type: 'POST',
			data: fromData					
		}).then(function(data){
			window.location.href = "http://localhost:8080/ASMJAVA/admin/videoi?action=view";
		}).fail(function(error){
			alert("loi");
		});
	});
</script>

</body>
</html>