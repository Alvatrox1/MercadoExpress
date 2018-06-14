package ulsa.mercado

class Imagen {

	Date lastUpdated
	Date dateCreated
	byte[] urlImagen

    static constraints = {
    	urlImagen(maxSize:1073741824)
    }
}
