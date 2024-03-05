

# MyPost API
This is a simple API for a blog post. It allows you to create, read, update and delete a post. It also allows you to create, read, update and delete a comment on a post.
You can add friends and see their posts and comments and give likes to posts.

# GET Endpoints for UserController

## Get all users
```http
GET /api/users/allUsers
```

* **Description:** 

Get all users from database with optional list of friends and posts and ability to paginate data.

* **Query Parameters:**

| Parameter     | Type | Description                                       | Default |
|:--------------| :--- |:--------------------------------------------------|:--------|
| `postPage`    | `number` | number of page for posts data                     | 0       |
| `friendsPage` | `number` | number of page for friends data                   | 0       |
| `friends`     | `boolean` | Include friends  with status accepted in response | FALSE   |
| `posts`       | `boolean` | Include posts in response                         | FALSE   |

* **Request Example:**

    * no request Body 

* **Response Example:**

```json
{
  "userList": [
    {
      "id": 1,
      "firstName": "string",
      "lastName": "string",
      "email": "string",
      "createdAt": "2024-02-29T21:20:59.000+00:00",
      "friends": [
        {
          "id": 11,
          "firstName": "fr",
          "lastName": "fr",
          "email": "f@f.com",
          "createdAt": "2024-03-01T17:26:27.000+00:00"
        }
      ],
      "posts": [
        {
          "postId": 1,
          "content": "This is a post from user 1",
          "createdAt": "2024-03-01T16:40:33.000+00:00",
          "likesCount": 0
        }
      ]
    }
    ]
}
```
_________________________________

## Get currently logged in user
```http
GET /api/users/currentlyLoggedInUser
```

* **Description:**

Get user based by token from database with optional list of friends and posts.

* **Query Parameters:**

| Parameter     | Type     | Description                     | Default |
|:--------------|:---------|:--------------------------------|:--------|
| `id`          | `number` | id of user to get (required)    | 0       |
| `postPage`    | `number` | number of page for posts data   | 0       |
| `friendsPage` | `number` | number of page for friends data | 0       |
| `friends`     | `number` |Include friends  with status accepted in response     | 0       |
| `posts`       | `number` | Include posts in response       | 0       |

* **Request Example:**

  * no request Body
* **Response Examples**

```json
{
  "id": 12,
  "firstName": "fr",
  "lastName": "fr",
  "email": "f2@f.com",
  "createdAt": "2024-03-01T17:26:39.000+00:00",
  "friends": [
    {
      "id": 11,
      "firstName": "fr",
      "lastName": "fr",
      "email": "f@f.com",
      "createdAt": "2024-03-01T17:26:27.000+00:00"
    }
  ],
  "posts": [
    {
      "postId": 1,
      "content": "This is a post from user 1",
      "createdAt": "2024-03-01T16:40:33.000+00:00",
      "likesCount": 0
    }
  ]
}
```


## Get user by id
```http
GET /api/users/userById?id=12&friends=1
```

* **Description:**

Get user by id from database with optional list of friends and posts.

* **Query Parameters:**

| Parameter     | Type     | Description                     | Default |
|:--------------|:---------|:--------------------------------|:--------|
| `id`          | `number` | id of user to get (required)    | 0       |
| `postPage`    | `number` | number of page for posts data   | 0       |
| `friendsPage` | `number` | number of page for friends data | 0       |
| `friends`     | `number` |Include friends  with status accepted in response     | 0       |
| `posts`       | `number` | Include posts in response       | 0       |

* **Request Example:**

    * no request Body
* **Response Examples**

```json
{
  "id": 12,
  "firstName": "fr",
  "lastName": "fr",
  "email": "f2@f.com",
  "createdAt": "2024-03-01T17:26:39.000+00:00",
  "friends": [
    {
      "id": 11,
      "firstName": "fr",
      "lastName": "fr",
      "email": "f@f.com",
      "createdAt": "2024-03-01T17:26:27.000+00:00"
    }
  ],
  "posts": [
    {
      "postId": 1,
      "content": "This is a post from user 1",
      "createdAt": "2024-03-01T16:40:33.000+00:00",
      "likesCount": 0
    }
  ]
}
```

