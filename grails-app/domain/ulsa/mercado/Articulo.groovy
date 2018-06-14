package ulsa.mercado

class Articulo {

	String nombre
	Categoria categoria 
	String descripcion
	Double precio

    static constraints = {
    }

    public String toString() {
    	return "Nombre : " + nombre + ". Categoria : " + categoria + ". Descripcion " + descripcion +
    	". Precio : " + precio + ". "
    }

}
