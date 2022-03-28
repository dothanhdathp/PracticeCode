:: Make new folder day by day

@echo off
IF #%1#==## (GOTO NORMAL) ELSE (GOTO SPECIAL)
:NORMAL
set "fname=%DATE:~-4%-%DATE:~-10,2%-%DATE:~-7,2%"
IF EXIST %fname% GOTO PRINT_MESSAGE
mkdir %fname%
cd %fname%
echo class JavaClass> JavaClass.java
echo {>> JavaClass.java
echo     public static void main(String[] args) {>> JavaClass.java
echo         System.out.println("Hello my friend!");>> JavaClass.java
echo     }>> JavaClass.java
echo }>> JavaClass.java
echo.>> JavaClass.cpp

GOTO EXIT

:SPECIAL
set "fname=%1"
IF EXIST %fname% GOTO PRINT_MESSAGE
mkdir %fname%
copy "./run.bat" "%fname%/run.bat"
cd %fname%
echo class %fname%> %fname%.java
echo {>> %fname%.java
echo     public static void main(String[] args) {>> %fname%.java
echo         System.out.println("Hello my friend!"); >> %fname%.java
echo     }>> %fname%.java
echo }>> %fname%.java
echo.>> %fname%.cpp
GOTO EXIT

:PRINT_MESSAGE
ECHO Folder exist!
:EXIT