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

Authentication and Authorization Mechanisms
Traditional Username/Password Login with JWT Token
Registration: Users register with their email, username, and password. Passwords are securely hashed before storage.

Authentication: Users log in with their username/email and password. Upon successful login, a JWT token is generated and returned.

JWT Token: JSON Web Token containing user information, signed with a secret key. Used for subsequent API requests and has an expiration time.

Authorization: Protected endpoints require the JWT token in the Authorization header. The server validates the token to grant access.

Social Authentication with OAuth 2.0 Providers
OAuth 2.0 Flow: Users sign in with Google, GitHub, or Facebook. This redirects them to the provider's authentication page.

User Consent: Users consent to share profile information. The provider redirects them back with an authorization code.

Token Exchange: Our app exchanges the code for an access token and possibly a refresh token.

User Creation/Authentication: New users are created using profile info. Existing users are authenticated, and a JWT token is issued.

Authorization: The JWT token from social login is used for access to protected resources.

Token Exchange: Our application exchanges the authorization code for an access token and optionally a refresh token with the identity provider's token endpoint.

User Creation/Authentication: If it's the user's first time signing in with the social provider, we create a new user account using the profile information provided by the identity provider. Otherwise, we authenticate the user and issue a JWT token as described in the previous section.

Authorization: The JWT token obtained from social authentication is used in the same way as for traditional login to authorize access to protected resources.
