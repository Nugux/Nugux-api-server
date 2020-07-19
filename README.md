# Nugux-api-server


## Description
- 관광지 API와 TMap API를 활용한 관광지 정보 및 혼잡도 데이터 수집 및 API 제공
    1. 위도, 경도 Boundary내의 특별시,도 / 시군구 별 혼잡도 정보 제공 API
    2. 위도, 경도 Boundary내의 관광지 혼잡도 및 상세 정보 제공 API
    3. 자동으로 데이터 수집

## How to build / How to run
1. Gradle build를 통하여 complie & build
2. TouristSpotRepositoryTest, CongestionCalculateServiceTest의 Junit 테스트를 실행하여 관광지 및 혼잡도 데이터 생성
3. Project Root에 key.property 생성
3.1. ```ProjectKey:<TMap API Key>``` 와 같이, 발급받은 T-map API Key 입력
4. Spring boot main application 구동
