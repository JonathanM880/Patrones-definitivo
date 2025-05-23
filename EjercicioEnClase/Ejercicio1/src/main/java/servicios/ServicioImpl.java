package servicios;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.Singleton;
import modelo.Producto;
import crud.DbConfig;

public class ServicioImpl implements ServicioProducto {

	private Singleton singleton = Singleton.getInstance(); 
    
	@Override
	public void create(Producto p) {
		try (Connection con = singleton.getDbCon().getConnection()) {
			var pstmt = con.prepareStatement("INSERT INTO Producto (nombre, precio, cantidad) VALUES (?, ?, ?)");
			pstmt.setString(1, p.getNombre());
			pstmt.setDouble(2, p.getPrecio());
			pstmt.setInt(3, p.getCantidad());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Producto read(int id) {
		Producto producto = null;
		try (Connection con = singleton.getDbCon().getConnection()) {
			var pstmt = con.prepareStatement("SELECT * FROM producto WHERE id = ?");
			pstmt.setInt(1, id);
			var rs = pstmt.executeQuery();
			if (rs.next()) {
				producto = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"),
						rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			System.out.println("Error al leer producto: " + e.getMessage());
		}
		return producto;
	}

	@Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try (Connection con = singleton.getDbCon().getConnection()) {
            var pstmt = con.prepareStatement("SELECT * FROM Producto");
            var rs = pstmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getDouble("precio"), rs.getInt("cantidad"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public void upgrade(Producto p) {
        try (Connection con = singleton.getDbCon().getConnection()) {
            var pstmt = con.prepareStatement("UPDATE Producto SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?");
            pstmt.setString(1, p.getNombre());
            pstmt.setDouble(2, p.getPrecio());
            pstmt.setInt(3, p.getCantidad());
            pstmt.setInt(4, p.getId());
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                System.out.println("Producto actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection con = singleton.getDbCon().getConnection()) {
            var pstmt = con.prepareStatement("DELETE FROM Producto WHERE id = ?");
            pstmt.setInt(1, id);
            int rs = pstmt.executeUpdate();
            if (rs > 0) {
                System.out.println("Producto eliminado correctamente ");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
