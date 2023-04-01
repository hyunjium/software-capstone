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
taste_dict = {}
quantity_dict = {}
average_dict = {}
for each_menu in menuz:
    taste_dict[each_menu] = 'None'
    quantity_dict[each_menu] = 'None'
    average_dict[each_menu] = 'None'

#메뉴별 '맛','양','평균' 별점 추가
taste_df = pd.DataFrame(data={}, columns=taste_dict.keys())
quantity_df = pd.DataFrame(data={}, columns=quantity_dict.keys())
average_df = pd.DataFrame(data={}, columns=average_dict.keys())
for i in range(len(reviews)):
    order_dict_taste = taste_dict.copy()
    order_dict_quantity = quantity_dict.copy()
    order_dict_average = average_dict.copy()
    for order in reviews.loc[i]['order']:
        order_dict_taste[order] = reviews.loc[i]['taste']
        order_dict_quantity[order] = reviews.loc[i]['quantity']
        order_dict_average[order] = reviews.loc[i]['average']
    taste_df = taste_df.append(order_dict_taste, ignore_index=True)
    quantity_df = quantity_df.append(order_dict_quantity, ignore_index=True)
    average_df = average_df.append(order_dict_average, ignore_index=True)


#엑셀로 출력
writer = pd.ExcelWriter('강릉동화가든짬뽕순두부_정리.xlsx')
taste_df.to_excel(writer, sheet_name="맛")
quantity_df.to_excel(writer, sheet_name="양")
average_df.to_excel(writer, sheet_name="평균")
writer.save()