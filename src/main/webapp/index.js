//import 'bootstrap/dist/css/bootstrap.css'
const URL = "https://accaroli.com/CA2-1.2/api/Person/";

////////////////////////////////////////////////////////////////////////////////////////////////////////

document.getElementById("buttonGET").addEventListener("click", getPersByID);
function getPersByID() {
    const fetchID = document.getElementById("GET").value;

    fetchJsonPromise(URL + fetchID, "GET")
        .then((json) => {
            if (json.code) {
                console.log("RESOLVED NOTOK: " + JSON.stringify(json));
                document.getElementById("fetchedGET").innerHTML =
                    "<br/> Vi kunne ikke finde en person med ID " + fetchID + " i vores database";
            }
            else {
                console.log("RESOLVED OK: " + JSON.stringify(json));
                printPerson("fetchedGET", json);
            }
        })
        .catch((json) => { console.log("REJECTED: " + JSON.stringify(json)); });
}

////////////////////////////////////////////////////////////////////////////////////////////////////////

document.getElementById("buttonPersByPhone").addEventListener("click", getPersByPhone);
function getPersByPhone() {
    const fetchPhone = document.getElementById("fetchByPhone").value;

    fetchJsonPromise(URL + "getPhone/" + fetchPhone, "GET")
        .then((json) => {
            if (json.code) {
                console.log("RESOLVED NOTOK: " + JSON.stringify(json));
                document.getElementById("fetchedPersByPhone").innerHTML =
                    "<br/> Vi kunne ikke telefonnummert " + fetchPhone + " i vores database";
            }
            else {
                console.log("RESOLVED OK: " + JSON.stringify(json));
                printPerson("fetchedPersByPhone", json);
            }
        })
        .catch((json) => { console.log("REJECTED: " + JSON.stringify(json)); });
}

////////////////////////////////////////////////////////////////////////////////////////////////////////

document.getElementById("buttonPersByZip").addEventListener("click", getPersByZip);
function getPersByZip() {
    const fetchZip = document.getElementById("fetchByZip").value;

    fetchJsonPromise(URL + "getCity/" + fetchZip, "GET")
        .then((json) => {
            if (json.code) {
                console.log("RESOLVED NOTOK: " + JSON.stringify(json));
                document.getElementById("fetchedPersByZip").innerHTML =
                "<br/>Beklager, din søgning efter" + fetchZip + " gav desværrer intet resultat.";
            }
            else {
                console.log("RESOLVED OK: " + JSON.stringify(json));
                printPerson("fetchedPersByZip", json);
            }
        })
        .catch((json) => { console.log("REJECTED: " + JSON.stringify(json)); });
}

//////////////////////////////////////////// HELPER FUNCTION ////////////////////////////////////////////

function printPerson(p, json) {
    document.getElementById(p).innerHTML =
        "<br/>" + json.firstname + " " + json.lastname +
        "<br/>" + json.email +
        "<br/>" + json.phones +
        "<br/>" + json.address +
        "<br/>" + json.cityinfo + " " + json.zip +
        "<br/>" + json.hobbies.join(", ");
}

const fetchJsonPromise = (requestURL, requestMethod, requestBody) => {
    const options = {
        method: requestMethod,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    };

    return fetch(requestURL, options)
        .then(response => { return response.json(); })
        .catch(error => { return Promise.reject({ status: "Network error", msg: "Unable to process request" }); });
}