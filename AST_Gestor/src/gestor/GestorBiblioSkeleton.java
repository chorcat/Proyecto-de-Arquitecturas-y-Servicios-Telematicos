/**
 * GestorBiblioSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.0  Built on : Jan 18, 2016 (09:41:27 GMT)
 */
package gestor;

import org.apache.axis2.context.MessageContext;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/**
 * GestorBiblioSkeleton java skeleton for the axisService
 */
public class GestorBiblioSkeleton {
	/**
	 * Auto generated method signature
	 *
	 * @param consultaTitulo
	 * @return consultaTituloResponse
	 */
	public gestor.ConsultaTituloResponse consultaTitulo(gestor.ConsultaTitulo consultaTitulo) {
		ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext().getProperty("db_conexion_biblio");
		gestor.ConsultaTituloResponse respuestaLibrosPorTitulo = new gestor.ConsultaTituloResponse();

		Libros libros = new Libros(consultaTitulo.getTitulo(), null, 0);
		ObjectSet<Object> resultado = db.queryByExample(libros);
		String cadena = "";

		while (resultado.hasNext()) {
			
			cadena =cadena + "\n" + resultado.next();
		}
		if (cadena == "")
			cadena = "No Hay Libros Con Dicho Título Disponibles";

		respuestaLibrosPorTitulo.set_return(cadena);

		return respuestaLibrosPorTitulo;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param introducirLibros
	 * @return
	 */
	public void introducirLibros(gestor.IntroducirLibros introducirLibros) {
		ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext().getProperty("db_conexion_biblio");
		Libros lib = new Libros(null, introducirLibros.getISBN(), 0);
		ObjectSet<Object> resultado = db.queryByExample(lib);
		if (resultado.size() == 1) {

			Libros libro_a_modificar = (Libros) resultado.next();
			int cantidad = libro_a_modificar.getCantidad();
			libro_a_modificar.setCantidad(cantidad + introducirLibros.getCantidad());
			db.store(libro_a_modificar);

		} else {

			Libros libro = new Libros(introducirLibros.getTitulo(), introducirLibros.getISBN(),
					introducirLibros.getCantidad());
			db.store(libro);

		}

		return;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param obtenerLibro
	 * @return obtenerLibroResponse
	 */
	public gestor.ObtenerLibroResponse obtenerLibro(gestor.ObtenerLibro obtenerLibro) {
		ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext().getProperty("db_conexion_biblio");

		gestor.ObtenerLibroResponse respuestaLibro = new gestor.ObtenerLibroResponse();

		Libros libro = new Libros(null, obtenerLibro.getISBN(), 0);
		ObjectSet<Object> resultado = db.queryByExample(libro);

		if (resultado.size() == 1) {

			Libros libro_a_modificar = (Libros) resultado.next();
			int cantidad = libro_a_modificar.getCantidad();

			if (cantidad - 1 == 0) {

				db.delete(libro_a_modificar);

			} else {

				libro_a_modificar.setCantidad(cantidad - 1);
				db.store(libro_a_modificar);
			}

			respuestaLibro.set_return(true);

		} else {

			respuestaLibro.set_return(false);

		}

		return respuestaLibro;
	}

	/**
	 * Auto generated method signature
	 *
	 * @param listarLibros
	 * @return listarLibrosResponse
	 */
	public gestor.ListarLibrosResponse listarLibros(gestor.ListarLibros listarLibros) {
		ObjectContainer db = (ObjectContainer) MessageContext.getCurrentMessageContext().getProperty("db_conexion_biblio");

		gestor.ListarLibrosResponse respuestaLibros = new gestor.ListarLibrosResponse();

		Libros libros = new Libros(null, null, 0);
		ObjectSet<Object> resultado = db.queryByExample(libros);
		String cadena = "";
		while (resultado.hasNext()) {
		
			cadena =cadena + "\n" + resultado.next();
		}
		if (cadena == "")
			cadena = "No Hay Libros Disponibles";

		respuestaLibros.set_return(cadena);
		return respuestaLibros;
	}
}
