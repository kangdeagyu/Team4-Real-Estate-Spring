<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
					console.log(x);
					console.log(y);
				});
			});
		}
    </script>

	<div>
		<ul id="addressList">
			<li>언주로 3</li>
			<li>개포로 307</li>
			<li>개포로109길 69</li>
			<li>개포로 310</li>
			<li>선릉로 7</li>
			<li>삼성로 14</li>
			<li>삼성로4길 17</li>
			<li>개포로 516</li>
			<li>언주로 105</li>
			<li>언주로 110</li>
			<li>논현로2길 61-4</li>
			<li>개포로28길 28</li>
			<li>개포로109길 21</li>
			<li>논현로2길 36</li>
			<li>논현로2길 38</li>
			<li>개포로109길 9</li>
			<li>언주로 21</li>
			<li>논현로2길 22</li>
			<li>선릉로8길 5</li>
			<li>개포로 411</li>
			<li>개포로 311</li>
			<li>개포로31길 9-9</li>
			<li>개포로31길 9-5</li>
			<li>선릉로18길 12</li>
			<li>개포로 303</li>
			<li>언주로 107</li>
			<li>개포로 409</li>
			<li>언주로 103</li>
			<li>개포로15길 32-8</li>
			<li>삼성로 11</li>
			<li>선릉로 8</li>
			<li>논현로2길 50</li>
			<li>개포로 264</li>
			<li>논현로2길 62</li>
			<li>개포로 405</li>
			<li>논현로2길 34</li>
			<li>개포로31길 3-12</li>
			<li>양재대로 379</li>
			<li>논현로4길 28</li>
		</ul>
	</div>
</body>
</html>

