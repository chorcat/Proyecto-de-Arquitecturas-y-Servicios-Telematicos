package gestor;

public class Libros {
	private String Titulo;
	private String ISBN;
	private int Cantidad;

	public Libros(String titulo, String iSBN, int cantidad) {
		super();
		Titulo = titulo;
		ISBN = iSBN;
		Cantidad = cantidad;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Libros [Titulo=" + Titulo + ", ISBN=" + ISBN + ", Cantidad=" + Cantidad + "]";
	}

}
