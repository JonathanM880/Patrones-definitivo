package servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Bebida;
import modelo.Comida;
import modelo.Factory;
import modelo.FactoryImpl;
import modelo.Producto;
import modelo.Ropa;

public class Tienda {

	 static {
		ServicioImpl servicio = new ServicioImpl();
		Map<String, Class> componentes = new HashMap<String, Class>();
		Factory fac = new FactoryImpl();
		fac.init("modelo");
		
		servicio.create(Producto.builder().cantidad(10).nombre("Coca-Cola").precio(1.25).build());
		servicio.create(Producto.builder().cantidad(12).nombre("GokuChiquito").precio(12.5).build());
		servicio.create(Producto.builder().cantidad(13).nombre("LibroMatematicaDiscreta").precio(15000000).build());
		Producto p4 = fac.create("comida");
		servicio.create(p4.builder().cantidad(11).precio(10.3).nombre("Arroz").build());
		Producto p5 = fac.create("bebida");
		servicio.create(p5.builder().cantidad(35).precio(11.7).nombre("Vino Tinto").build());
		Producto p6 = fac.create("ropa");
		servicio.create(p6.builder().cantidad(123).precio(23.85).nombre("Saco navideño").build());
	}

	public  void mostrarProductos() {
		System.out.println("-----------------------------------------------------------------");
		ServicioImpl servicio = new ServicioImpl();

		List<Producto> productos = servicio.listar();
		productos.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------------");
	}

	public static void comprarProducto(int id, int cantidad) {
	    ServicioImpl s = new ServicioImpl();

	    Producto p = s.read(id);

	    if (p != null) {
	        if (p.getCantidad() >= cantidad) {
	            p.setCantidad(p.getCantidad() - cantidad);
	            s.upgrade(p);
	            System.out.println("Compra realizada: " + cantidad + " unidades de " + p.getNombre()+ " :D");
	            System.out.println("Gracias por la compra :3 ");
	        } else {
	            System.out.println("No hay suficiente stock disponible. Solo quedan " + p.getCantidad() + " unidades :´'( ");
	        }
	    } else {
	        System.out.println("Producto no encontrado :o");
	    }
	}


	public  Integer mostrarMenu() {
		System.out.println();
		System.out.println("--- BIENVENIDO A LA TIENDA ---");
		System.out.println("Elija una de las siguientes opciones:");
		System.out.println("1. Crear producto");
		System.out.println("2. Leer producto por ID");
		System.out.println("3. Mostrar productos");
		System.out.println("4. Actualizar producto");
		System.out.println("5. Eliminar producto");
		System.out.println("6. Comprar producto");
		System.out.println("7. Salir");
		System.out.print("Ingrese una opción (numérica): ");

		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		return opcion;
	}

	public  void ejecutar() {
		boolean salir = false;
		Scanner sc = new Scanner(System.in);
		ServicioImpl servicio = new ServicioImpl();

		while (!salir) {
			Integer opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				System.out.println("Ingrese nombre del producto: ");
				String nombre = sc.next();
				System.out.println("Ingrese precio: ");
				double precio = sc.nextDouble();
				System.out.println("Ingrese cantidad: ");
				int cantidad = sc.nextInt();
				Producto pNuevo = Producto.builder().nombre(nombre).precio(precio).cantidad(cantidad).build();
				servicio.create(pNuevo);
				System.out.println("Producto creado exitosamente :) ");
				break;

			case 2:
				System.out.println("Ingrese ID del producto a buscar: ");
				int idBuscar = sc.nextInt();
				Producto pBuscado = servicio.read(idBuscar);
				if (pBuscado != null) {
					System.out.println(pBuscado);
				} else {
					System.out.println("Producto no encontrado :( ");
				}
				break;

			case 3:
				mostrarProductos();
				break;

			case 4:
				System.out.println("Ingrese ID del producto a actualizar:");
				int idActualizar = sc.nextInt();
				Producto pActualizado = servicio.read(idActualizar);
				if (pActualizado != null) {
					System.out.println("Ingrese nuevo nombre:");
					pActualizado.setNombre(sc.next());
					System.out.println("Ingrese nuevo precio:");
					pActualizado.setPrecio(sc.nextDouble());
					System.out.println("Ingrese nueva cantidad:");
					pActualizado.setCantidad(sc.nextInt());
					servicio.upgrade(pActualizado);
				} else {
					System.out.println("Producto no encontrado.");
				}
				break;

			case 5:
				System.out.println("Ingrese ID del producto a eliminar:");
				int pEliminar = sc.nextInt();
				servicio.delete(pEliminar);
				break;

			case 6:
			    System.out.println("Ingrese ID del producto que quiere comprar:");
			    int idCompra = sc.nextInt();
			    System.out.println("Ingrese la cantidad que desea comprar:");
			    int cantidadCompra = sc.nextInt();
			    comprarProducto(idCompra, cantidadCompra);
			    break;

			case 7:
				salir = true;
				System.out.println("Gracias por usar la tienda <3 <3 <3");
				break;

			default:
				System.out.println("Opción no válida, intente nuevamente D: ");
			}
		}
	}

}
