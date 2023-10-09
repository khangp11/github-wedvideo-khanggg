<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@ include file ="/common/taglib.jsp" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    
    <%@ include file ="/common/head.jsp" %>
    
</head>
<body>
<%@ include file ="/common/header.jsp" %>

    <div class="container container-fluid tm-container-content tm-mt-60">
        <div class="row mb-4">   
        </div>
        <div class="row">
            <h2 class="col-3 tm-text-primary">
                List Favorite
            </h2>
        </div>
        <div class="row tm-mb-90 tm-gallery">
        <c:forEach items="${videos}" var="video">
        	<div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
        		<h5 class="tm-text-secondary" style="white-space: nowrap; overflow: hidden;">
        			${video.title}
        		</h5>
                <figure class="effect-ming tm-video-item">
                    <img src="<c:url value='${video.poster}'/>" alt="Image" class="img-fluid">
                    <figcaption class="d-flex align-items-center justify-content-center">
                        
                        <a href="<c:url value='/video?action=watch&id=${video.href}'/>">View more</a>
                    </figcaption>                    
                </figure>
                <div class="d-flex justify-content-between tm-text-gray">
                    <span class="tm-text-gray-light">${video.shares} Shares</span>
                    <span>${video.view} views</span>
                </div>
            </div>
        </c:forEach>
      
        </div>
        <div class="row tm-mb-90">
            <div class="col-12 d-flex justify-content-between align-items-center tm-paging-col">
                <a href="javascript:void(0);" class="btn btn-primary tm-btn-prev mb-2 disabled">Previous</a>
                <div class="tm-paging d-flex">
                    <a href="javascript:void(0);" class="active tm-paging-link">1</a>
                    <a href="javascript:void(0);" class="tm-paging-link">2</a>
                    <a href="javascript:void(0);" class="tm-paging-link">3</a>
                    <a href="javascript:void(0);" class="tm-paging-link">4</a>
                </div>
                <a href="javascript:void(0);" class="btn btn-primary tm-btn-next">Next Page</a>
            </div>            
        </div>
    </div> <!-- container-fluid, tm-container-content -->

   <%@ include file ="/common/footer.jsp" %>
   
</body>
</html>