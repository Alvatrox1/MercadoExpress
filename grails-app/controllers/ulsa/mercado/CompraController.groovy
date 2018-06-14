package ulsa.mercado

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_USUARIO'])
@Transactional(readOnly = false)
class CompraController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Compra.list(params), model:[compraCount: Compra.count()]
    }

    def show(Compra compra) {
        respond compra
    }

    def create() {
        respond new Compra(params)
    }


    @Transactional
    def crear() {
        Compra compra = new Compra()
        def n = SesionDos.executeQuery("select max(id) from SesionDos")
       // println "----- id query " + n
        Integer idq=n[0]
       /* println "id query en enter" + idq 

               println "--IMPRIMIR " + params

               println " tipoPago " + params.tipoPago.id
               println "tipo Envio " + params.tipoEnvio.id
               println "direccion  "  + params.direccion
        */
        def suma = CarritoCompra.executeQuery("select sum(precio) from CarritoCompra where idSesion ="+idq)
        //println "total : )" + suma 
        Double tot = suma[0]
        compra.idCarrito = idq
        compra.fecha=new Date()
        compra.total = tot
        compra.tipoPago=TipoPago.get(params.tipoPago.id)
        compra.tipoEnvio= TipoEnvio.get(params.tipoEnvio.id)
        compra.direccion=params.direccion
 
        compra.save flush:true
        SesionDos s = new SesionDos()
        s.llave=1
        s.save flush:true
    }


    @Transactional
    def save(Compra compra) {
        if (compra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (compra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond compra.errors, view:'create'
            return
        }

        compra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
                redirect compra
            }
            '*' { respond compra, [status: CREATED] }
        }
    }

    def edit(Compra compra) {
        respond compra
    }

    @Transactional
    def update(Compra compra) {
        if (compra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (compra.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond compra.errors, view:'edit'
            return
        }

        compra.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
                redirect compra
            }
            '*'{ respond compra, [status: OK] }
        }
    }

    @Transactional
    def delete(Compra compra) {

        if (compra == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        compra.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'compra.label', default: 'Compra'), compra.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'compra.label', default: 'Compra'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
