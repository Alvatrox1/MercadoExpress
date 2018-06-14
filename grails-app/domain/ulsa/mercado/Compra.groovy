package ulsa.mercado

class Compra {
	Integer idCarrito
	Date fecha	
	Double total
	TipoPago tipoPago
	TipoEnvio tipoEnvio
	String direccion

    static constraints = {
    }

    public String toString() {
    	return " Fecha : " + fecha + ". Total : " + total + ". Tipo de Pago : " + tipoPago + 
    	". Tipo de Envio : " + tipoEnvio + " Direccion : " + direccion + ". "
    }

}
