

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

## Get user by id
```http
GET /api/users/userById?id=12&friends=1
```

* **Description:**

Get user by id from database with optional list of friends and posts.

* **Query Parameters:**

| Parameter     | Type | Description                     |Default|
|:--------------| :--- |:--------------------------------|:------|
| `id`          | `number` | id of user to get (required)    |0|
| `postPage`    | `number` | number of page for posts data   |0|
| `friendsPage` | `number` | number of page for friends data |0|
| `friends`     | `boolean` |Include friends  with status accepted in response     |FALSE|
| `posts`       | `boolean` | Include posts in response       |FALSE |

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
POST /api/friendship/createFriendShip
```

* **Description:**

Create a friendship between two users. Endpoint used to send a friend request to another user.
Necessary condition to create a friendship with status pending waiting for approval is that user1 or user2 must be logged and user1 and user2 must be different users.
In request body we pass id of user with whom we want to create a friendship. After creating a friendship, user2 will receive a friend request from user1 and user1 is waiting for approval which changes statis from PENDING to ACCEPTED.

* **Request

```json
{
  "id": 12
}
```

* **Response : **user added to friends**"

_________________________________

## change status of friendship to accepted (accept friend request)

```http
PUT /api/friendship/acceptFriendShip
```

* **Description:**

Change status of friendship to accepted. Endpoint used to accept a friend request from another user. After accepting a friend request, user1 and user2 are friends and status of friendship is changed from PENDING to ACCEPTED.
Important condition is that one of the users must be logged and user1 and user2 must be different users. We pass in body request id of user with whom we want to accept a friendship.

* **Request

```json
{
  "id": 12
}
```

* **Response : **friendship accepted**"
________________

## delete friendship (reject friend request or delete from friends)

```http
DELETE /api/friendship/acceptFriendShip
```

* **Description:**

Delete friendship. Endpoint used to reject a friend request from another user or delete a friend from friends list. After deleting a friendship, user1 and user2 are not friends anymore and data about friendship is deleted from database.
In request body we pass id of user with whom we want to delete a friendship. One user must be logged in


* **Request

```json
{
  "id": 12
}
```

* **Response : **friendship deleted**"
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

_______________________














