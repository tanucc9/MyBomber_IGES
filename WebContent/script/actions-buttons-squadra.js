$("#eliminaSquadraBTN").on("click", function() {
    if (confirm('Sei sicuro di voler eliminare la squadra?')) {
        location.href = "/mybomber/eliminaSquadra";
    }
});

$("#uniscitiSquadraBTN").on("click", function() {
    if (confirm('Sei sicuro di voler unirti a questa squadra?')) {
        const id = $("#uniscitiSquadraBTN").attr("data-id-squadra");
        location.href = "/mybomber/uniscitiSquadra?id=" + id;
    }
});