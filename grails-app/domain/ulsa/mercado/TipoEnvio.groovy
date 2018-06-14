package ulsa.mercado

class TipoEnvio {

	String descripcion
	Double costo

    static constraints = {
    }

    public String toString() {
    	return " Tipo de Envio : " + descripcion + ". Costo : " + costo + ". "
    }
}
