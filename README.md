![GitHub Release](https://img.shields.io/github/v/release/weemeal/weemeal-backend-spring)


# WeeMeal Spring Boot Backend Projekt

Willkommen zu dem WeeMeal Spring Boot Backend Projekt! 🚀

## Requirements

- tbd

## Installation

tbd

## Docker Setup
The application uses Docker Compose to start the PostgreSQL database. The docker-compose.yml file is located in the root directory of the project.
Docker Images can be found on [Docker Hub](https://hub.docker.com/repository/docker/darthkali/weemeal-backend-spring/general). 
1. Start Docker Compose

  Use the following command to start the Docker containers in the background:  
  
  ```bash
  docker-compose up -d
  ```
  This starts the following services:
  - `PostgreSQL Database`: Runs on port 5432.
  For more details about the services, check the docker-compose.yml file.

2. Start the Backend
  In a separate terminal, you can start the frontend:
  
  ```bash
  npm start
  ```
  This will start the React frontend on http://localhost:3000.

## Forking and Docker Hub Integration
If you want to fork this project, you need to modify the publish.yml and release.yml workflows:

1. Update the GitHub Actions workflows:
  In the .github/workflows/publish.yml and .github/workflows/release.yml files, make sure to update the following:
  - `IMAGE_NAME`: The name of your Docker image.
  - docker hub path: The path to your Docker Hub repository.
  
2. Set up GitHub Secrets:
  In your forked repository, you will need to add the following GitHub Secrets:
  - `DOCKER_HUB_USER`: Your Docker Hub username.
  - `DOCKER_HUB_PASS`: Your Docker Hub password.
  - `RELEASE_TOKEN`: A GitHub token that can be created in your GitHub profile under "Settings" > "Developer Settings" > "Personal Access Tokens".
  These secrets are required for automatically pushing Docker images to your Docker Hub repository and for creating new releases.
