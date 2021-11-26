## Member

### Member

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /login | 로그인 | {"mid":"아이디","mpassword":"비밀번호"} | {"result": "성공/실패여부","jwt": "토큰","mid": "아이디"}|
| `post`  /join | 회원가입  | {"mid":"아이디","mpassword":"비밀번호","mname":"사용자1","memail":"user@mycompany.com","mphone":"01022227777","mbirth":"1990-01-01","mgender":"1"} | {"result": "성공여부"}|
| `post`  /member/mypage | 마이페이지 |  | {"mid": "아이디","mpassword": "null","mname": "사용자1","memail": "user@mycompany.com","mphone": "01022227777","mtel": null,"mzipcode": null,"maddress1": null,"maddress2": null,"mbirth": "1990-01-01T00:00:00.000+00:00","mgender": 1,"mrefid": null,"mlogintype": "1","mtosno": 1,"menabled": 1,"mrole": "ROLE_USER","mgrade": 1,"mmileage": 0}|
| `post`  /member/modifyinfo | 회원정보수정 | {"mphone":"01011112222","memail":"","mtel":"","mzipcode": "","maddress1": "", "maddress2" : ""} | {"result": "성공/실패여부"}|
| `post`  /idcheck | 아이디중복확인 | {"mid": "아이디"} | {"result": "possible/duplicate"}|


### likes(찜)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /member/likes/likelist | 찜목록  |  | [{"pid": "CM2B7NOT219W","pname": "크롭 데님 점퍼","pstatus": 1,"pcprice": 545000,"pcimg1": "http://newmedia.thehandsome.com/CM/2B/FW/CM2B7NOT219W_CP_W01.jpg/dims/resize/684x1032/"},{},{},{}]|
| `delete` /member/likes/deletelike  | 찜취소 | {"pid":"CM2B0KCD231WP1"} | {"result": "성공여부"} |
| `post` /member/likes/addlike  | 찜하기 | {"pid":"CM2B0KCD231WP1"} | {"result": "성공여부"} |

### cart(장바구니)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post` /member/cart/cartlist | 장바구니목록 |  | [{"bname": "SJSJ","pid": "SJ2B3WJM401W","pname": "셔링 집업 점퍼","pccolorcode": "CR","pcimg1": "psid": "SJ2B3WJM401W_CR_76","psstock": 1,"psize": "76","pquantity": 1,"pcprice": 318500}, {} {} ]|
| `delete` /member/cart/deletecart  | 장바구니삭제 | {"psid":"SJ2B3WOP731W_BK_82"} | {"result": "성공여부"} |
| `post` /member/cart/addcart  | 장바구니추가 | {"psid":"SJ2B4WOP759W_MB_82","pquantity":"1"} | {"result": "성공여부"} |
| `post` /member/cart/getcolorsize | 장바구니 변경시 색상,사이즈가져오기 | {"pid":"CM2B1KPC502WM1"} | postman참고.. |
| `post` /member/cart/changecart | 장바구니 변경사항 저장 | {"oldpsid":"SJ2B3WSH842W_LB_82","newpsid":"SJ2B3WSH842W_LB_82","pquantity":"2"} | {"result": "성공여부"} |

### event(이벤트)
| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `get` /event/eventlist | 이벤트리스트 | ?pageNo=1 | [{"eno": 1,"etitle": "크리스마스 이벤트","econtent": null,"eissuedate": "2021-11-26T02:26:54.794+00:00","eexpiredate": "2021-12-25T02:26:58.040+00:00","elimitcount": 0,"ecount": 0,"eimg": "이미지url","ediscount": 0,"estatus": 0},{}{}]|
| `get` /event/eventdetail | 이벤트상세페이지 | ?eno=1 | {"eno": 1,"etitle": "앱다운로드이벤트","econtent": "크리스마스이벤트","eissuedate": "2021-11-10T06:04:41.780+00:00","eexpiredate": "2021-12-25T06:04:44.366+00:00","elimitcount": 0,"ecount": 0,"eimg": null,"ediscount": 0,"estatus": 0,"edetailimg": "http://cdn.thehandsome.com/pc/event/detail/image/handsome_202111/event_black_friday_211117_pc_img_01_02_1.jpg"}|

### couponDetail(쿠폰)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `get` /member/coupon/couponlist | 쿠폰목록 |  | [{"ecoupontitle": "앱다운로드 쿠폰","ediscount": 10,"cpstatus": 1,"cpissuedate": "2021-11-26T08:09:01.133+00:00","cpexpiredate": "2021-11-29T08:09:04.048+00:00"},{},{}]|
| `get` /member/coupon/download | 쿠폰다운 |  ?eno=2 | [{"result":"needlogin" or "already" or "exhausted" or "expired" or "enoerror"}|
