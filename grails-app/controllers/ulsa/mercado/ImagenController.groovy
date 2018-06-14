package ulsa.mercado

class ImagenController {

    def index() { }

    def scaffold=Imagen

    def showImage = {
    	def imagen = Imagen.get(params.id)
    	response.outputStream << imagen.urlImagen
    	response.outputStream.flush()
    }

}
