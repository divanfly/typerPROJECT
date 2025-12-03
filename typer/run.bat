@echo off
cd /d c:\dev\typerPROJECT\typer
set PATH=%USERPROFILE%\maven\apache-maven-3.9.6\bin;%PATH%
mvn javafx:run
pause