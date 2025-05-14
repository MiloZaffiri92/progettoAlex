<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Nuovo Docente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 2em;
            background-color: #f4f4f4;
        }
        form {
            background-color: #fff;
            padding: 1.5em;
            border-radius: 8px;
            max-width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        div {
            margin-bottom: 1em;
        }
        label {
            display: block;
            margin-bottom: 0.3em;
        }
        input {
            width: 100%;
            padding: 0.6em;
            box-sizing: border-box;
        }
        button {
            padding: 0.7em 1.5em;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <h2>Inserisci Nuovo Discente</h2>

    <form:form method="POST" modelAttribute="discente" action="${pageContext.request.contextPath}/discenti">

    <form:hidden path="id" />
        <div>
            <label for="nome">Nome:</label>
            <form:input path="nome" id="nome" required="true"/>
        </div>

        <div>
            <label for="cognome">Cognome:</label>
            <form:input path="cognome" id="cognome" required="true"/>
        </div>

        <div>
            <label for="matricola">Matricola:</label>
            <form:input path="matricola" id="matricola" required="true"/>
        </div>
        <div>
            <label for="cittaResidenza">Citta di Residenza:</label>
            <form:input path="cittaResidenza" id="cittaResidenza" required="true"/>
        </div>

        <div>
            <button type="submit">Salva</button>
        </div>
    </form:form>

</body>
</html>