* **Error Messages**

1. User not found

```json
{
  "apiPath": "uri=/api/users/userById",
  "errorCode": "NOT_FOUND",
  "errorMessage": " not found with the given input data User not found with id: 117",
  "errorTime": "2024-03-05T12:50:09.0868538"
}
```


_________________________________

## Get user by email
```http
GET http://localhost:8080/api/users/userByEmail
```

* **Description:**

Get user by email from database with optional list of friends and posts.

* **Query Parameters:**

| Parameter     | Type | Description                     |Default|
|:--------------| :--- |:--------------------------------|:------|
| `email`       | `string` | email of user to get (required) |""|
| `postPage`    | `number` | number of page for posts data   |0|
| `friendsPage` | `number` | number of page for friends data |0|
| `friends`     | `boolean` | Include friends  with status accepted in response     |FALSE|
| `posts`       | `boolean` | Include posts in response       |FALSE |


* **Request Example:**

    * no request Body

* **Response Example:**

```json
{
  "id": 12,
  "firstName": "fr",
  "lastName": "fr",
  "email": "f2@f.com",
  "createdAt": "2024-03-01T17:26:39.000+00:00",
  "friends": [
    {
      "id": 11,
      "firstName": "fr",
      "lastName": "fr",
      "email": "f@f.com",
      "createdAt": "2024-03-01T17:26:27.000+00:00"
    }
  ],
  "posts": [
    {
      "postId": 1,
      "content": "This is a post from user 1",
      "createdAt": "2024-03-01T16:40:33.000+00:00",
      "likesCount": 0
    }
  ]
}
```

* **Error Messages**

1. User not found

```json
{
  "apiPath": "uri=/api/users/userByEmail",
  "errorCode": "NOT_FOUND",
  "errorMessage": " not found with the given input data User not found with email: a@adwdw.com",
  "errorTime": "2024-03-05T12:52:10.2193341"
}
```

_________________________________

## Get user by name
```http
GET /api/users/userByFirstName
```

* **Description:**

Get user by first name from database with optional list of friends and posts.

* **Query Parameters:**

| Parameter     | Type | Description                     |Default|
|:--------------| :--- |:--------------------------------|:------|
| `firstName`   | `string` | first name of user to get (required) |""|
| `postPage`    | `number` | number of page for posts data   |0|
| `friendsPage` | `number` | number of page for friends data |0|
| `friends`     | `boolean` | Include friends  with status accepted in response     |FALSE|
| `posts`       | `boolean` | Include posts in response       |FALSE |

* **Request Example:**

    * no request Body

* **Response Example:**

```json
{
  "id": 12,
  "firstName": "fr",
  "lastName": "fr",
  "email": "f2@f.com",
  "createdAt": "2024-03-01T17:26:39.000+00:00",
  "friends": [
    {
      "id": 11,
      "firstName": "fr",
      "lastName": "fr",
      "email": "f@f.com",
      "createdAt": "2024-03-01T17:26:27.000+00:00"
    }
  ],
  "posts": [
    {
      "postId": 1,
      "content": "This is a post from user 1",
      "createdAt": "2024-03-01T16:40:33.000+00:00",
      "likesCount": 0
    }
  ]
}
```

_________________________________

## Get user by lastname 

```http
GET /api/users/userByLastName
```

* **Description:**

Get user by last name from database with optional list of friends and posts.

* **Query Parameters:**

| Parameter     | Type | Description                     |Default|
|:--------------| :--- |:--------------------------------|:------|
| `lastName`    | `string` | last name of user to get (required) |""|
| `postPage`    | `number` | number of page for posts data   |0|
| `friendsPage` | `number` | number of page for friends data |0|
| `friends`     | `boolean` | Include friends  with status accepted in response    |FALSE|
| `posts`       | `boolean` | Include posts in response       |FALSE |

