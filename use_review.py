import pickle
import pandas as pd

#pickle list로 가져와서 dataframe으로 변환
with open("강릉동화가든짬뽕순두부.pickle","rb") as fr:
    list = pickle.load(fr)
reviews = pd.DataFrame(list)

#order 중 NaN과 '' 행 삭제, index reset
reviews = reviews[reviews['order'].notna()]
for row_num in range(len(reviews)):
    if reviews.loc[row_num]['order'][0] == '':
        reviews = reviews.drop(row_num)
reviews = reviews.reset_index(drop=True)        

#set에 메뉴들 넣기
menuz = set()
for review in reviews['order']:
    for menu in review:
        menuz.add(menu)

#set의 메뉴들을 key로 가지고 있는 'None' dict 만들기
menu_dict = {}
for each_menu in menuz:
    menu_dict[each_menu] = 'None'

#메뉴별 '맛' 별점 추가
df = pd.DataFrame(data={}, columns=menu_dict.keys())
for i in range(len(reviews)):
    order_dict = menu_dict.copy()
    for order in reviews.loc[i]['order']:
        order_dict[order] = reviews.loc[i]['taste']
    df = df.append(order_dict, ignore_index=True)



#엑셀로 출력
file_name = '강릉동화가든짬뽕순두부_정리.xlsx'
df.to_excel(file_name)