var stompClient = null;
var notificationCount = 0;

$(document).ready(function() {
    console.log("Index page is ready");
    $(document).on('click', '.accept-button', function() {
        var requestId = $(this).data('request-id'); // get the request ID from the data attribute
        var jwtToken = sessionStorage.getItem('jwtToken'); // get the token from sessionStorage
        $.ajax({
            url: 'http://localhost:8080/api/friendship/acceptFriendShip/' + requestId,
            type: 'put',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", jwtToken);
            },
            success: function() {
                alert("Friend request accepted");
            },
            error: function(jqXHR) {
                alert("Failed to accept friend request");
                var errorResponse = JSON.parse(jqXHR.responseText);
                var errorMessage = errorResponse.errorMessage;
                $('#error-message').text(errorMessage);
            }
        });
    });


    $("#login").submit(function(event) {
        event.preventDefault();
        var email = $("#email").val();
        var password = $("#password").val();
        $.ajax({
            url: 'http://localhost:8080/api/v1/auth/authenticate',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify({
                "email": email,
                "password": password
            }),
            success: function(data) {
                var jwtToken = "Bearer " + data.token;
                sessionStorage.setItem('jwtToken', jwtToken); // save the token in sessionStorage
                connect(jwtToken);
            },
            error: function() {
                alert("Failed to authenticate");
            }
        });
    });

    $("#send-friend-request").submit(function(event) {
        event.preventDefault();
        var friendId = $("#friend-id").val();
        var jwtToken = sessionStorage.getItem('jwtToken'); // get the token from sessionStorage
        $.ajax({
            url: 'http://localhost:8080/api/friendship/createFriendShip/' + friendId,
            type: 'post',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", jwtToken);
            },
            success: function() {
                alert("Friend request sent");
            },
            error: function(jqXHR) {
                alert("Failed to send friend request");
                var errorResponse = JSON.parse(jqXHR.responseText);
                var errorMessage = errorResponse.errorMessage;
                $('#error-message').text(errorMessage);
            }
        });
    });

    $("#accept-friend-request").submit(function(event) {
        event.preventDefault();
        var requestId = $("#request-id").val();
        var jwtToken = sessionStorage.getItem('jwtToken'); // get the token from sessionStorage
        $.ajax({
            url: 'http://localhost:8080/api/friendship/acceptFriendShip/' + requestId,
            type: 'put',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", jwtToken);
            },
            success: function() {
                alert("Friend request accepted");
            },
            error: function() {
                alert("Failed to accept friend request");
            }
        });
    });


    $("#send").click(function() {
        sendMessage();
    });

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

    $("#notifications").click(function() {
        resetNotificationCount();
    });
});

function connect(token) {
    //var jwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IlVTRVIiLCJzdWIiOiJmQGYuY29tIiwiaWF0IjoxNzA5NDcyNjA4LCJleHAiOjM0NTU5NDcyNjA4fQ.2aMjQyMH6ZzQ_Hd2U2CU29t1tFmd4ZNv9qTCGhcoQUQ";
    //var jwtToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IlVTRVIiLCJzdWIiOiJmMkBmLmNvbSIsImlhdCI6MTcwOTQ5NTQxMSwiZXhwIjozNDU1OTQ5NTQxMX0.jn7lF1l895tocL2LQeFETMDOcrkrGSRhEA6xYNo9auM";
    var socket = new SockJS('http://localhost:8080/our-websocket?token=' + encodeURIComponent(token));
    stompClient = Stomp.over(socket);


    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        $('#notifications').css('background-color', 'green'); // Change the color of the notification to green
        updateNotificationDisplay();
        updateNotificationDisplay();
        stompClient.subscribe('/user/topic/friend-request-accept', function (message) {
            notificationCount++;
            showAcceptedMessage(JSON.parse(message.body));
        });
        stompClient.subscribe('/user/topic/friend-request-pending', function (message) {
            notificationCount++;
            showInvitationRequest(JSON.parse(message.body));
        });
    });

}

function showInvitationRequest(message) {
    // Assuming the request ID is included in the message object
    var requestId = message.userId;
    $("#messages").append("<tr><td>" + message.content + "</td><td><button class='accept-button' style='background-color: #4CAF50; color: white; border: none; padding: 15px 32px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px; cursor: pointer;' data-request-id='" + requestId + "'>Accept</button></td></tr>");
}
function showAcceptedMessage(message) {
    // Assuming the request ID is included in the message object
    var requestId = message.userId;
    $("#messages").append("<tr><td>" + message.content + "</td></tr>");
}


function sendMessage() {
    console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({'messageContent': $("#message").val()}));
}

function sendPrivateMessage() {
    console.log("sending private message");
    stompClient.send("/ws/private-message", {}, JSON.stringify({'messageContent': $("#private-message").val()}));
}

function updateNotificationDisplay() {
    if (notificationCount == 0) {
        $('#notifications').hide();
    } else {
        $('#notifications').show();
        $('#notifications').text(notificationCount);
    }
}

function resetNotificationCount() {
    notificationCount = 0;
    updateNotificationDisplay();
}
