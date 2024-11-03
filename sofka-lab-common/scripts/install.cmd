@echo off
rem Muestra el directorio actual
cd

rem Cambia al directorio padre y muestra el nuevo directorio
cd ..
cd
java -version

rem Ejecuta Maven clean install
mvn clean install