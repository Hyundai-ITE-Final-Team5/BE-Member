## Member

### Member(로그인/회원가입)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /login |  | {"mid":"아이디","mpassword":"비밀번호"} | {"result": "성공/실패여부","jwt": "토큰","mid": "아이디"}|
| `post`  /join |  | {"mid":"user1","mpassword":"12345","mname":"사용자1","memail":"user@mycompany.com","mphone":"01022227777","mbirth":"1990-01-01","mgender":"1"} | {"result": "성공여부"}|

### likes(찜)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /member/likes/likelist | 찜목록  |  | {"likedlist": [{"pid": "CM2B7NOT219W","pname": "크롭 데님 점퍼","pstatus": 1,"pcprice": 545000,"pcimg1": "http://newmedia.thehandsome.com/CM/2B/FW/CM2B7NOT219W_CP_W01.jpg/dims/resize/684x1032/"},{},{},{}]}|
| `delete` http://localhost/member/likes/deletelike  | 찜취소 | {"pid":"CM2B0KCD231WP1"} | {"result": "성공여부"} |

### cart(장바구니)

| API | 설명 | input data |
| --- | --- | --- |
| `get`  |  |  |
| `get`  |  |  |
| `get`  |  |  |
| `get`  |  |  |


