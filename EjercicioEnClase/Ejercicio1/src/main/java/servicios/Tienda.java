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

		Producto p1 = Producto.builder().cantidad(10).nombre("Coca-Cola").precio(1.25).build();
		Producto p2 = Producto.builder().cantidad(12).nombre("GokuChiquito").precio(12.5).build();
		Producto p3 = Producto.builder().cantidad(13).nombre("LibroMatematicaDiscreta").precio(15000000).build();
		Producto p4 = fac.create("comida");
		p4.builder().cantidad(11).precio(10.3).nombre("Arroz");
		Producto p5 = fac.create("bebida");
		p5.builder().cantidad(35).precio(11.7).nombre("Vino Tinto");
		Producto p6 = fac.create("ropa");
		p6.builder().cantidad(123).precio(23.85).nombre("Saco navideño");

		servicio.create(p1);
		servicio.create(p2);
		servicio.create(p3);
		servicio.create(p4);
		servicio.create(p5);
		servicio.create(p6);
	}

	public  void mostrarProductos() {
		System.out.println("-----------------------------------------------------------------");
		ServicioImpl servicio = new ServicioImpl();

		List<Producto> productos = servicio.listar();
		productos.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------------");
	}

	public  void comprarProducto(int id) {
		ServicioImpl servicio = new ServicioImpl();

		Producto producto = servicio.read(id);

		if (producto != null && producto.getCantidad() > 0) {
			producto.setCantidad(producto.getCantidad() - 1);
			servicio.upgrade(producto);
			System.out.println("Se realizó la compra :3 ");
			System.out.println("-----------------------------------------------------------------");
		} else {
			System.out.println("Ya no quedan productos :v ");
		}
	}

	public  Integer mostrarMenu() {
		System.out.println("--- BIENVENIDO A LA TIENDA ---");
		System.out.println("Elija una de las siguientes opciones:");
		System.out.println("1. Crear producto");
		System.out.println("2. Leer producto por ID");
		System.out.println("3. Listar productos");
		System.out.println("4. Actualizar producto");
		System.out.println("5. Eliminar producto");
		System.out.println("6. Mostrar todos los productos");
		System.out.println("7. Comprar producto");
		System.out.println("8. Salir");
		System.out.print("Ingrese una opción(numérica): ");

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
				System.out.println("Ingrese nombre del producto:");
				String nombre = sc.next();
				System.out.println("Ingrese precio:");
				double precio = sc.nextDouble();
				System.out.println("Ingrese cantidad:");
				int cantidad = sc.nextInt();
				Producto nuevoProducto = Producto.builder().nombre(nombre).precio(precio).cantidad(cantidad).build();
				servicio.create(nuevoProducto);
				System.out.println("Producto creado exitosamente.");
				break;

			case 2:
				System.out.println("Ingrese ID del producto a buscar:");
				int idBuscar = sc.nextInt();
				Producto productoBuscado = servicio.read(idBuscar);
				if (productoBuscado != null) {
					System.out.println(productoBuscado);
				} else {
					System.out.println("Producto no encontrado.");
				}
				break;

			case 3:
				mostrarProductos();
				break;

			case 4:
				System.out.println("Ingrese ID del producto a actualizar:");
				int idActualizar = sc.nextInt();
				Producto productoActualizar = servicio.read(idActualizar);
				if (productoActualizar != null) {
					System.out.println("Ingrese nuevo nombre:");
					productoActualizar.setNombre(sc.next());
					System.out.println("Ingrese nuevo precio:");
					productoActualizar.setPrecio(sc.nextDouble());
					System.out.println("Ingrese nueva cantidad:");
					productoActualizar.setCantidad(sc.nextInt());
					servicio.upgrade(productoActualizar);
				} else {
					System.out.println("Producto no encontrado.");
				}
				break;

			case 5:
				System.out.println("Ingrese ID del producto a eliminar:");
				int idEliminar = sc.nextInt();
				servicio.delete(idEliminar);
				break;

			case 6:
				mostrarProductos();
				break;

			case 7:
				System.out.println("Ingrese ID del producto que quiere comprar:");
				int idCompra = sc.nextInt();
				comprarProducto(idCompra);
				break;

			case 8:
				salir = true;
				System.out.println("Gracias por usar la tienda. ¡Hasta luego!");
				break;

			default:
				System.out.println("Opción no válida, intente nuevamente.");
			}
		}
	}

}
