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


menu = set()


'''
#엑셀로 출력
file_name = 's.xlsx'
reviews.to_excel(file_name)
'''