package ulsa.mercado

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SesionDosController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SesionDos.list(params), model:[sesionDosCount: SesionDos.count()]
    }

    def show(SesionDos sesionDos) {
        respond sesionDos
    }

    def create() {
        respond new SesionDos(params)
    }

    @Transactional
    def save(SesionDos sesionDos) {
        if (sesionDos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sesionDos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sesionDos.errors, view:'create'
            return
        }

        sesionDos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sesionDos.label', default: 'SesionDos'), sesionDos.id])
                redirect sesionDos
            }
            '*' { respond sesionDos, [status: CREATED] }
        }
    }

    def edit(SesionDos sesionDos) {
        respond sesionDos
    }

    @Transactional
    def update(SesionDos sesionDos) {
        if (sesionDos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sesionDos.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sesionDos.errors, view:'edit'
            return
        }

        sesionDos.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sesionDos.label', default: 'SesionDos'), sesionDos.id])
                redirect sesionDos
            }
            '*'{ respond sesionDos, [status: OK] }
        }
    }

    @Transactional
    def delete(SesionDos sesionDos) {

        if (sesionDos == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sesionDos.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sesionDos.label', default: 'SesionDos'), sesionDos.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sesionDos.label', default: 'SesionDos'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
