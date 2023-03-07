# Import libraries and packages for the project

from selenium import webdriver
from selenium.webdriver.common.keys import Keys

print("log:: Import libraries suggest!")

###
#    Section 1: login
###

# Step 4: save persional data to another file. And put data to that file.
open_file = open("data_file.txt")
line = open_file.readlines()
user_name = line[0]
passwd    = line[1]

# step 1: login to website
# open Chrome and log in {url} login site

driver = webdriver.Chrome()
url = 'https://www.your_link.etc/login'
driver.get(url)

# done open link
# can test run end then the link open

# Step 2: open web and get element on html text with tab.
# get tabname of id and pass

accout_name_field = driver.find_element_by_id("user_name") # example
accout_name_field.send_keys('your_accout_name')

passwd_field = driver.find_element_by_id("password") # example
passwd_field.send_keys("your_pass") # example

# find login button and do click
# can use copy xpath to get the xpath of element. put them to here
button_sign_in = driver.find_element_by_xpath("xpath")
button_sign_in.click() # this for login

time = 100 # ms, this time wait for web respond
sleep(time) 

###
#    Section 2: find data (grep or Regex or any)
###

