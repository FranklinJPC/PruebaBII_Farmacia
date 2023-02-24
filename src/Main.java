import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        JFrame ventana = new JFrame("Productos");
        ventana.setContentPane(new Productos_ver().Principal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500,500);
        ventana.setVisible(true);
    }
}