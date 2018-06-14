package ulsa.mercado

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = false)
class TipoPagoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TipoPago.list(params), model:[tipoPagoCount: TipoPago.count()]
    }

    def show(TipoPago tipoPago) {
        respond tipoPago
    }

    def create() {
        respond new TipoPago(params)
    }

    @Transactional
    def save(TipoPago tipoPago) {
        if (tipoPago == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoPago.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoPago.errors, view:'create'
            return
        }

        tipoPago.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoPago.label', default: 'TipoPago'), tipoPago.id])
                redirect tipoPago
            }
            '*' { respond tipoPago, [status: CREATED] }
        }
    }

    def edit(TipoPago tipoPago) {
        respond tipoPago
    }

    @Transactional
    def update(TipoPago tipoPago) {
        if (tipoPago == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoPago.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoPago.errors, view:'edit'
            return
        }

        tipoPago.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoPago.label', default: 'TipoPago'), tipoPago.id])
                redirect tipoPago
            }
            '*'{ respond tipoPago, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoPago tipoPago) {

        if (tipoPago == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tipoPago.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoPago.label', default: 'TipoPago'), tipoPago.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoPago.label', default: 'TipoPago'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
