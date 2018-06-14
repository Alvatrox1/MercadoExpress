package ulsa.mercado

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USUARIO'])
@Transactional(readOnly = false)
class CarritoCompraController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def n = SesionDos.executeQuery("select max(id) from SesionDos")
       // println "id query " + n
        Integer s=n[0]
       // println "valor s" + s
        params.max = Math.min(max ?: 10, 100)
        //respond CarritoCompra.list(params), model:[carritoCompraCount: CarritoCompra.count()]
        //def con = CarritoCompra.executeQuery("Select all From CarritoCompra Where idSesion = " + s )
        def con=CarritoCompra.findAllByIdSesion(s)
       // println con
        [elementos:con]

    }

    def show(CarritoCompra carritoCompra) {
        respond carritoCompra
    }

    def create() {
        respond new CarritoCompra(params)
/*
        def lista = Articulo.executeQuery('From Articulo')
        [results:lista]
        println lista
*/
    }

    @Transactional
    def save(CarritoCompra carritoCompra) {
        if (carritoCompra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (carritoCompra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond carritoCompra.errors, view:'create'
            return
        }

        carritoCompra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'carritoCompra.label', default: 'CarritoCompra'), carritoCompra.id])
                redirect carritoCompra
            }
            '*' { respond carritoCompra, [status: CREATED] }
        }
    }

    def edit(CarritoCompra carritoCompra) {
        respond carritoCompra
    }

    @Transactional
    def update(CarritoCompra carritoCompra) {
        if (carritoCompra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (carritoCompra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond carritoCompra.errors, view:'edit'
            return
        }

        carritoCompra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'carritoCompra.label', default: 'CarritoCompra'), carritoCompra.id])
                redirect carritoCompra
            }
            '*'{ respond carritoCompra, [status: OK] }
        }
    }

    @Transactional
    def delete(CarritoCompra carritoCompra) {

        if (carritoCompra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        carritoCompra.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'carritoCompra.label', default: 'CarritoCompra'), carritoCompra.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'carritoCompra.label', default: 'CarritoCompra'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
