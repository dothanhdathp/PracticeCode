# Import libraries and packages for the project
import time
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys


print("log:: Import libraries suggest!")

driver = webdriver.Chrome()
url = 'https://hris.humaxdigital.com/login'
driver.get(url)

accoutName = driver.find_element(By.ID, "login_username")
accoutName.send_keys('dtdat')
passwd = driver.find_element(By.ID, "login_password") # example
passwd.send_keys("Rakuhouha10") # example

# button_sign_in = driver.find_element(By.CSS_SELECTOR, ".btn-primary")
# button_sign_in.click()
button_sign_in = WebDriverWait(driver, 10).until(
	lambda bt: bt.find_element(By.CSS_SELECTOR, ".btn-primary")
)
button_sign_in.click()

button_lw = WebDriverWait(driver, 10).until(
 	lambda bt: bt.find_element(By.XPATH, "/html/body/div[1]/div[1]/div[2]/div[1]/div/div/div/div[1]/div[3]/button")
) 
# # button_lw = driver.find_element(By.CSS_SELECTOR, ".outline-color.sc-keVrkP.gOsRKZ.btn-lg.sc-bwzfXH.dvWKXF")
button_lw.click()

button_create_log = WebDriverWait(driver, 10).until(
	lambda bt: bt.find_element(By.CLASS_NAME , "drag-container")
)
button_create_log.click()

mtextView_descript = WebDriverWait(driver, 10).until(
	lambda bt: bt.find_element(By.XPATH, "/html/body/div[6]/div/div[2]/div/div[2]/div/div/div[2]/div[3]/textarea")
)
mtextView_descript.send_keys("Tad_Zeilar")


end_time = WebDriverWait(driver, 20).until(
# 	lambda bt: bt.find_element(By.XPATH, "/html/body/div[6]/div/div[2]/div/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/div/span")
    lambda bt: bt.find_element(By.XPATH, "/html/body/div[6]/div/div[2]/div/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[1]")
)
end_time.click()







try:
	x = WebDriverWait(driver, 10).until(
		lambda bt: bt.find_element(By.CLASS_NAME, "css-2613qy-menu")
	)
	print("x exist!!")
except:
	print("time out!!!")
# start_time = WebDriverWait(driver, 10).until(
# 	lambda bt: bt.find_element(By.XPATH, "/html/body/div[7]/div/div[2]/div/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/div")
# )
# start_time.click()



time.sleep(2000)
driver.close()