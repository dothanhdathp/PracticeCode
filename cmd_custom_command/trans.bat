@echo off
SET "base_url=https://translate.googleapis.com/translate_a/single?client=gtx^&sl=en^&tl=vi^&dt=t^&q="
echo %base_url%
SET "ua=Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox\109.0"
echo %*% | sed -e "s/\ /+/g" > temp
for /f "delims=" %%i in ('cat temp') do set "output=%%i"
rm -rf temp
echo %output%
SET "full_url=%base_url%%qry%"
curl -sA %ua% %full_url%
echo %full_url%
:: SET response=%undefined%