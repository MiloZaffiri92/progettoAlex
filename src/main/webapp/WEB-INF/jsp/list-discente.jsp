<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Discenti</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Gestione Discenti</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/corsi/list'/>">Corsi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/discenti/lista'/>">Discenti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/docenti/lista'/>">Docenti</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1>Elenco Discenti</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/discenti/nuovo'/>">Nuovo Discente</a>

<form action="/discenti/perCitta" method="get">
    <label for="citta">Cerca per citta:</label>
    <input type="text" id="citta" name="citta" placeholder="Inserisci una citta">
    <button type="submit">Cerca</button>
</form>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Matricola</th>
        <th>Citta di Residenza</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="d" items="${discente}">
        <tr>
            <td>${d.id}</td>
            <td>${d.nome}</td>
            <td>${d.cognome}</td>
            <td>${d.matricola}</td>
            <td>${d.cittaResidenza}</td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/discenti/${d.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/discenti/${d.id}/delete'/>"
                   onclick="return confirm('Sei sicuro?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Risultati della ricerca per citta</h2>

<c:if test="${not empty risultatiCitta}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Città di Residenza</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="risultato" items="${risultatiCitta}">
            <tr>
                <td>${risultato.id}</td>
                <td>${risultato.nome}</td>
                <td>${risultato.cittaResidenza}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty risultatiCitta and param.citta != null}">
    <p>Nessun discente trovato per la citta di "${param.citta}".</p>
</c:if>

</body>
</html>