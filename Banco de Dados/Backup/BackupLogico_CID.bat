@echo off
setlocal enabledelayedexpansion

:: Configurações do Backup
set "PG_DUMP_PATH=C:\Program Files\PostgreSQL\16\bin\pg_dump.exe"
set "BACKUP_DIR=C:\Users\vinic\Desktop\testeBackup"
set "PG_USER=postgres"
set "PG_PASSWORD=postgres"
set "PG_PORT=5432"
set "DATABASE_NAME=CID"
set "ZIP_PATH=C:\Program Files\WinRAR\WinRAR.exe"

:: Cria o diretório de backup se não existir
if not exist "%BACKUP_DIR%" (
    mkdir "%BACKUP_DIR%"
)

:: Obtém a data e hora para nomear o backup
for /f "tokens=2 delims==" %%I in ('wmic os get localdatetime /value ^| find "="') do set DATETIME=%%I
:: Formata a data como DD-MM-AAAA
set DATE=%DATETIME:~6,2%-%DATETIME:~4,2%-%DATETIME:~0,4%
:: Formata a hora como HH-MM-SS
set TIME=%DATETIME:~8,2%-%DATETIME:~10,2%-%DATETIME:~12,2%
:: Combina data e hora
set TIMESTAMP=%DATE%_%TIME%

:: Caminho do arquivo de backup
set "BACKUP_FILE=%BACKUP_DIR%\%DATABASE_NAME%_backup_%TIMESTAMP%.sql"

:: Define a senha para o pg_dump
set PGPASSWORD=%PG_PASSWORD%

:: Executa o pg_dump para criar o backup
echo [INFO] Realizando backup do banco de dados %DATABASE_NAME%...
"%PG_DUMP_PATH%" -U %PG_USER% -p %PG_PORT% -F c -b -f "%BACKUP_FILE%" %DATABASE_NAME% 2>> "%BACKUP_DIR%\backup_error.log"

if errorlevel 1 (
    echo [ERRO] Falha ao criar o backup. Verifique as configurações.
    echo ERRO AO REALIZAR BACKUP, VERIFIQUE >> "%BACKUP_DIR%\backup_error.log"
    pause
    exit /b 1
)

:: Compacta o arquivo de backup em .zip
echo [INFO] Compactando backup...
"%ZIP_PATH%" a -afzip "%BACKUP_FILE%.zip" "%BACKUP_FILE%" >nul

if errorlevel 1 (
    echo [ERRO] Falha ao compactar o backup. Verifique as configurações.
    echo ERRO AO REALIZAR BACKUP, VERIFIQUE >> "%BACKUP_DIR%\backup_error.log"
    pause
    exit /b 1
)

:: Remove o arquivo SQL após a compactação
del "%BACKUP_FILE%"

:: Remove backups mais antigos que 7 dias
forfiles /p "%BACKUP_DIR%" /m *.zip /d -7 /c "cmd /c del @path"

echo [SUCESSO] Backup concluído: %BACKUP_FILE%.zip
exit /b 0
