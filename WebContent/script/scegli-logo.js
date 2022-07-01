console.log("scegli logo");

$(".loghi_container img").on("click", function(img) {
    let imgElem = $(this);
    $("#id_logo_scelto").val(imgElem.attr("data-id"));
    $(".loghi_container img").removeClass("selected_logo");
    imgElem.addClass("selected_logo");
});