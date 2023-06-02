# BeerMe

# P1 - Full Stack Beer Review Application 

## Introduction 

This is a full stack beer review application built using Spring Boot for the backend and Angular for the frontend. The application will utilize a PostgreSQL database to store beer, review, and user information.

## User Stories 

- **As a user,** I want to register an account so that I can personalize my beer review experience.
- **As a user,** I want to log in to my account so that I can access my profile and review history. 
- **As a user**, I want to browse through different beers only when I'm logged in. 
- **As a user**, I want to search for beers by name, brewery, or beer type so that I can find what I'm looking for. 
- **As a user,** I want to rate and review beers so that I can share my opinion about them. 
- **As a user,** I want to edit or delete my reviews so that I can make changes if my opinion changes. 
- **As a user**, I want to view ratings and reviews from other users so that I can get different perspectives about a beer.
- **As a user**, I want to mark my favorite beers so that I can easily find them later.

## MVP (Minimum Viable Product)

- User registration and login 
- Browsing and searching for beers 
- Rating and reviewing beers 
- Edit or delete reviews 
- Viewing ratings and reviews from other users 
- Marking favorite beers

## Stretch Goals 

- Implementing a recommendation system based on user's beer preferences and past reviews 
- Adding an admin role that can add, remove, or modify beers 
- Implementing user profiles with avatars and bio 
- Adding a feature for users to follow other users and see their reviews

## Tech Stacks 

- **Java**: The main programming language used for building the backend of the application.
- **Spring Boot**: Used for developing the RESTful APIs.
- **Angular**: Used for frontend development.
- **PostgreSQL**: Used as the database to store user, beer, and review data.
- **Maven or Gradle**: Used for managing project dependencies.
- **JUnit**: A testing framework for Java applications, used to ensure our code works as expected.
- **Spring Security**: A powerful and highly customizable authentication and access-control framework.
- **Hibernate**: A Java framework that simplifies the development of database interaction of Java applications.
- **BCrypt**: A Java library for hashing and checking passwords for security.
- **Git and GitHub**: Used for version control.

## Requirements 

- **Clean Codebase**: All code should be clean and well-documented. The repository should not include any unnecessary files or folders such as the `target/`, `.DS_Store`, etc. All files and directories should be appropriately named and organized.
- **Database Design**: The database should be designed following the principles of the 3rd Normal Form (3NF) to ensure data integrity and efficiency. An Entity Relationship Diagram (ERD) should be included in the documentation.
- **Secure**: All sensitive user data such as passwords must be securely hashed before storing it in the database. The application should not display any sensitive information in error messages.
- **Error Handling**: The application should handle potential errors gracefully and provide clear and helpful error messages to the users.
- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and other appropriate testing frameworks.
- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.
- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should we well-commented to allow for easy understanding and maintenance.
- **Scalable**: the design of the application should be scalable, allowing for easy addition of new features or modifications in the future.

- **Testing**: The application should have a high test coverage. Unit tests and integration tests should be implemented using JUnit, Mockito, and PowerMock.

- **Version Control**: The application should be developed using a version control system, preferably Git, with regular commits denoting progress.

- **Documentation**: The repository should include a README file with clear instructions on how to run the application. Code should be well-commented to allow for easy understanding and maintenance.

- **Scalable**: The design of the application should be scalable, allowing for easy addition of new features or modifications in the future.

