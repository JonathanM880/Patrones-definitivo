package modelo;

import anotaciones.MiComponente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@MiComponente(name = "producto")
//En esta clase usé el patron builder :)
public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private int cantidad;
	
}
