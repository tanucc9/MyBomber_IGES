$("#eliminaSquadraBTN").on("click", function() {
    if (confirm('Sei sicuro di voler eliminare la squadra?')) {
        console.log("dentro if");
        location.href = "/mybomber/eliminaSquadra";
    }
});