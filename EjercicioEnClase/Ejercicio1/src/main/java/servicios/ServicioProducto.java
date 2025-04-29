package servicios;

import modelo.Producto;
import java.util.List;

import crud.DbConfig;

public interface ServicioProducto {

	void create(Producto p);

	Producto read(int id);

	List<Producto> listar();

	void upgrade(Producto p);

	void delete(int id);
}
