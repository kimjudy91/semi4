
<!DOCTYPE HTML>
<html>
<head><title></title>
<meta http-equiv="Content-Type" Content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<style type="text/css">
<!-- -->
	body {font-size:11pt; padding:0; margin:0; text-align: center;}
	h3 {color: #85144b; font-size: 14pt; margin:10 auto; padding: 10px;}
	.contents {width: 800px; height: 400px; background-color: #d6d6d6; margin: 0 auto;}
	
	/* banner */
	.banner {position: relative; width: 340px; height: 210px; top: 50px;  margin:0 auto; padding:0; overflow: hidden;}
	.banner ul {position: absolute; margin: 0px; padding:0; list-style: none; }
	.banner ul li {float: left; width: 340px; height: 210px; margin:0; padding:0;}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script language="JavaScript">

	$(document).ready(function() {
		//사용할 배너
		var $banner = $(".banner").find("ul");

		var $bannerWidth = $banner.children().outerWidth();//배너 이미지의 폭
		var $bannerHeight = $banner.children().outerHeight(); // 높이
		var $bannerLength = $banner.children().length;//배너 이미지의 갯수
		var rollingId;

		//정해진 초마다 함수 실행
		rollingId = setInterval(function() { rollingStart(); }, 3000);//다음 이미지로 롤링 애니메이션 할 시간차


		banner.mouseout(function(){
			rollingId = setInterval(function() { rollingStart(); }, 300);
			$(this).css("cursor", "default");
		});
		
		function rollingStart() {
			$banner.css("width", $bannerWidth * $bannerLength + "px");
			$banner.css("height", $bannerHeight + "px");
			//alert(bannerHeight);
			//배너의 좌측 위치를 옮겨 준다.
			$banner.animate({left: - $bannerWidth + "px"}, 1500, function() { //숫자는 롤링 진행되는 시간이다.
				//첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
				$(this).append("<li>" + $(this).find("li:first").html() + "</li>");
				//뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
				$(this).find("li:first").remove();
				$(this).css("left", 0);
				
			});
		}
	}); 

</script>

</head>
<body>


		<div class="banner">
			<ul>
				<li><img src="https://ncache.ilbe.com/files/attach/new/20150326/377678/8823920/5537097613/7108a74fda00e617d1f4b9c33b5ab5f0.jpg" width="340" height="210px"></li>
				<li><img src="https://www.nemopan.com/files/attach/images/166591/120/156/005/%EC%98%9B%EB%82%A0%EA%B4%91%EA%B3%A0.jpg" width="340" height="210px"></li>
				<li><img src="http://app.jjalbang.today/jj1lW.jpg" width="340" height="210px"></li>
				<li><img src="https://t1.daumcdn.net/cfile/tistory/252BF73751F7502C1B" width="340" height="210px"></li>
				<li><img src="http://down.humoruniv.org/hwiparambbs/data/pdswait/a_3693262318_80bb3e0277918d3c23c251ab33a5b1a912a2de9c.jpg" width="340" height="210px"></li>
			</ul>
		</div>
	</div>

</body>
</html>