* **Request Example:**

    * no request Body

* **Response Example:**

```json
{
  "userList": [
    {
      "id": 1,
      "firstName": "string",
      "lastName": "string",
      "email": "string",
      "createdAt": "2024-02-29T21:20:59.000+00:00",
      "friends": [
        {
          "id": 11,
          "firstName": "fr",
          "lastName": "fr",
          "email": "f@f.com",
          "createdAt": "2024-03-01T17:26:27.000+00:00"
        }
      ],
      "posts": [
        {
          "postId": 1,
          "content": "This is a post from user 1",
          "createdAt": "2024-03-01T16:40:33.000+00:00",
          "likesCount": 0
        }
      ]
    }
    ]
}
```

_________________________________

# Endpoints for UserFriendShipController

## Create friendship endpoint 

```http
POST /api/friendship/createFriendShip/{id}
```

* **Description:**

Create a friendship between two users. Endpoint used to send a friend request to another user.
Necessary condition to create a friendship with status pending waiting for approval is that user1 or user2 must be logged and user1 and user2 must be different users.
In request body we pass id of user with whom we want to create a friendship. After creating a friendship, user2 will receive a friend request from user1 and user1 is waiting for approval which changes statis from PENDING to ACCEPTED.

* **Query Parameters:**

| Parameter     | Type | Description                     | Default  |
|:--------------| :--- |:--------------------------------|:---------|
| `id`          | `number` | id of user to create a friendship with (required) | required |

* **Request Example:**

    * no request Body

* **Response : **user added to friends**"

* **Error Messages**

1. User not found-> error indicating that we try to invite user which doesnt exist or we try to do it woth invalid token of no existing user id

```json
{
  "apiPath": "uri=/api/friendship/createFriendShip/120",
  "errorCode": "NOT_FOUND",
  "errorMessage": " not found with the given input data User not found with id: 120",
  "errorTime": "2024-03-05T12:55:24.8462408"
}
```

2. User already in friends -> error indicating that we try to invite user which is already in friends list

```json
{
  "apiPath": "uri=/api/friendship/createFriendShip/12",
  "errorCode": "METHOD_NOT_ALLOWED",
  "errorMessage": "It is not allowed to create friendship because it already exists Friendship already exists",
  "errorTime": "2024-03-05T12:59:39.89665"
}
```
3. friendShip with ourself -> error indicating that we try to invite user with our own id

```json
{
  "apiPath": "uri=/api/friendship/createFriendShip/11",
  "errorCode": "METHOD_NOT_ALLOWED",
  "errorMessage": "You cannot be friend with yourself",
  "errorTime": "2024-03-05T13:00:14.1806627"
}
```



_________________________________

## change status of friendship to accepted (accept friend request)

```http
PUT /api/friendship/acceptFriendShip/{id}
```

* **Query Parameters:**

| Parameter     | Type | Description                     | Default  |
|:--------------| :--- |:--------------------------------|:---------|
| `id`          | `number` | id of user to accept a friendship with (required) | required |




* **Description:**

Change status of friendship to accepted. Endpoint used to accept a friend request from another user. After accepting a friend request, user1 and user2 are friends and status of friendship is changed from PENDING to ACCEPTED.
Important condition is that one of the users must be logged and user1 and user2 must be different users. We pass in body request id of user with whom we want to accept a friendship.

* **Request Example:**

  * no request Body


* **Response : **friendship accepted**"


* **Error Messages**

1. User not found-> error indicating that we try to accept friendship with user which doesnt exist or we try to do it woth invalid token of no existing user id

```json
{
  "apiPath": "uri=/api/friendship/acceptFriendShip/110",
  "errorCode": "NOT_FOUND",
  "errorMessage": " not found with the given input data User not found with id: 110",
  "errorTime": "2024-03-05T13:03:36.8771697"
}
```

