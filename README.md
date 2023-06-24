# SIDE STAR
## 특정 음식의 맛집을 찾을 수 있는 앱
### 앱의 검색창에 음식 이름을 검색하면, 그 음식의 별점 순으로 음식의 점보가 나타난다.   
### 음식의 정보: 음식 이름, 음식의 별점, 음식의 리뷰수, 음식을 판매하는 가게의 이름, 가게의 별점, 음식 사진
#### 음식의 정보가 나오는 각각의 박스를 클릭하면 그 음식을 구매할 수 있는 화면으로 넘어간다.  


#### 실제 사용 코드: for_ppt_take_review.py, for_ppt_use_review.py, Final   
#### for_ppt_take_review.py: '요기요' 사이트에서 각 가게의 데이터를 크롤링한다. 코드 내부에 데이터를 추출하고 싶은 가게의 이름을 적는다. 크롤링한 데이터는 pickle로 저장된다.   
#### for_ppt_use_review.py: 저장되어 있는 pickle을 사용하여 데이터를 원하는 형태로 변형한다. 변형한 데이터를 엑셀 파일로 저장한다.   
#### Final: 안드로이드 스튜디오에서 사용하는 모든 코드가 들어있는 폴더
