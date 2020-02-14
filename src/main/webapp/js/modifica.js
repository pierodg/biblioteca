$(document).ready(function () {
    var numAut = 0;
    var nomeAutore = "#nomeAutoreLibro";
    var cognomeAutore = "#cognomeAutoreLibro";

    $(".modify").click(function (e) {
        console.log("ID LIBRO : " + $(this).attr("id"));
        $.ajax({
            url:"http://localhost:8080/biblioteca/book?action=modifica",
            data: {
                idLibro: $(this).attr("id")
            },
            type: "POST",
            dataType: "json"
        })
            .done(function (data) {

                    $("#titoloLibro").val(data.titolo);
                    $("#editoreLibro").val(data.editore);
                    $("#annoPubblicazioneLibro").val(data.annoPubblicazione);
                    $("#genereLibro").html(data.genere.nome); //Il selector non funziona con val ////// usare html
                    $(nomeAutore).val(data.autore[0].nome);
                    $(cognomeAutore).val(data.autore[0].cognome);

                    $.each(data.autore, function(index, el) {

                    console.log("INDEX " + index);
                            $(nomeAutore+numAut).val(el.nome);
                            $(cognomeAutore+numAut).val(el.cognome);

                            $('.autori').append("<div class=\"form-row delete\">" +
                            "<div class=\"form-group col-5\">" +
                            "<input type=\"text\" placeholder=\"Nome\" class=\"form-control\" id=\"nomeAutoreLibro" + numAut + "\" name=\"autoreNomeLibro\"></div>" +
                            "<div class=\"form-group col-5\">" +
                            "<input type=\"text\" placeholder=\"Cognome\" class=\"form-control\" id=\"cognomeAutoreLibro" + numAut + "\" name=\"autoreCognomeLibro\"></div>" +
                            "<div class=\"form-group col-2\">" +
                            "<button type=\"button\" class=\"btn btn-outline-danger delAutore\">Rimuovi</button></div></div>");
                            
                });
            })
            .fail(function (xhr,status,errorThrow) {
                console.log("Error!!! " + errorThrow)
            });
    });
});

