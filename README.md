https://youtu.be/M7WaYtx8d4U - Youtube link

https://docs.google.com/document/d/1ZtC2qMnmGyJSKS2-i6hiKWXU6VUwfHbWx7wSJUyP0Do/edit?usp=sharing - SRS


# E-commerce

## Introduction

Welcome to E-commerce! This project is a web application built with Spring Boot that serves as a platform for managing orders, products, and users. It offers functionalities for creating, updating, and deleting orders and products, as well as managing user accounts.

## Features

- **Order Management:** Create, update, and delete orders.
- **Product Management:** Manage products, including adding new products and updating existing ones.
- **User Account:** Users can register, log in, and manage their accounts.
- **Validation:** Input validation ensures data integrity and consistency.
- **Swagger Documentation:** Integrated Swagger for API documentation and testing.

## Getting Started

To get started with E-commerce:

### Prerequisites

- Java Development Kit (JDK)
- Maven
- Your favorite IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. Clone this repository to your local machine.
2. Open the project in your IDE.
3. Run `mvn clean install` to build the project and install dependencies.
4. Start the application by running the main class `MidtermSpring`.
Authentication and Authorization Mechanisms
Traditional Username and Password Login with JWT Token
For traditional username and password login, we have implemented a JWT (JSON Web Token) based authentication mechanism. Here's how it works:

Registration: Users can register with their email address, username, and password. Upon successful registration, the password is securely hashed and stored in the database.

Authentication: Users can log in using their username/email and password. Upon successful authentication, a JWT token is generated and returned to the client. This token is used for subsequent API requests to authenticate the user.

JWT Token: The JWT token contains encoded user information and is signed using a secret key known only to the server. It has an expiration time to enhance security.

Authorization: Protected endpoints in our application require the client to include the JWT token in the Authorization header of the HTTP request. The server validates the token and grants access to authorized resources.

Social Authentication with Google, GitHub, and Facebook
We also support social authentication using OAuth 2.0 with popular identity providers such as Google, GitHub, and Facebook. Here's how it works:

OAuth 2.0 Flow: Users can choose to sign in with their Google, GitHub, or Facebook accounts. This initiates the OAuth 2.0 flow, redirecting the user to the respective identity provider's authentication page.

User Consent: The user is prompted to consent to sharing their profile information with our application. Once consent is granted, the identity provider redirects the user back to our application with an authorization code.

Token Exchange: Our application exchanges the authorization code for an access token and optionally a refresh token with the identity provider's token endpoint.

User Creation/Authentication: If it's the user's first time signing in with the social provider, we create a new user account using the profile information provided by the identity provider. Otherwise, we authenticate the user and issue a JWT token as described in the previous section.

Authorization: The JWT token obtained from social authentication is used in the same way as for traditional login to authorize access to protected resources.
