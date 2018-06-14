<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <style type="text/css">
        footer{
            position: fixed;
        }

    </style>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <div id="content" role="main">
        <section class="row colset-2-its">


            <br><br><br><br><br><br><br><br><br><br>
        </section>
    </div>
    <p>
    <sec:ifNotLoggedIn>
    <div align="right">
        <form action="mercadoexpress/login/aunthenticate" method="POST" id="loginForm" class="ccsform" autocomplete="off">
        <p>
            <label for="username">Nombre del usuario:</label>
            <input type="text" class="text_" name="username" id="username">
        </p>
        <p>
            <label for="password">Contraseña:</label>
            <input type="password" class="text_" name="password" id="password">
        </p>
        <p>
        <g:link controller="bienvenida" action="index">
            <input type="submit" name="ingresar"/>
        </g:link> 
        </p>
        </form>
    </div>
    </sec:ifNotLoggedIn>
    </p>
</body>
</html>
