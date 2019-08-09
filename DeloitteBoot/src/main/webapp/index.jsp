
<html>
<head>
	<title></title>
</head>

<style>
#backgroundimage {
background-image: url("Business.jpg");
width: 100vw;
height: 100vh; 
background-size: 100% 100% ;
background-repeat: no-repeat;
position: relative;
} 
#parent
{
width: 100%;
height:100%
}
</style>
<body>
<div id="parent">
<jsp:include page="header.jsp"/>
	<div>
 		<h3 class="center-align" style="colour:blue;"><marquee direction="left" behavior="alternate" scrollamount="12" onmouseover="this.stop()"  onmouseout="this.start()" margin-bottom="10px">Deloitte Employee Management System</marquee></h3>
 	</div>
 	<div id="backgroundimage">
 	</div>
</div> 	
 </body>
 </html>
 
<jsp:include page="footer.jsp"/>