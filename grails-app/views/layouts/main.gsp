<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    Mercado Express
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <assets:stylesheet src="bootstrap.css"/>
    <assets:javascript src="jquery-3.3.1.min.js"/>
    <assets:javascript src="bootstrap.js"/>

    <style>
        body {
            background: #E6E6E6;
            width: 100%;
            height: 100%;
        }
        footer {
            background: #CEF6F5;
        }
        nav {
            background: #CEF6F5;
        }

        
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${createLink(uri: '/Compra')}">Compra</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${createLink(uri: '/Categoria')}">Categorias</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${createLink(uri: '/CarritoCompra')}">carrito de compras</a>
                </li>
                  <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/Articulo')}">Articulo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/TipoPago')}">tipos de pagos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/TipoEnvio')}">Tipos de envio</a>
                    </li>
                <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/')}">Inicio</a>
                    </li>
            </ul>
        </div>
    </nav>

     <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <g:img dir="images" file="hotel.jpg" width="100%" height="300"/>
        </div>
    </div>
            <p>
                <center><h1>Mercado Express</h1></center>
            </p>
    <g:layoutBody/>

    <asset:javascript src="application.js"/>
<footer ><center>Desarrollado en la universidad la Salle Oaxaca</center></footer>
</body>
</html>
