$(document).ready(function () {
    $(".delete").click(function (e) {
        console.log($(this).attr("id"));
        $.ajax({
            url:"http://localhost:8080/biblioteca/book?action=elimina",
            data: {
                idLibro: $(this).attr("id")
            },
            type: "POST",
            dataType: "json"
        })
            .done(function (data) {
                console.log("Libro cancellato");
                location.reload();
            })
            .fail(function (xhr,status,errorThrow) {
                console.log("Errore!!!! " + errorThrow)
            });
    });
});