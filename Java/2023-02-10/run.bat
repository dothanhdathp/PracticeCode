@echo off
forfiles /s /m *.java /c "cmd /c javac @file"
