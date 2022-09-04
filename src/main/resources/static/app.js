var stompClient = null;
var name= null;
var attendees= null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
}

function connect() {

    name= $("#name").val();
    if( attendees!== null && attendees!==[] ) {
        let _usr = attendees.find(a => a.name === name);
        if (_usr !== null) {
            alert("Name in use, choose another one!");
            return;
        }
    }

    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', function (acknowledge) {
            showMessages(JSON.parse(acknowledge.body).content);
        });

        stompClient.subscribe('/topic/attendees', function (users) {
            attendees= JSON.parse(users.body).attendees;
            showUsers();
        });

        $("#disconnect").removeClass("hidden");
        $("#name").prop("disabled",true);
        $("#connect").addClass("hidden");

        sendName();
        $("#chatter").html( 'Hi ' + name + '!' );
    });
}

function disconnect() {

    sendIdToDelete();
    $("#name").prop("disabled",false);

    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $("#disconnect").addClass("hidden");
    $("#connect").removeClass("hidden");

    $("#people").html(null);
    $("#chatter").html( null );

    cleanMessages();
    attendees= null;

    console.log(name + " has been disconnected!");
}

function sendMessage() {
    name= $("#name").val();
    let _txt= $("#messageToSend").val();
    if( !isUndefinedOrNullOrEmpty(_txt) )
    {
        stompClient.send("/app/message", {}, JSON.stringify({'sender': name,'text': _txt}));
        $("#messageToSend").val(null);
    }
}

function sendName() {
    name= $("#name").val();
    stompClient.send("/app/attending", {}, JSON.stringify({'name': name}));
}

function sendIdToDelete() {
    let _attendee= attendees.find(f => f.name===name);
    stompClient.send("/app/attending-nope", {}, JSON.stringify({"id":_attendee.id,"name": name}));
}


function showUsers() {

    $("#people").html(null);

    for(let usr of attendees)
        $("#people").append("<tr><td>" + usr.name + "</td></tr>");
}

function showMessages(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function cleanMessages() {
    $("#messages").html(null);
}


function isUndefinedOrNullOrEmpty(o)
{
    if( isUndefinedOrNull(o) )
        return true;

    if( !isUndefined(o.length) )
    {
        if( typeof o === 'string' )
            return o.trim().length < 1;
        else
            return o.length < 1;
    }

    return false;
}

const __UNDEFINED= 'undefined';
function isUndefined(o) { return typeof o===__UNDEFINED; }
function isNotUndefined(o) { return !isUndefined(o); }
function isUndefinedOrNull(o) { return isUndefined(o) || o===null; }

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });

    $("#name").change(function (e){
        e.preventDefault();
        let _name= $("#name").val();
        if( isUndefinedOrNullOrEmpty(_name) ) {
            $("#connect").prop('disabled', true);
        }
        else {
            $("#connect").prop('disabled', false);
            name = $("#name").val();
        }
    })

    $("#showAppStats").click(function (e){
       e.preventDefault();
       window.open("/action-monitor");
    });
});

