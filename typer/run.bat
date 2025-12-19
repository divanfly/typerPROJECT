@echo off
echo ========================================
echo   TyperApp Launcher
echo ========================================
echo.

@echo off
cd /d c:\dev\typerPROJECT\typer

REM Используем Maven из домашней папки
set "MAVEN_HOME=%USERPROFILE%\maven\apache-maven-3.9.6"
set "PATH=%MAVEN_HOME%\bin;%PATH%"

echo ========================================
echo   TyperApp Launcher
echo ========================================
echo.
echo Maven found. Running TyperApp...
echo.

mvn clean javafx:run

pause