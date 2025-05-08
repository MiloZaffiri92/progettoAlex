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
<h1>${corso.id == null ? 'Crea Nuovo Corso' : 'Modifica Corso'}</h1>

<form:form method="post" modelAttribute="corso" action="/corsi">
    <form:hidden path="id"/> <%-- Necessario per la modifica --%>

    <div class="mb-3">
        <form:label path="nome">Nome Corso:</form:label>
        <form:input path="nome" cssClass="form-control"/>
        <form:errors path="nome" cssClass="text-danger"/>
    </div>

     <div class="mb-3">
         <form:label path="annoAccademico">Anno Accademico:</form:label>
         <form:input path="annoAccademico" cssClass="form-control"/>
         <form:errors path="annoAccademico" cssClass="text-danger"/>
     </div>

    <div class="mb-3">
        <form:label path="docente.id">Docente:</form:label>
        <form:select path="docente.id" cssClass="form-select">
            <form:option value="" label="-- Seleziona Docente --"/>
            <form:options items="${docentiList}" itemValue="id" itemLabel="nomeCompleto"/>
        </form:select>
        <form:errors path="docente" cssClass="text-danger"/>
    </div>

<div class="mb-3">
    <label>Discenti:</label>
    <c:forEach var="discente" items="${discentiList}">
        <div class="form-check">
            <form:checkbox path="discenti" value="${discente}" cssClass="form-check-input"/>
            <label class="form-check-label">${discente.nomeCompleto}</label>
        </div>
    </c:forEach>
    <form:errors path="discenti" cssClass="text-danger"/>
</div>

    <button type="submit" class="btn btn-success">Salva</button>
    <a href="<c:url value='/corsi'/>" class="btn btn-secondary">Annulla</a>
</form:form>

</body>
</html>