package mercadoexpress

import ulsa.mercado.SesionDos
import ulsa.mercado.*

class BootStrap {

    def init = { servletContext ->

    	SesionDos s = new SesionDos()
    	s.llave=1
    	s.save()
    	def rolAdmin = new Rol(authority: 'ROLE_ADMIN').save()
    	def rolUsuario = new Rol(authority: 'ROLE_USUARIO').save()

    	def usuAdmin = new Usuario(username: 'admin', password: 'admin').save()
    	def usuSis = new Usuario(username: 'usuario', password: 'usuario').save()

    	UsuarioRol.create usuAdmin, rolAdmin
    	UsuarioRol.create usuSis, rolUsuario

    	UsuarioRol.withSession {
    		it.flush()
    		it.clear()
    	}
    }
    def destroy = {
    }
}
