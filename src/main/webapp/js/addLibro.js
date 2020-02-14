$(document).ready(function () {
    $(".addBook").click(function (e) {
        console.log($(this).attr("id"));
        $.ajax({
            url: "http://localhost:8080/biblioteca/book?action=saveOrUpdate",
            data: {
                titoloLibro: $(this).attr("titolo"),
                autoreLibro: $(this).attr("autore"),
                editoreLibro: $(this).attr("editore"),
                annoPubblicazioneLibro: $(this).attr("annoPubblicazione"),
                genereLibro: $(this).attr("genere")

                //idLibro: $(this).attr("id")
            },
            type: "POST",
            dataType: "json"
        })
            .done(function (data) {
                console.log("Dovrei stampare robe...");
                $("#titoloLibro").val(data.titoloLibro);
                $("#editoreLibro").val(data.editoreLibro);
                $("#annoPubblicazioneLibro").val(data.annoPubblicazioneLibro);
                $("#genereLibro").html(data.genere.genereLibro); //Il selector non funziona con val ////// usare html
            })
    })

})