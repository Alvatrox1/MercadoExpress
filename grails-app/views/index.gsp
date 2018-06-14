<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <style>
        footer{
            position: fixed;
        }

        body {
            background: #f7f7f9;
        }

    </style>
</head>
<body>
    <br>
    <center>
        <div id="content" role="main">
                <form action="Bienvenida" action="index" method="POST" id="loginForm" class="ccsform" autocomplete="off">
                    <p>
                        <g:link controller="bienvenida" action="index">
                            <input type="submit" name="ingresar" value="Ingresar" />
                        </g:link> 
                    </p>
                </form>
        </div>
    </center>

    <br><br><br><br><br><br><br><br>
</body>
</html>
