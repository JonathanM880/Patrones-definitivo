package modelo;

import anotaciones.MiComponente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MiComponente(name = "bebida")
public class Bebida extends Producto{

	private int id;
	private String nombre;
	private double precio;
	private int cantidad;
	
	public Bebida(int id, String nombre, double precio, int cantidad, int id2, String nombre2, double precio2,
			int cantidad2) {
		super(id, nombre, precio, cantidad);
		id = id2;
		nombre = nombre2;
		precio = precio2;
		cantidad = cantidad2;
	}
	
}
