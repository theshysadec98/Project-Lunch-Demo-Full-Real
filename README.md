# Java spring boot project

A java spring boot project generated from charizard-generator

# Prerequisite

- Java 11
- Maven 3
- Docker

# Deployment on Localhost

## What will be deployed ?

- java application
- database
- flyway plugin (optional)
- standalone swagger-ui (optional)
- kafka plugin (optional)

## Linux

Execute `./local-deploy.sh` to deploy application to Docker Desktop

Notes: You might need to grant execution permission before run the deployment script: `chmod u+x ./local-deploy.sh`

## Windows

Execute `local-deploy.bat` under powershell

# Features

## openapi

### Nope

Nothing to add

### SpecOnly

- Generating openapi specification example CRUD Pokémon under `${project_dir}/openapi`
- Deploying a separating swagger-ui service to docker compose on `http://localhost`

### SpecImpl

- Including SpecOnly
- Generating Controller and Model from specification
- Implementation of CRUD Pokémon
