# Codea

# 깃 컨벤션
## Commit message 7가지 규칙
1. 제목과 본문을 한 줄 띄어 구분
2. 제목은 50자 이내
3. 제목 첫 글자는 대문자
4. 제목 끝에 마침표 X
5. 제목은 명령문으로, 과거형 X
6. 본문의 각 행은 72자 이내 (줄바꿈 사용)
7. 본문은 어떻게 보다 무엇을, 왜에 대하여 설명

## Commit message 구조
```
<type>: <subject>
<body>
<footer> 
```

## Type
- feat : 새로운 기능 추가, 기존의 기능을 요구 사항에 맞추어 수정
- fix : 기능에 대한 버그 수정
- build : 빌드 관련 수정
- chore : 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore
- ci : CI 관련 설정 수정
- docs : 문서(주석) 수정
- style : 코드 스타일, 포맷팅에 대한 수정
- refactor : 기능의 변화가 아닌 코드 리팩터링 ex) 변수 이름 변경
- test : 테스트 코드 추가/수정
- release : 버전 릴리즈

## Subject
Type 함께 헤더 구성
ex) ` feat: Add App.Css` 
(App.css 파일 추가)

## Body
헤더로 표현이 가능하면 생략이 가능하며, 아닌 경우 자세한 내용을 함께 적어 본문 구성한다.

참조 링크
[커밋 메세지 컨벤션](https://beomseok95.tistory.com/328)




