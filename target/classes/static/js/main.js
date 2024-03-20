// script.js

// Ajoutez une fonction pour afficher le popup dès que la page est chargée
document.addEventListener("DOMContentLoaded", function () {
    openPopup();
});

// Fonction pour ouvrir le popup
function openPopup() {
    document.getElementById("popup").style.display = "block";
}

// Fonction pour fermer le popup
function closePopup() {
    document.getElementById("popup").style.display = "none";
}
