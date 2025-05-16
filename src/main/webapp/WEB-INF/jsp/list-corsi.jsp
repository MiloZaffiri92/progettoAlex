<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-4">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Gestione Corsi</a>
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

<h1>Elenco Corsi</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/corsi/nuovo'/>">Nuovo Corso</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Anno Accademico</th>
        <th>Docente</th>
        <th>Discenti</th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="corso" items="${corsi}">
        <tr>
            <td>${corso.id}</td>
            <td>${corso.nome}</td>
            <td>${corso.annoAccademico}</td>
            <td>
                <c:if test="${corso.docente != null}">
                    ${corso.docente.nome} ${corso.docente.cognome}
                </c:if>
            </td>
            <td>
                <c:if test="${not empty corso.discenti}">
                    <c:forEach var="discente" items="${corso.discenti}">
                        ${discente.nome} ${discente.cognome}<br/>
                    </c:forEach>
                </c:if>
            </td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${corso.id}/edit'/>">Modifica</a>
                <a class="btn btn-sm btn-danger"
                   href="<c:url value='/corsi/${corso.id}/delete'/>"
                   onclick="return confirm('Sei sicuro di voler eliminare questo corso?')">Elimina</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>