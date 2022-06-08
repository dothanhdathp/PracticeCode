:: Make new folder day by day

@echo off
IF #%1#==## (GOTO NORMAL) ELSE (GOTO SPECIAL)

:NORMAL
set "fname=%DATE:~-4%-%DATE:~-10,2%-%DATE:~-7,2%"
IF EXIST %fname% GOTO PRINT_MESSAGE
mkdir %fname%
copy "./run.bat" "%fname%/run.bat"
cd %fname%
echo #include ^<cstdlib^>> main.cpp
echo #include ^<iostream^>>> main.cpp
echo #include ^<algorithm^>>> main.cpp
echo //#include "stdio.h">> main.cpp
echo.>> main.cpp
echo using namespace std; >> main.cpp
echo.>> main.cpp
echo int main()>> main.cpp
echo {>> main.cpp
echo 	return EXIT_SUCCESS; >> main.cpp
echo }>> main.cpp

GOTO EXIT

:SPECIAL
set "fname=%1"
IF EXIST %fname% GOTO PRINT_MESSAGE
mkdir %fname%
copy "./run.bat" "%fname%/run.bat"
cd %fname%
echo #include ^<cstdlib^>> main.cpp
echo #include ^<iostream^>>> main.cpp
echo #include ^<algorithm^>>> main.cpp
echo //#include "stdio.h">> main.cpp
echo.>> main.cpp
echo using namespace std; >> main.cpp
echo.>> main.cpp
echo int main()>> main.cpp
echo {>> main.cpp
echo 	return EXIT_SUCCESS; >> main.cpp
echo }>> main.cpp

GOTO EXIT

:PRINT_MESSAGE
ECHO Folder exist!
:EXIT