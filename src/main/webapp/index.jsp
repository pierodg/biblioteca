<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
    <body>
        <h1>${hello}</h1>
            <ul>
                <c:forEach items="${libri}" var="libro">
                <li>${libro.titolo}</li>
                <li>${libro.editore}</li>
                <li>${libro.anno_pubblicazione}</li>
                <li>${libro.genere}</li>
                </c:forEach>
            </ul>
    </body>
</html>
