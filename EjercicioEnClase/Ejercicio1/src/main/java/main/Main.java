package main;

import modelo.Producto;
import servicios.ServicioImpl;
import servicios.ServicioProducto;
import servicios.Tienda;

public class Main {
	public static void main(String[] args) {
		Tienda t = new Tienda();
		t.ejecutar();
	}
}
