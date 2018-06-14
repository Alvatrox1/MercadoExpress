package ulsa.mercado

class TipoPago {

	String descripcion

    static constraints = {
    }

    public  String toString() {
    	return " Tipo de Pago : " + descripcion + ". "
    }
}
