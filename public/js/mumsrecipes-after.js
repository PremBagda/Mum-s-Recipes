// Display user name when logged in
firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
        let displayName = user.displayName;
        if (displayName == null) { // login via email & password
            displayName = getUsername(user.email);
        }
        const userButton = document.getElementById("userLogin");
        userButton.innerHTML = "<i class=\"fas fa-user\"></i> " + displayName;
        console.log("User signed in is: ", displayName);


        // Checks if user exists, if not user will be created in db.
        // does not return anything as it is an async function.
        userDatabaseExistenceCheck(displayName);

    } else {
        console.log("User is NOT signed in.");
    }
});

/*
 * Extracts the username from email.
 * return username
 */
function getUsername(str) {
    console.log("Entered");
    var end = str.indexOf("@");
    var username = str.substr(0, end);
    return username;
}

/*
 * Checks if user exists in database. If not, user will be created in database.
 */
function userDatabaseExistenceCheck(name) {
    // Connecting to database
    const db_users = firebase.firestore().collection("users").doc(name);

    db_users.get().then(function(doc) {
        if (doc.exists) {
            console.log("UserDatabaseExistenceCheck: " + name + " exists!");
        } else {
            console.log("UserDatabaseExistenceCheck: User does not exist!");
            // Function is async.
            createUserInDatabase(name);
        }
    }).catch(function(error) {
        console.log("UserDatabaseExistenceCheck: Connection error getting user:", error);
    });
}

/*
 * Creates user in database with default settings.
 */
function createUserInDatabase(name) {
    // Connecting to database
    const db_users = firebase.firestore().collection("users");

    // Copy contents of defaultUser to new user.
    db_users.get().then(query => {
        query.forEach (function(doc){
            if (doc.id == "defaultUser") {
                var promise = firebase.firestore().collection("users").doc(name).set(doc.data());
            }
        });
    });
}