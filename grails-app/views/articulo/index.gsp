<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'articulo.label', default: 'Articulo')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-articulo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-articulo" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${articuloList}" />

            <div class="pagination">
                <g:paginate total="${articuloCount ?: 0}" />
            </div>
        </div>

        <div id="muestra">
            
                <g:if test="${articuloList == null}">
                    VACIA
                </g:if>
                <table id="example">
                    <g:each in="${articuloList}" var="res">
                        <tr>
                            <tr>
                                <td>${res.id}</td>
                                <td>${res.nombre}</td>
                                <td>${res.categoria}</td>
                                <td>${res.descripcion}</td>
                                <td>${res.precio}</td>
                                <td>
                                    <g:form action="agregarToCarrito">
                                        <input type="hidden" name="id" value="${res.id}" />
                                        <input type="submit" value="Crear" />
                                    </g:form>
                                </td>
                            </tr>
                        </tr>
                    </g:each>
                </table>
            
        </div>




    </body>
</html>