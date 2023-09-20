import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

public class Editor extends JFrame implements ActionListener {
    // Text component
    JTextArea areaDeTexto;
    JFrame frame;

    Editor()
    {
        // Create a frame
        frame = new JFrame("Editor de texto");
        frame.setLocationRelativeTo(null);

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (Exception e) {
        }

        areaDeTexto = new JTextArea();

        JMenuBar barraDeMenu = new JMenuBar();


        JMenu archivo = new JMenu("Archivo");

        // Create menu items
        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem imprimir = new JMenuItem("Imprimir");

        // Add action listener
        nuevo.addActionListener(this);
        abrir.addActionListener(this);
        guardar.addActionListener(this);
        imprimir.addActionListener(this);

        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(imprimir);


        JMenu editar = new JMenu("Editar");


        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem pegar = new JMenuItem("Pegar");


        cortar.addActionListener(this);
        copiar.addActionListener(this);
        pegar.addActionListener(this);

        editar.add(cortar);
        editar.add(copiar);
        editar.add(pegar);

        JMenuItem cerrar = new JMenuItem("Cerrar");

        cerrar.addActionListener(this);

        barraDeMenu.add(archivo);
        barraDeMenu.add(editar);
        barraDeMenu.add(cerrar);

        frame.setJMenuBar(barraDeMenu);
        frame.add(areaDeTexto);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e)
    {
        String opcion = e.getActionCommand();

        switch (opcion){
            case "Cortar":{
                areaDeTexto.cut();
                JOptionPane.showMessageDialog(frame, "Texto copiado en el portapapeles");
                break;
            }
            case "Copiar":{
                areaDeTexto.copy();
                JOptionPane.showMessageDialog(frame, "Texto copiado en el portapapeles");
                break;
            }
            case "Pegar": {
                areaDeTexto.paste();
                break;
            }
            case "Guardar": {
                JFileChooser jFileChooser = new JFileChooser("f:");

                int respuesta = jFileChooser.showSaveDialog(null);

                if (respuesta == JFileChooser.APPROVE_OPTION) {


                    File archivoGuardar = new File(jFileChooser.getSelectedFile().getAbsolutePath());

                    try {

                        FileWriter escribir = new FileWriter(archivoGuardar, false);

                        BufferedWriter sobrescribir = new BufferedWriter(escribir);

                        sobrescribir.write(areaDeTexto.getText());
                        sobrescribir.flush();
                        sobrescribir.close();
                        JOptionPane.showMessageDialog(frame, "Archivo guardado con éxito");
                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(frame, evt.getMessage());
                    }

            } else {
                    JOptionPane.showMessageDialog(frame, "Operacion cancelada");
                }
                break;
            }
            case "Abrir":{

                JFileChooser jFileChooser = new JFileChooser("f:");
                int r = jFileChooser.showOpenDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    File archivoAbrir = new File(jFileChooser.getSelectedFile().getAbsolutePath());

                    try {

                        String s1 = "", sl = "";

                        FileReader fr = new FileReader(archivoAbrir);

                        BufferedReader br = new BufferedReader(fr);

                        sl = br.readLine();

                        while ((s1 = br.readLine()) != null) {
                            sl = sl + "\n" + s1;
                        }
                        areaDeTexto.setText(sl);
                    }
                    catch (Exception evt) {
                        JOptionPane.showMessageDialog(frame, evt.getMessage());
                    }
                }
                else
                    JOptionPane.showMessageDialog(frame, "Operacion cancelada");
                break;
            }
            case "Imprimir": {
                try {
                    areaDeTexto.print();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
                break;
            }
            case "Nuevo":{
                JOptionPane.showMessageDialog(frame, "Se perderan todos los cambios que no hayas guardado");
                areaDeTexto.setText("");
                break;
            }
            case "Cerrar":{
                JOptionPane.showMessageDialog(frame, "Vuelve pronto");
                System.exit(0);
            }
        }
    }

}
