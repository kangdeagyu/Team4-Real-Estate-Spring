<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Kakao Map API</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<body>
	<div>
        <button onclick="getXY()">좌표 변환</button>
    </div>
	
	<br/>
	<div>
        <h3>결과</h3>
		도로명, x, y <br>
        <div id="results"></div>
    </div>

    <script type="text/javascript">
		function getXY(){
			const addressList = document.querySelectorAll('#addressList li');
			var API_KEY = 'ef894ee905a0643b7844daf7341d7569';
			const resultsDiv = document.getElementById('results');

			addressList.forEach(function (addressItem) {
                const address = addressItem.textContent;
				axios({
					method: 'get',
					url: 'https://dapi.kakao.com/v2/local/search/address.json?query=' + address,
					headers: {'Authorization': 'KakaoAK ' + API_KEY }
				})
				.then(function (response) {
					var x = response.data.documents[0].x;
					var y = response.data.documents[0].y;

					const resultSpan = document.createElement('span');
                    resultSpan.innerHTML = address + ',' + x + ',' + y + '<br>';
                    resultsDiv.appendChild(resultSpan);
					// document.getElementById('address').textContent = address;
					// document.getElementById('getx').textContent = x;
					// document.getElementById('gety').textContent = y;
					console.log("x: " + x + ", y: " + y);
				});
			});
		}
    </script>


	<div>
		<ul id="addressList">
			<c:forEach items="${list}" var="dto">
				<li>
					${dto}
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>

