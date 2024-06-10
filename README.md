# Leaderboard Application

Welcome to the Leaderboard Application! This application provides APIs to manage a leaderboard for a coding contest.

## Description

The Leaderboard Application allows users to register, update scores, and delete users from the contest. It provides endpoints for CRUD operations on user registrations and ensures proper validation and error handling.

## Setup Instructions

1. **Clone the Repository**: 
git clone https://github.com/shubhamjainjnsb/Leaderboard
2. **Build the Project**: 
mvn clean install
3. **Run the Application**: 
java -jar target/leaderboard-application.jar



## Endpoints

- **GET /users**: Retrieve a list of all registered users.
- **GET /users/{userId}**: Retrieve the details of a specific user.
- **POST /users**: Register a new user to the contest.
- **PUT /users/{userId}**: Update the score of a specific user.
- **DELETE /users/{userId}**: Deregister a specific user from the contest.

## Error Handling

- **400 Bad Request**: Invalid input or missing fields.
- **404 Not Found**: User not found.
- **500 Internal Server Error**: Unexpected server error.

## Postman Collection

Explore the [Postman Collection](https://github.com/shubhamjainjnsb/Leaderboard/blob/main/Leaderboard%20Public%20Postman%20Collection.postman_collection.json) for detailed documentation and examples of API requests.

## Contributors

- Shubham Jain <jains5833@gmail.com>




