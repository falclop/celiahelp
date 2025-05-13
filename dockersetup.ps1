# Ejecutar como administrador para instalar docker desktop en Windows

Write-Host "Descargando el instalador de Docker Desktop..."
Invoke-WebRequest -UseBasicParsing -Uri "https://desktop.docker.com/win/main/amd64/Docker Desktop Installer.exe" -OutFile "$env:TEMP\DockerInstaller.exe"

Write-Host "Instalando Docker Desktop..."
Start-Process -Wait -FilePath "$env:TEMP\DockerInstaller.exe" -ArgumentList "install", "--quiet"

Write-Host "Docker Desktop instalado. Se recomienda reiniciar el sistema."
