@echo off
goto remove
:remove
for /F "tokens=*" %%G in ('dir *.exe /b') do (
	IF EXIST %%G rm %%G
)
GOTO build

:build
forfiles /m *.cpp /c "cmd /c g++ -std=c++17 -Wall -o @fname.exe @fname.cpp"
for /F "tokens=*" %%G in ('dir *.exe /b') do (
	ECHO [ START : %%G ]
	%%G
	ECHO.
	ECHO [ END   : %%G ]
)