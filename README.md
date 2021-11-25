## Member

### Member

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /login | 로그인 | {"mid":"아이디","mpassword":"비밀번호"} | {"result": "성공/실패여부","jwt": "토큰","mid": "아이디"}|
| `post`  /join | 회원가입  | {"mid":"아이디","mpassword":"비밀번호","mname":"사용자1","memail":"user@mycompany.com","mphone":"01022227777","mbirth":"1990-01-01","mgender":"1"} | {"result": "성공여부"}|
| `post`  /member/mypage | 마이페이지 |  | {"mid": "아이디","mpassword": "null","mname": "사용자1","memail": "user@mycompany.com","mphone": "01022227777","mtel": null,"mzipcode": null,"maddress1": null,"maddress2": null,"mbirth": "1990-01-01T00:00:00.000+00:00","mgender": 1,"mrefid": null,"mlogintype": "1","mtosno": 1,"menabled": 1,"mrole": "ROLE_USER","mgrade": 1,"mmileage": 0
}|

### likes(찜)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /member/likes/likelist | 찜목록  |  | {"likedlist": [{"pid": "CM2B7NOT219W","pname": "크롭 데님 점퍼","pstatus": 1,"pcprice": 545000,"pcimg1": "http://newmedia.thehandsome.com/CM/2B/FW/CM2B7NOT219W_CP_W01.jpg/dims/resize/684x1032/"},{},{},{}]}|
| `delete` /member/likes/deletelike  | 찜취소 | {"pid":"CM2B0KCD231WP1"} | {"result": "성공여부"} |
| `post` /member/likes/addlike  | 찜하기 | {"pid":"CM2B0KCD231WP1"} | {"result": "성공여부"} |

### cart(장바구니)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post` /member/cart/cartlist | 장바구니목록 |  | {"cartedlist": [{"bname": "SJSJ","pid": "SJ2B3WJM401W","pname": "셔링 집업 점퍼","pccolorcode": "CR","pcimg1": "psid": "SJ2B3WJM401W_CR_76","psstock": 1,"psize": "76"}, {} {} ]}|
| `delete` /member/cart/deletecart  | 장바구니삭제 | {"psid":"SJ2B3WOP731W_BK_82"} | {"result": "성공여부"} |
| `post` /member/cart/addcart  | 장바구니추가 | {"psid":"SJ2B4WOP759W_MB_82","pquantity":"1"} | {"result": "성공여부"} |
| `post` /member/cart/getcolorsize | 장바구니 변경시 색상,사이즈가져오기 | {"pid":"CM2B1KPC502WM1"} | postman참고.. |
| `post` /member/cart/changecart | 장바구니 변경사항 저장 | {"oldpsid":"SJ2B3WSH842W_LB_82","newpsid":"SJ2B3WSH842W_LB_82","pquantity":"2"} | {"result": "성공여부"} |

