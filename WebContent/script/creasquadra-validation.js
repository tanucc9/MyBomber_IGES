let validated = false;
$("#form_creasquadra").on("submit", function(form) {validateTeamsFields(form)});

function validateTeamsFields(form) {
    if (!validated) {
        form.preventDefault();

        const validationNome = validateNome();
        const validationNomeAbbr = validateNomeAbbr();
        const validationCitta = validateCitta();
        const validationDescr = validateDescr();
        //const validationLogo = validateLogo();

        if (validationNome && validationNomeAbbr && validationCitta && validationDescr) {
            validated = true;
            $("#form_creasquadra").submit();
        }
    }
}

function validateNome() {
    let containerNome = $("#container_nomeSquadra");
    const nomeSquadra = containerNome.find("input").val();
    let resultValidation = true;
    const regexNome = /^[A-Za-z \']*$/;

    if (nomeSquadra.length > 30 ) {
        containerNome.find(".invalid-feedback").html("Lunghezza massima: 30 caratteri");
        resultValidation = false;
    } else if (nomeSquadra.length < 1 ) {
        containerNome.find(".invalid-feedback").html("Lunghezza minima: 1 carattere");
        resultValidation = false;
    } else if (!nomeSquadra.match(regexNome)) {
        containerNome.find(".invalid-feedback").html("Errore formato. Es. Tigers");
        resultValidation = false;
    }

    if (!resultValidation) {
        containerNome.find("input").removeClass("is-valid");
        containerNome.find("input").addClass("is-invalid");
    } else {
        containerNome.find("input").removeClass("is-invalid");
        containerNome.find("input").addClass("is-valid");
    }

    return resultValidation;
}

function validateNomeAbbr() {
    let containerNomeAbbr = $("#container_nomeSquadraAbbr");
    const nomeAbbr = containerNomeAbbr.find("input").val();
    let resultValidation = true;
    const regexNomeAbbr = /^[A-Za-z]*$/;

    if (nomeAbbr.length > 3 ) {
        containerNomeAbbr.find(".invalid-feedback").html("Lunghezza massima: 3 caratteri");
        resultValidation = false;
    } else if (nomeAbbr.length < 1 ) {
        containerNomeAbbr.find(".invalid-feedback").html("Lunghezza minima: 1 carattere");
        resultValidation = false;
    } else if (!nomeAbbr.match(regexNomeAbbr)) {
        containerNomeAbbr.find(".invalid-feedback").html("Errore formato. Es. Tig");
        resultValidation = false;
    }

    if (!resultValidation) {
        containerNomeAbbr.find("input").removeClass("is-valid");
        containerNomeAbbr.find("input").addClass("is-invalid");
    } else {
        containerNomeAbbr.find("input").removeClass("is-invalid");
        containerNomeAbbr.find("input").addClass("is-valid");
    }

    return resultValidation;
}

function validateCitta() {
    let containerCitta = $("#container_citta");
    const citta = containerCitta.find("input").val();
    let resultValidation = true;
    const regexCitta = /^[A-zÀ-ù '-]*$/;

    if (citta.length > 30 ) {
        containerCitta.find(".invalid-feedback").html("Lunghezza massima: 30 caratteri");
        resultValidation = false;
    } else if (citta.length < 1 ) {
        containerCitta.find(".invalid-feedback").html("Lunghezza minima: 1 carattere");
        resultValidation = false;
    } else if (!citta.match(regexCitta)) {
        containerCitta.find(".invalid-feedback").html("Errore formato. Es. Tig");
        resultValidation = false;
    }

    if (!resultValidation) {
        containerCitta.find("input").removeClass("is-valid");
        containerCitta.find("input").addClass("is-invalid");
    } else {
        containerCitta.find("input").removeClass("is-invalid");
        containerCitta.find("input").addClass("is-valid");
    }

    return resultValidation;
}

function validateDescr() {
    let containerDescr = $("#container_descr");
    const descr = containerDescr.find("textarea").val();
    let resultValidation = true;

    if (descr.length > 300 ) {
        containerDescr.find(".invalid-feedback").html("Lunghezza massima: 300 caratteri");
        resultValidation = false;
    } else if (descr.length < 30 ) {
        containerDescr.find(".invalid-feedback").html("Lunghezza minima: 30 caratteri");
        resultValidation = false;
    }

    if (!resultValidation) {
        containerDescr.find("textarea").removeClass("is-valid");
        containerDescr.find("textarea").addClass("is-invalid");
    } else {
        containerDescr.find("textarea").removeClass("is-invalid");
        containerDescr.find("textarea").addClass("is-valid");
    }

    return resultValidation;
}

function validateLogo() {
    let containerLogo = $("#container_logo");
    const logo = containerLogo.find("input").val();
    if (logo.length == 0)
        return true; //se non è stato passato il logo, la validazione non è necessaria.

    const extension = logo.split(".").pop();
    let resultValidation = true;

    if (extension != "png" && extension != "jpg" && extension != "jpeg") {
        containerLogo.find(".invalid-feedback").html("Estensioni supportate: png, jpg, jpeg");
        resultValidation = false;
    }

    if (!resultValidation) {
        containerLogo.find("input").removeClass("is-valid");
        containerLogo.find("input").addClass("is-invalid");
    } else {
        containerLogo.find("input").removeClass("is-invalid");
        containerLogo.find("input").addClass("is-valid");
    }

    return resultValidation;
}