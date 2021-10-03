# Users
This is an REST API application created over github users API.

## Setup
1. clone repository
1. build with maven `mvn clean install`
1. add Spring configuration to `intellij` or run app in terminal with command `mvn spring-boot:run` 

## Endpoints
- GET `/users/{login}`  
returns shorten user information for user with given login retrieved from github users api 

## Models
- UserWsm
    - *id*: id of the user
    - *login*: login name
    - *name*: user name
    - *type*: user type
    - *avatarUrl*: avatar url
    - *createdAt*: date of account creation
    - *calculations*: an result of calculation (6 / followers * (2 + public_repos)) where `followers` is a number of 
    user followers and `public_repos` is a number of user public repositories 
- ErrorWsm
    - *developerMessage*: verbose, plain language description of the problem
    - *userMessage*: message that can be passed along to end-users, if needed
    - *errorId*: short, random-looking identifier helping to find more details when troubleshooting the issue
    - *documentationUrl*: URL where more information is available
    
## Database
Application stores event logs of accessing data for users with specific login.
 
 | Login event  | | |
 | ------------- | ------------- | ------------- |
 | login_event_id  | BINARY(16)  | [PK]  |
 | login  | VARCHAR(250)  | NOT NULL [AK] |
 | request_count  | NUMERIC  | NOT NULL  |
