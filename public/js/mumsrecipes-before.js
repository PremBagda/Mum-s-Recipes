/*
 * Checks the validity of the key entered for cuisine.
 * return true cuisine consists of alphabets; otherwise false.
 */
function isCuisineValid(event) {
    let code = event.key;
    let charCode = code.charCodeAt(0);
    // checks whether key entered is lowercase or uppercase alphabet.
    return ((charCode >= 65 && charCode <= 90) || (charCode >= 97 && charCode <= 122));
}

/*
 * Adds a cuisine specified by the user.
 */
function addCuisine() {

    // Get the cuisine name that needs to be added.
    var cuisineToAdd = document.getElementById("add-cuisine-form").value;
    // Get name of user.


    if (cuisineToAdd.length == 0) {
        alert("Please enter cuisine.");
        $("#modalAddCuisine").modal();
    } else {
        // Connecting to user's database
        const db_cuisines = firebase.firestore().collection("cuisine").doc("entire_list");

        var cuisineArray;

        // Get the names of all the cuisines in database
        db_cuisines.get()
            .then(doc => {
                cuisineArray = doc.data().cuisineArray;
                // Add new cuisine into array.
                cuisineArray.push(cuisineNameToAdd);
                cuisineArray.sort();
                // cuisineArray.splice(0, 1);

                // Overwritting the data in the database using the cuisineArray
                db_cuisines.set({
                    cuisineArray
                })
                    .then(function() {
                        console.log("Document successfully written!");
                    })
                    .catch(function(error) {
                        console.error("Error writing document: ", error);
                    });

            });
        clearCuisineTextInput();
    }
}