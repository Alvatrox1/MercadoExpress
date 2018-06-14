<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'carritoCompra.label', default: 'CarritoCompra')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-carritoCompra" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-carritoCompra" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${carritoCompraList}" />

            <div class="pagination">
                <g:paginate total="${carritoCompraCount ?: 0}" />
            </div>
        </div>
             <div id="muestra">
             <g:if test="${elementos == null}">
                    VACIA
                </g:if>
                <table id="example">
                    <g:each in="${elementos}" var="ele">
                        <tr>
                            <tr>
                                <td> <h4> PRODUCTO </h4> </td>
                            </tr>
                            <tr>
                                <td>${ele.articulo}</td>
                            </tr>
                        </tr>
                    </g:each>
                </table>
            
        </div>
        <div>
        <g:link controller="compra" action="create">
            <input type="submit" value="Comprar todo"/>
        </g:link>
        </div>

    </body>
</html>