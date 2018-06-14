<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'carritoCompra.label', default: 'CarritoCompra')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:if test="${listaArticulos == null }">
            Lista Vacia ( Carrito de Compras Vacio!. )
        </g:if>
        <table id="tabla">
            <g:each in="${listaArticulos" var="miLista">
                <tr>
                    <tr>
                        <td> ${miLista.Articulo} </td>
                        <td>
                            <g:form method="DELETE" action="eliminar">
                                <input type"hidden" name="id" value="${p.id}"/>
                                <input type="submit" value="Eliminar" />
                            </g:form>
                        </td>
                    </tr>
                </tr>
            </g:each>
        </table>
    </body>
</html>
