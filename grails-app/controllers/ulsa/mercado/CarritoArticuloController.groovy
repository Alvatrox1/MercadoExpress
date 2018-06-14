package ulsa.mercado

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class CarritoArticuloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CarritoArticulo.list(params), model:[carritoArticuloCount: CarritoArticulo.count()]
    }

    def show(CarritoArticulo carritoArticulo) {
        respond carritoArticulo
    }

    def create() {
        respond new CarritoArticulo(params)
    }

    @Transactional
    def save(CarritoArticulo carritoArticulo) {
        if (carritoArticulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (carritoArticulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond carritoArticulo.errors, view:'create'
            return
        }

        carritoArticulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'carritoArticulo.label', default: 'CarritoArticulo'), carritoArticulo.id])
                redirect carritoArticulo
            }
            '*' { respond carritoArticulo, [status: CREATED] }
        }
    }

    def edit(CarritoArticulo carritoArticulo) {
        respond carritoArticulo
    }

    @Transactional
    def update(CarritoArticulo carritoArticulo) {
        if (carritoArticulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (carritoArticulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond carritoArticulo.errors, view:'edit'
            return
        }

        carritoArticulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'carritoArticulo.label', default: 'CarritoArticulo'), carritoArticulo.id])
                redirect carritoArticulo
            }
            '*'{ respond carritoArticulo, [status: OK] }
        }
    }

    @Transactional
    def delete(CarritoArticulo carritoArticulo) {

        if (carritoArticulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        carritoArticulo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'carritoArticulo.label', default: 'CarritoArticulo'), carritoArticulo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'carritoArticulo.label', default: 'CarritoArticulo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