2. User already in friends -> error indicating that we try to accept friendship with user which is already in friends list

```json
{
  "apiPath": "uri=/api/friendship/acceptFriendShip/11",
  "errorCode": "METHOD_NOT_ALLOWED",
  "errorMessage": "You cannot accept this friend request because you are already friends",
  "errorTime": "2024-03-05T13:04:39.7626888"
}
```
3. Accepting friendship where you are not a pending user -> error indicating that we try to accept friendship as a user which is not in pending status
For example user1 creates invitation to user2 and user1 tries to change status of relationship from pending to accepted

```json
{
    "apiPath": "uri=/api/friendship/acceptFriendShip/12",
    "errorCode": "METHOD_NOT_ALLOWED",
    "errorMessage": "you are not allowed to accept this friendShip request because you are not pending user",
    "errorTime": "2024-03-05T13:07:01.4736658"
}
```

________________

## delete friendship (reject friend request or delete from friends)

```http
DELETE /api/friendship/deleteFriendShip/{id}
```

* **Description:**

Delete friendship. Endpoint used to reject a friend request from another user or delete a friend from friends list. After deleting a friendship, user1 and user2 are not friends anymore and data about friendship is deleted from database.
In request body we pass id of user with whom we want to delete a friendship. One user must be logged in


* **Request Example:**

  * no request Body

* **Response : **friendship deleted**"
* **Error Messages**

1. User not found-> error indicating that we try to accept friendship with user which doesnt exist or we try to do it woth invalid token of no existing user id

```json
{
  "apiPath": "uri=/api/friendship/acceptFriendShip/110",
  "errorCode": "NOT_FOUND",
  "errorMessage": " not found with the given input data User not found with id: 110",
  "errorTime": "2024-03-05T13:03:36.8771697"
}
```
__________________

## get relationship with given id

```http
GET   /api/friendship/getRelationShip
```

* **Description:**

Get relationship with given id. Endpoint used to get relationship with another user.

* **Request

```json
{
  "id": 12
}
```

* **Response Example:**

```json
{
  "friendshipId": 5,
  "user1": {
    "userId": 11,
    "firstName": "fr",
    "lastName": "fr",
    "email": "f@f.com",
    "createdAt": "2024-03-01T17:26:27.000+00:00"
  },
  "user2": {
    "userId": 12,
    "firstName": "fr",
    "lastName": "fr",
    "email": "f2@f.com",
    "createdAt": "2024-03-01T17:26:39.000+00:00"
  },
  "createdAt": "2024-03-02T13:36:12.000+00:00",
  "status": "accepted"
}
```

* **Error Messages**

1. User not found-> error indicating that we try to accept friendship with user which doesnt exist or we try to do it woth invalid token of no existing user id

```json
{
  "apiPath": "uri=/api/friendship/acceptFriendShip/110",
  "errorCode": "NOT_FOUND",
  "errorMessage": " not found with the given input data User not found with id: 110",
  "errorTime": "2024-03-05T13:03:36.8771697"
}
```

_______________________


# Endpoints for Authentication

## Register user

```http
POST /api/v1/auth/register
```

* **Description:**

Register user. Endpoint used to register a new user. In request body we pass user data: first name, last name, email and password.In response we get a token which must be places in header in Authorization field to authenticate user.

* **Request

```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string"
}
```

* **Response:**

```json
{
  "token": "JwtToken"
}
```

## Login user

```http
POST /api/v1/auth/authenticate
```

* **Description:**

Login user. Endpoint used to login a user. In request body we pass user email and password. In response we get a token which must be places in header in Authorization field to authenticate user.

* **Request

```json
{
  "email": "string",
  "password": "string"
}
```

* **Response**

```json
{
  "token": "JwtToken"
}
```

_______________________















