from selenium import webdriver
from selenium.webdriver.common.keys import Keys

driver = webdriver.Chrome("./chromedriver")

driver.get("http://localhost:8080/login")

usernameField = driver.find_element_by_css_selector("#username")
usernameField.send_keys("Frozr")

passwordField = driver.find_element_by_css_selector("#password")
passwordField.send_keys("nastya2016")

logInBtn = driver.find_element_by_css_selector("body > div > div > form > div:nth-child(2) > button")

# driver.execute_script("arguments[0].click();", logInBtn);
logInBtn.click()
