git ## db

- 로컬에 db 계정 먼저 해주세용
  - user: wbc
  - password: wbc12345
  - database:wbc
- 아래 명령어 바로 입력하시면 될꺼에요


    CREATE USER 'wbc'@'%' IDENTIFIED BY 'wbc12345';
    GRANT ALL PRIVILEGES ON wbc.* TO 'wbc'@'%';
    FLUSH PRIVILEGES;

## aws

    cloud.aws.credentials.access-key=짦은데 복잡한거
    cloud.aws.credentials.secret-key=길고 복잡한거
    cloud.aws.s3.bucket=s3ARN
    cloud.aws.region.static=ap-northeast-2

## Commit Convention
1. 타입은 소문자로 작성 후 콜론(:)으로 마무리
2. 제목과 본문을 한 줄 띄어 구분
3. 제목은 50자 이내
4. 제목 첫 글자는 대문자
5. 제목 끝에 마침표 X
6. 제목은 명령문으로 작성, 과거형 X
7. 제목에 상세하게 작성하기!!!!!

### Header
|  | emoticon | 의미  | 설명                             |
| --- |----------|-------|--------------------------------|
| `:tada:` | 🎉       | init  | 프로젝트 생성                        |
| `:sparkles:` | ✨        | feat  | 기능 구현 첫 커밋 할 때                 |
| `:zap:` | ⚡        | feat  | 기능 구현 중                        |
| `:lipstick:` | 💄       | style | UI 디자인 (프론트엔드)                 |
| `:pencil2:` | ✏️       | fix   | 자잘한 코드 수정                      |
| `:bug:` | 🪲       | fix   | 버그 수정 (코드 수정)                  |
| `:art:` | 🎨       | refactor | 코드 수정 (기능 수정 없이 이쁘게 최적화)       |
| `:memo:` | 🗒️      | docs  | 문서 추가 or 수정 (README 수정할 때)     |
| `:fire:` | 🔥       | remove | 파일 삭제                          |
| `:heavy_plus_sign:` | ➕        | add   | build.gradle에 dependency 추가할 때 |
| `:heavy_minus_sign:` | ➖        | remove | build.gradle에 dependency 삭제할 때 |
| `:hammer:` | 🔨       | fix   | 설정 파일 수정                       |
| `:white_check_mark:` | ✅        | test  | 테스트 코드 작성                      |
| `:ambulance:` | 🚑       | !HOTFIX | 급하게 치명적인 버그를 고쳐야하는 경우          |
| `:truck:` | 🚚       | rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우   |
| `:bulb:` | 💡       | comment | 필요한 주석 추가 및 변경                 |
| `:wrench:` | 🔧       | edit  | .yml, .properties 파일 변경        |