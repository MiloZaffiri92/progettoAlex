<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>${corso.id == null ? 'Crea Nuovo Corso' : 'Modifica Corso'}</title>
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

<h1>${corso.id == null ? 'Crea Nuovo Corso' : 'Modifica Corso'}</h1>

<form:form method="post" modelAttribute="corso" action="${pageContext.request.contextPath}/corsi">
    <form:hidden path="id"/>

    <div class="mb-3">
        <form:label path="nome" cssClass="form-label">Nome Corso:</form:label>
        <form:input path="nome" cssClass="form-control"/>
        <form:errors path="nome" cssClass="text-danger"/>
    </div>

    <div class="mb-3">
        <form:label path="annoAccademico" cssClass="form-label">Anno Accademico:</form:label>
        <form:input path="annoAccademico" cssClass="form-control"/>
        <form:errors path="annoAccademico" cssClass="text-danger"/>
    </div>

    <div class="mb-3">
        <form:label path="docenteId" cssClass="form-label">Docente:</form:label>
        <form:select path="docenteId" cssClass="form-select">
            <form:option value="" label="-- Seleziona Docente --"/>
            <c:forEach var="docente" items="${docentiList}">
                <form:option value="${docente.id}" label="${docente.nome} ${docente.cognome}"/>
            </c:forEach>
        </form:select>
        <form:errors path="docenteId" cssClass="text-danger"/>
    </div>

    <div class="mb-3">
        <label class="form-label">Discenti:</label>
        <div class="border p-3 rounded">
            <c:forEach var="discente" items="${discentiList}">
                <div class="form-check">
                    <form:checkbox path="discentiIds" value="${discente.id}" cssClass="form-check-input"/>
                    <label class="form-check-label">
                            ${discente.nome} ${discente.cognome}
                    </label>
                </div>
            </c:forEach>
        </div>
        <form:errors path="discentiIds" cssClass="text-danger"/>
    </div>

    <div class="mb-3">
        <button type="submit" class="btn btn-primary">Salva</button>
        <a href="<c:url value='/corsi/list'/>" class="btn btn-secondary">Annulla</a>
    </div>
</form:form>

</body>
</html>