package ulsa.mercado

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = false)
class TipoEnvioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TipoEnvio.list(params), model:[tipoEnvioCount: TipoEnvio.count()]
    }

    def show(TipoEnvio tipoEnvio) {
        respond tipoEnvio
    }

    def create() {
        respond new TipoEnvio(params)
    }

    @Transactional
    def save(TipoEnvio tipoEnvio) {
        if (tipoEnvio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoEnvio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoEnvio.errors, view:'create'
            return
        }

        tipoEnvio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoEnvio.label', default: 'TipoEnvio'), tipoEnvio.id])
                redirect tipoEnvio
            }
            '*' { respond tipoEnvio, [status: CREATED] }
        }
    }

    def edit(TipoEnvio tipoEnvio) {
        respond tipoEnvio
    }

    @Transactional
    def update(TipoEnvio tipoEnvio) {
        if (tipoEnvio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoEnvio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoEnvio.errors, view:'edit'
            return
        }

        tipoEnvio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoEnvio.label', default: 'TipoEnvio'), tipoEnvio.id])
                redirect tipoEnvio
            }
            '*'{ respond tipoEnvio, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoEnvio tipoEnvio) {

        if (tipoEnvio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tipoEnvio.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoEnvio.label', default: 'TipoEnvio'), tipoEnvio.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEnvio.label', default: 'TipoEnvio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
