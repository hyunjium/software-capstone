from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time
from bs4 import BeautifulSoup
import pandas as pd
import openpyxl

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
search_word.send_keys("맥도날드")
search_word.send_keys(Keys.ENTER)
time.sleep(2)
click_mac = browser.find_element(By.CSS_SELECTOR, "#content > div > div:nth-child(5) > div > div > div > div")
click_mac.click()
time.sleep(2)

soup = BeautifulSoup(browser.page_source, "lxml")

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

#별점 카테고리 클릭
star_sec = browser.find_element(By.CSS_SELECTOR, "#content > div.restaurant-detail.row.ng-scope > div.col-sm-8 > ul > li:nth-child(2) > a")
star_sec.click()
time.sleep(2)

#더보기 클릭
look_more = browser.find_element(By.CSS_SELECTOR, "#review > li.list-group-item.btn-more > a")
while True:
    try:
        look_more.click()
        time.sleep(0.5)
    except:
        break

#리뷰 메뉴들 list 안에 가져오기
time.sleep(2)
star_menu = browser.find_elements(By.CSS_SELECTOR,'.order-items.default.ng-binding')
time.sleep(2)
all=[]
for name in star_menu:
    star_list=[]
    star_list.append(name.text)
    all.append(star_list)

#전체 별점 가져오기
time.sleep(2)
star_num = browser.find_elements(By.CSS_SELECTOR,'.points.ng-binding')
time.sleep(2)
rate=[]
for star_rate in star_num:
    rate.append(star_rate.text)

#맛 별점만 선별
i=0
for num in range(3,len(rate),3):
    all[i].append(rate[num])
    i+=1

for if_menu in menu_dict:
    for if_star_menu in all:
        if if_menu in if_star_menu[0]:
            menu_dict[if_menu].append(if_star_menu[1])
        else:
            menu_dict[if_menu].append('0')

#엑셀로 출력
time.sleep(2)
df = pd.DataFrame(menu_dict)
file_name = 'test_sheet.xlsx'
df.to_excel(file_name)

#,로 자르고 /있으면 앞메뉴가져오기.
# /없으면 전체 가져오기.
# 리뷰 많은 중국집이나 패턴 간단한것 4개 