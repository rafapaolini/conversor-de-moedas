@echo off
REM Define UTF-8 no console
chcp 65001 >nul
title Conversor de Moedas - Programa ONE

REM Pasta raiz do projeto (com aspas para caminhos com espaços)
set "ROOT_DIR=%~dp0"

REM Pasta onde os .class compilados vão ficar
set "OUT_DIR=%ROOT_DIR%bin"

REM Cria a pasta bin se não existir
if not exist "%OUT_DIR%" mkdir "%OUT_DIR%"

REM Compila o Main.java
javac -cp "%ROOT_DIR%gson-2.10.1.jar" -d "%OUT_DIR%" "%ROOT_DIR%src\main\java\br\com\seuusuario\Main.java"

REM Executa a classe principal com UTF-8
java -Dfile.encoding=UTF-8 -cp "%OUT_DIR%;%ROOT_DIR%gson-2.10.1.jar" br.com.seuusuario.Main

echo.
echo Programa finalizado. Pressione qualquer tecla para sair...
pause >nul

