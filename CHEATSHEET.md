# Cheatsheet de Git - Tarea 1, parte A

En este documento se resumen los comandos principales de Git que se utilizarán durante el 
desarrollo del proyecto. La idea es tener una referencia rápida para consultar los comandos 
más importantes sin tener que buscarlos cada vez.

| Comando                                     | Descripción                                                                                 |
|---------------------------------------------|---------------------------------------------------------------------------------------------|
| `git config --global user.name "Nombre"`    | Configura el nombre del usuario que aparecerá en los commits de forma global.               |
| `git config --global user.email "email"`    | Configura el correo electrónico asociado a los commits de forma global.                     |
| `git init`                                  | Inicializa un nuevo repositorio Git en la carpeta actual.                                   |
| `git clone <url>`                           | Clona un repositorio remoto en el equipo local.                                             |
| `git status`                                | Muestra el estado del repositorio: archivos modificados, añadidos o pendientes de commit.   |
| `git add <archivo>`                         | Añade archivos al área de preparación para incluirlos en el próximo commit.                 |
| `git commit -m "mensaje"`                   | Guarda los cambios preparados en el historial del repositorio con un mensaje descriptivo.   |
| `git log`                                   | Muestra el historial de commits del repositorio.                                            |
| `git push`                                  | Envía los commits locales al repositorio remoto.                                            |
| `git pull`                                  | Descarga cambios del repositorio remoto y los integra en la rama actual.                    |
| `git fetch`                                 | Descarga información y cambios del repositorio remoto, pero no los integra automáticamente. |
| `git branch`                                | Lista las ramas del repositorio y muestra en cuál estás actualmente.                        |
| `git checkout <rama>` / `git switch <rama>` | Cambia de una rama a otra.                                                                  |
| `git merge <rama>`                          | Integra los cambios de otra rama dentro de la rama actual.                                  |                             |
