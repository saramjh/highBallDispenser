# 하이볼 디스펜서 (Highball Dispenser)

IoT 기반 자동 하이볼 제조 시스템 데모 프로그램입니다. 다양한 하이볼 레시피와 제조량을 선택하여 자동으로 제조하는 과정을 시각적으로 시뮬레이션합니다.

## 🥃 주요 기능

- **다양한 하이볼 레시피 제공**

  - 깊고 강렬한 풍미 (위스키 1:3)
  - 균형 잡힌 조화 (위스키 1:4)
  - 가볍고 산뜻한 청량감 (위스키 1:5)
  - 상큼하고 활력 있는 느낌 (레몬 추가)
  - 달콤하거나 스파이시한 변화 (토닉워터/진저에일)

- **맞춤형 제조량 선택**

  - 200ml부터 700ml까지 원하는 양 선택 가능
  - 선택한 제조량에 맞게 비율 자동 계산

- **시각적 제조 과정 표현**
  - 얼음, 위스키, 탄산수 등의 제조 과정 시각화
  - 정확한 재료 양과 비율 표시
  - 실시간 제조 상태 업데이트

## 📋 설치 및 실행 방법

### 요구사항

- Java 11 이상
- Gradle 7.0 이상

### 설치 방법

1. 레포지토리 복제

```bash
git clone https://github.com/saramjh/highBallDispenser.git
cd highBallDispenser
```

2. 프로젝트 빌드

```bash
./gradlew build
```

3. 애플리케이션 실행

```bash
./gradlew run
```

## 🖥️ 사용 방법

1. 애플리케이션을 실행하면 하이볼 제조 시스템 UI가 표시됩니다.
2. 드롭다운 메뉴에서 원하는 하이볼 스타일을 선택합니다.
3. 제조량(ml) 스피너를 사용하여 원하는 총량을 설정합니다(200ml~700ml).
4. 선택한 하이볼에 대한 설명 및 비율이 표시됩니다.
5. "하이볼 제조 시작" 버튼을 클릭하여 제조 과정을 시작합니다.
6. 제조 과정이 시뮬레이션되어 화면에 표시됩니다.

## 📁 프로젝트 구조

```
src/
└── main/
    └── java/
        └── com/
            └── highball/
                ├── HighballApplication.java      # 메인 애플리케이션 클래스
                ├── model/
                │   └── HighballRecipe.java       # 하이볼 레시피 모델
                ├── service/
                │   ├── HighballMaker.java        # 하이볼 제조 서비스
                │   └── RecipeDatabase.java       # 레시피 데이터베이스
                └── ui/
                    └── HighballGUI.java          # 사용자 인터페이스
```

## 🔍 테스트 케이스

본 애플리케이션은 다음과 같은 테스트 케이스를 통해 검증되었습니다:

1. **최소 제조량 테스트 (200ml)**

   - 위스키 1:3 비율 (깊고 강렬한 풍미)
   - 위스키 50ml, 탄산수 150ml

2. **최대 제조량 테스트 (700ml)**

   - 위스키 1:5 비율 (가볍고 산뜻한 청량감)
   - 위스키 117ml, 탄산수 583ml

3. **특수 워터 테스트 (500ml)**
   - 위스키 1:4 비율 (진저에일)
   - 위스키 100ml, 진저에일 400ml

## 🛠️ 개발 정보

- 개발 언어: Java
- 그래픽 인터페이스: Swing
- 빌드 도구: Gradle

## 📄 라이선스

MIT License

## 🔗 관련 링크

- GitHub: [https://github.com/saramjh/highBallDispenser.git](https://github.com/saramjh/highBallDispenser.git)
