<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Aggiungi un libro</title>

        <style>
            .table td, th {
                text-align: center;
            }
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                /* display: none; <- Crashes Chrome on hover */
                -webkit-appearance: none;
                margin: 0; /* <-- Apparently some margin are still there even though it's hidden */
            }

            input[type=number] {
                -moz-appearance:textfield; /* Firefox */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Aggiungi un nuovo libro</h1>
            <div class="col-xl-6 col-md-8">
                <form action="${pageContext.request.contextPath}/book?action=createUpdate" method="post">
                    <div class="form-group">
                        <label for="titoloLibro">Titolo</label>
                        <input type="text" value="" class="form-control" id="titoloLibro" name="titoloLibro">
                    </div>
                    <label for="nomeAutoreLibro">Autore</label>
                    <div class="autori">
                        <div class="form-row">
                            <div class="form-group col-5">
                                <input type="text" placeholder="Nome" class="form-control" id="nomeAutoreLibro" name="autoreNomeLibro">
                            </div>
                            <div class="form-group col-5">
                                <input type="text" placeholder="Cognome" class="form-control" id="cognomeAutoreLibro" name="autoreCognomeLibro">
                            </div>
                            <div class="form-group col-2">
                                <button type="button" class="btn btn-outline-primary addAutore">+</button>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editoreLibro">Editore</label>
                        <input type="text" value="" class="form-control" id="editoreLibro" name="editoreLibro">
                    </div>
                    <div class="form-group">
                        <label for="annoPubblicazioneLibro">Anno pubblicazione</label>
                        <input type="number" value="" class="form-control" id="annoPubblicazioneLibro" name="annoPubblicazioneLibro">
                    </div>
                    <div class="form-group">
                        <fieldset>
                            <label for="genere">Genere</label>
                            <select class="form-control" name="genere" id="genere">
                                <c:forEach items="${generi}" var="genere">
                                    <option id="genereLibro">${genere.nome}</option>
                                </c:forEach>
                                <option disabled selected id="genereLibro"></option>
                            </select>
                        </fieldset>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-primary">Salva</button>
                    </div>
                </form>
            </div>
        </div>
            <div class="col-12">
            <table class="table table-dark" id="bookTable">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Titolo</th>
                    <th scope="col">Autore</th>
                    <th scope="col">Editore</th>
                    <th scope="col">Anno</th>
                    <th scope="col">Genere</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${libri}" var="libro">
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titolo}</td>
                        <td>
                            <c:forEach items="${autori}" var="scrittori" >
                                <c:if test="${scrittori.key == libro.id}">
                                    ${scrittori.value}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${libro.editore}</td>
                        <td>${libro.annoPubblicazione}</td>
                        <td>${libro.genere.nome}</td>
                        <td>
                            <div class="btn-group btn-group-sm" role="group" aria-label="Button group">
                                <button type="button" id="${libro.id}" class="btn btn-success modify">Modifica</button>
                                <button type="button" id="${libro.id}" class="btn btn-danger delete">Elimina</button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addAutore.js"></script>
        <script src="${pageContext.request.contextPath}/js/modifica.js"></script>
        <script src="${pageContext.request.contextPath}/js/elimina.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
