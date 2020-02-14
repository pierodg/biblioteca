$(document).ready(function(){
     var numAut = 0;
    $('.addAutore').click(function(){
        numAut++; //incrementa id nomeAutore e cognomeAutore
        $('.autori').append("<div class=\"form-row delete\">" +
            "<div class=\"form-group col-5\">" +
            "<input type=\"text\" placeholder=\"Nome\" class=\"form-control\" id=\"nomeAutoreLibro"+numAut+"\" name=\"autoreNomeLibro\"></div>" +
            "<div class=\"form-group col-5\">" +
            "<input type=\"text\" placeholder=\"Cognome\" class=\"form-control\" id=\"cognomeAutoreLibro"+numAut+"\" name=\"autoreCognomeLibro\"></div>" +
            "<div class=\"form-group col-2\">" +
            "<button type=\"button\" class=\"btn btn-outline-danger delAutore\">Rimuovi</button></div></div>");
    });

    $('.autori').on('click','.delAutore',function(){
        $(this).closest(".delete").remove();
    });
});