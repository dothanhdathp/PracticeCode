@echo off
goto remove
:remove
for /F "tokens=*" %%G in ('dir *.exe /b') do (
        IF EXIST %%G rm %%G
)
GOTO build

:build
forfiles /m *.rs /c "cmd /c rustc @fname.rs --color always 2>&1 | more"
for /F "tokens=*" %%G in ('dir *.exe /b') do (
        ECHO.
        %%G
        ECHO.
)