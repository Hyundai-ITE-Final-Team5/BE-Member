## Member

### Member(로그인/회원가입)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /login |  | {"mid":"아이디","mpassword":"비밀번호"} | {"result": "성공/실패여부","jwt": "토큰","mid": "아이디"}|
| `post`  /join |  | {"mid":"user1","mpassword":"12345","mname":"사용자1","memail":"user@mycompany.com","mphone":"01022227777","mbirth":"1990-01-01","mgender":"1"} | {"result": "성공여부"}|

### likes(찜)

| API | 설명 | input data | output data |
| --- | --- | --- | --- |
| `post`  /login |  | {"mid":"아이디","mpassword":"비밀번호"} | {"result": "성공/실패여부","jwt": "토큰","mid": "아이디"}|
| `post`  /join |  | {"mid":"user1","mpassword":"12345","mname":"사용자1","memail":"user@mycompany.com","mphone":"01022227777","mbirth":"1990-01-01","mgender":"1"} | {"result": "성공여부"}|

### cart(장바구니)

| API | 설명 | input data |
| --- | --- | --- |
| `get`  /list/brand/{bno} |  | ?pageNo=1 |
| `get`  /list/category |  | ?depth1=WOMEN&depth2=DRESS&depth3=MINI%20DRESS |
| `get`  /list/addlike/{pid} |  |  |
| `get`  /list/delike/{pid} |  |  |


