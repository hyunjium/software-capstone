from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from bs4 import BeautifulSoup
import pandas as pd
import openpyxl
import re

browser = webdriver.Chrome()
time.sleep(2)
browser.get("https://www.yogiyo.co.kr/mobile/#/")

#주소 경희대로 변경
search_box = browser.find_element(By.NAME,"address_input")
browser.find_element(By.NAME,"address_input").clear()
search_box.send_keys("경희대학교 국제캠퍼스")
search_box.send_keys(Keys.ENTER)
time.sleep(2)
school_add = browser.find_element(By.CSS_SELECTOR, "#search > div > form > ul > li:nth-child(3) > a")
school_add.click()
time.sleep(2)

#맥도날드 검색
search = browser.find_element(By.CSS_SELECTOR, "#category > ul > li.hidden-xs.menu-search > a")
search.click()
search_word = browser.find_element(By.CSS_SELECTOR, "#category > ul > li.main-search > form > div > input")
search_word.send_keys("강릉동화가든짬뽕순두부")
search_word.send_keys(Keys.ENTER)
time.sleep(2)
click_mac = browser.find_element(By.CSS_SELECTOR, "#content > div > div:nth-child(5) > div > div > div > div")
click_mac.click()
time.sleep(2)

soup = BeautifulSoup(browser.page_source, "lxml")

'''
#맥도날드 메뉴 전부 가져와서 list에 넣기
menuz = soup.find_all("div", attrs={"class":"menu-name ng-binding"})
menu_list = []
for menu in menuz:
    menu_name = menu.get_text()
    if menu_name not in menu_list:
        menu_list.append(menu_name)

#메뉴 dict로 만들기
menu_dict = {}
for menu_z in menu_list:
    menu_dict[menu_z] = []
'''
#리뷰 카테고리 클릭
star_sec = browser.find_element(By.CSS_SELECTOR, "#content > div.restaurant-detail.row.ng-scope > div.col-sm-8 > ul > li:nth-child(2) > a")
star_sec.click()
time.sleep(2)

#더보기 클릭
look_more = browser.find_element(By.CSS_SELECTOR, "#review > li.list-group-item.btn-more > a")
while True:
    try:
        look_more.click()
        time.sleep(1)
    except:
        break

#리뷰 메뉴들 가져오기 ,로 잘라서 list 안에 넣기
time.sleep(2)
star_menu = browser.find_elements(By.CSS_SELECTOR,'.order-items.default.ng-binding')
#time.sleep(2)
print("리뷰개수=",len(star_menu))

#전체 별점 가져오기
time.sleep(2)
star_num = browser.find_elements(By.CSS_SELECTOR,'.points.ng-binding')
#time.sleep(2)
rate=[]
for star_rate in star_num:
    if '' == star_rate.text:
        rate.append('None')
    else:
        rate.append(float(star_rate.text))

#리뷰들 ,로 잘라서 list 안에 넣기
menu_list=[]
menu_dict={}
i=3
for name in star_menu:
    menu_one_person=[]
    sp_1st = name.text.split(',')
    #/있을 경우 앞의 메뉴만, 없을 경우 전체 메뉴 list에 넣기
    for one_menu in sp_1st:
        if '/' in one_menu:
            front_menu=one_menu[:one_menu.find('/')]
            menu_one_person.append(front_menu)
            #메뉴 dict에 추가 및 별점 추가
            if front_menu not in menu_dict:
                menu_dict[front_menu]=[rate[i]]
            else:
                menu_dict[front_menu].append(rate[i])
        else:
            menu_one_person.append(one_menu)
            if one_menu not in menu_dict:
                menu_dict[one_menu]=[rate[i]]
            else:
                menu_dict[one_menu].append(rate[i])
    menu_list.append(menu_one_person)
    i+=3
print("메뉴개수=",len(menu_dict))

#별점 없는 칸 'None'으로 채우기
for number in menu_dict:
    left = int(len(star_menu)/2) - len(menu_dict[number])
    for zero in range(0,left):
        menu_dict[number].append('None')

#print(menu_list)
#print(menu_dict)


'''
#맛 별점만 선별해서 각 리뷰에 맞춤 
i=0
for num in range(3,len(rate),3):
    menu_list[i].append(rate[num])
    i+=1



#menu_dict의 key메뉴와 menu_list의 메뉴가 동일할 시 별점
for each_review in menu_list:
    for each_review_menu in each_review:
        for each_menu_in_dict in menu_dict:
            if each_review_menu == each_menu_in_dict:
                menu_dict[each_menu_in_dict].append(each_review[len(each_review)-1])
            #else:
               # menu_dict[each_menu_in_dict].append('0')





for if_menu in menu_dict:
    for if_star_menu in all:
        if if_menu in if_star_menu[0]:
            menu_dict[if_menu].append(if_star_menu[1])
        else:
            menu_dict[if_menu].append('0')
            '''


#엑셀로 출력
#time.sleep(2)
df = pd.DataFrame(menu_dict)
file_name = '강릉동화가든짬뽕순두부_맛.xlsx'
df.to_excel(file_name)