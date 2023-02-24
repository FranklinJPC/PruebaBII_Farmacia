import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Productos_ver {
    Statement st;
    PreparedStatement ps;
    public JPanel Principal;
    private JPanel ventana_opciones;
    private JButton crearButton;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton borrarButton;
    private JTextField tf_codigo;
    private JTextField tf_producto;
    private JTextField tf_compra;
    private JComboBox cb_venta;
    private JComboBox cb_proveedor;
    private JTextField tf_stock;
    private JTextField tf_venta;
    private JTextField tf_proveedor;

    public Productos_ver() {
    crearButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection connection;
            connection = getConnection();
            try {
                ps = connection.prepareStatement("INSERT INTO farmacia.productos " +
                        "(id_prod, nombre_prod, precioVenta_prod, precioCompra_prod, stock_prod, proveedor_prod) " +
                        "VALUES (?,?,?,?,?,?)");
                ps.setString(1,tf_codigo.getText());
                ps.setString(2,tf_producto.getText());
                ps.setDouble(3, Double.parseDouble(tf_venta.getText()));
                ps.setDouble(4, Double.parseDouble(tf_compra.getText()));
                ps.setInt(5, Integer.parseInt(tf_stock.getText()));
                ps.setString(6, tf_proveedor.getText());
                int res = ps.executeUpdate();
                if (res > 0)
                    JOptionPane.showMessageDialog(null, "Se Guardo correctamente!!", "Bien hecho", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "ERROR!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    buscarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection conexion;
            String auxVar;
            int auxiliar;
            try
            {
                conexion = getConnection();
                ResultSet rs;
                st = conexion.createStatement();
                rs = st.executeQuery("SELECT * FROM farmacia.productos " +
                        "WHERE id_prod =" + tf_codigo.getText() + ";");
                while (rs.next())
                {
                    tf_producto.setText(rs.getString("nombre_prod"));
                    cb_venta.addItem(rs.getInt("precioVenta_prod"));
                    tf_compra.setText(String.valueOf(rs.getInt("precioCompra_prod")));
                    tf_stock.setText(String.valueOf(rs.getInt("stock_prod")));
                    cb_proveedor.addItem(rs.getString("proveedor_prod"));
                }
                conexion.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    }
    public static Connection getConnection()
    {
        Connection conexion = null;
        String DBName = "farmacia";
        String url = "jdbc:mysql://localhost/";
        String user = "root";
        String password = "esfot123";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }
}
