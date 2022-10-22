@echo off
rustc %* --color always 2>&1 | more
