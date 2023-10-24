@echo off

for /F "tokens=*" %%G in ('dir *.java /b') do (
	javac %%G
)

java %1