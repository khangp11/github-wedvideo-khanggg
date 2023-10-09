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
            <h2 class="col-12 tm-text-primary">
                List History
            </h2>
        </div>
        <div class="row mb-3 tm-gallery">
        <c:forEach var="item" items="${videos}">
            <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-12 mb-5">
                <h5>${item.title}</h5>
                <figure class="effect-ming tm-video-item">
                    <img src="<c:url value='${item.poster}'/>" alt="Image" class="img-fluid">
                    <figcaption class="d-flex align-items-center justify-content-center">
                        <h2>Perfumes</h2>
                         <a href="<c:url value='/video?action=watch&id=${item.href}'/>">View more</a>
                    </figcaption>                    
                </figure>
                <div class="d-flex justify-content-between tm-text-gray">
                    <span class="tm-text-gray-light">18 Oct 2020</span>
                    <span>11,402 views</span>
                </div>
            </div>
        </c:forEach>      
    </div> <!-- container-fluid, tm-container-content -->
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