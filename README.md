# BeArchive - Backend

> 한성대학교 멋쟁이사자처럼 13기 백엔드 레포지토리  
> 이미지 기반 브레인스토밍 챗봇 프로젝트

---

## 📌 프로젝트 소개

BeArchive는 GPT API를 활용하여 이미지 기반 추천 질문을 제공하는 브레인스토밍 챗봇 서비스입니다.  
이미지를 분석하여 주제를 유추하고, 그에 맞는 창의적인 질문을 생성합니다.

---

## 🛠 기술 스택

- **Language**: Java 17  
- **Framework**: Spring Boot 3.x  
- **Build Tool**: Gradle  
- **Database**: MySQL  
- **ORM**: Spring Data JPA  
- **Cloud**: AWS S3 (이미지 업로드)  
- **AI API**: OpenAI GPT API  

---

## 🚩Git 명령어

| 명령어                      | 설명                                    |
| --------------------------- | --------------------------------------- |
| `git clone <url>`           | 원격 저장소 복제                        |
| `git add .`                 | 전체 변경 파일 스테이징                 |
| `git add <파일명>`          | 특정 파일만 스테이징                    |
| `git commit -m "메시지"` ⭐ | 커밋 메시지와 함께 커밋                 |
| `git log`                   | 커밋 히스토리 확인                      |
| `git fetch`                 | 원격 저장소의 브랜치 상태를 가져오기     |
| `git branch`                | 현재 브랜치 목록 확인                   |
| `git checkout <이름>` ⭐    | 해당 브랜치로 이동                      |
| `git checkout -b <이름>`    | 새 브랜치 생성 + 이동                   |
| `git push` ⭐               | 현재 브랜치 내용을 원격 `main`에 푸시   |
| `git pull origin main` ⭐   | 원격 `main` 브랜치 내용 가져오기 (병합) |
| `git push --set-upstream origin <이름>` | 로컬 브랜치와 원격 브랜치를 연결|

---

## ✏️ 커밋 메시지 규칙

> 모든 커밋은 아래 규칙을 따릅니다.  
> Gitmoji 또는 Conventional Commit 스타일 사용 권장!

### 형식

### 커밋 타입 목록

| 타입     | 설명                          |
|----------|-------------------------------|
| feat     | 새로운 기능 추가              |
| fix      | 버그 수정                     |
| docs     | 문서 수정 (README 등)         |
| style    | 코드 스타일 (세미콜론 등)     |
| refactor | 코드 리팩토링 (기능 변화 없음) |
| test     | 테스트 코드 추가/수정         |
| chore    | 빌드 설정, 패키지 등 기타 작업 |

> ✨ Gitmoji를 쓸 경우:  
> 예) `:sparkles: feat: 로그인 구현`

---
## ⚡️ 깃 이모지 종류
| 아이콘 | 코드                      | 설명                  |
|-----|------------------------------|----------------------|
| 🎨 | `:art:`	                     | 코드의 구조/형태 개선 |
| ⚡️ | `:zap:`                       | 성능 개선 |
| 🔥 | `:fire:`                      | 코드/파일 삭제 |
| 🐛 | `:bug:`                       | 버그 수정 |
| 🚑 | `:ambulance:`                 | 긴급 수정 |
| ✨ | `:sparkles:`                  | 새 기능 |
| 📝 | `:memo:`                      | 문서 추가/수정 |
| 💄 | `:lipstick:`                  | UI/스타일 파일 추가/수정 |
| 🎉 | `:tada:`                      | 프로젝트 시작 |
| ✅ | `:white_check_mark:`          | 테스트 추가/수정 |
| 🔒 | `:lock:`                      | 보안 이슈 수정 |
| 🔖 | `:bookmark:`                  | 릴리즈/버전 태그 |
| 💚 | `:green_heart:`               | CI 빌드 수정 |
| 📌 | `:pushpin:`                   | 특정 버전 의존성 고정 |
| 👷 | `:construction_worker:`       | CI 빌드 시스템 추가/수정 |
| 📈 | `:chart_with_upwards_trend:`  | 분석, 추적 코드 추가/수정 |
| ♻️ | `:recycle:`                   | 코드 리팩토링 |
| ➕ | `:heavy_plus_sign:`           | 의존성 추가 |
| ➖ | `:heavy_minus_sign:`          | 의존성 제거 |
| 🔧 | `:wrench:`                    | 구성 파일 추가/수정 |
| 🔨 | `:hammer:`                    | 개발 스크립트 추가/수정 |
| 🌐 | `:globe_with_meridians:`      | 국제화/현지화 |
| 💩 | `:poop:`                      | 똥싼 코드 |
| ⏪ | `:rewind:`                    | 변경 내용 되돌리기 |
| 🔀 | `:twisted_rightwards_arrows:` | 브랜치 합병 |
| 📦 | `:package:`                   | 컴파일된 파일 추가/수정 |
| 👽 | `:alien:`                     | 외부 API 변화로 인한 수정 |
| 🚚 | `:truck:`                     | 리소스 이동, 이름 변경 |
| 📄 | `:page_facing_up:`            | 라이센스 추가/수정 |
| 💡 | `:bulb:`                      | 주석 추가/수정 |
| 🍻 | `:beers:`                     | 술 취해서 쓴 코드 |
| 🗃 | `:card_file_box:`              | 데이터베이스 관련 수정 |
| 🔊 | `:loud_sound:`                | 로그 추가/수정 |
| 🙈 | `:see_no_evil:`               | .gitignore 추가/수정 |

