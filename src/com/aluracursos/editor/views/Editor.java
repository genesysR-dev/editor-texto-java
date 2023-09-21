package com.aluracursos.editor.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Toolkit;

public class Editor extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelNuevo;
	private JLabel labelAbrir;
	private JTextArea areaDeTexto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor frame = new Editor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Editor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editor.class.getResource("/com/aluracursos/editor/imagenes/icons8-new-copy-24.png")));
		setTitle("Editor");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 486);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textInactiveText);
		panel.setBounds(0, 0, 836, 486);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBounds(0, 0, 836, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		panel.add(header);
		header.setLayout(null);
		header.setBackground(SystemColor.textInactiveText);
		
		JPanel btnexit = new JPanel();
		btnexit.setLayout(null);
		btnexit.setBackground(SystemColor.textInactiveText);
		
		btnexit.setBounds(783, 0, 53, 36);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(SystemColor.textInactiveText);
			     labelExit.setForeground(Color.white);
			}
		});
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.WHITE);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		areaDeTexto = new JTextArea();
		areaDeTexto.setTabSize(10);
		areaDeTexto.setLineWrap(true);
		areaDeTexto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		areaDeTexto.setForeground(Color.WHITE);
		areaDeTexto.setBackground(SystemColor.activeCaptionBorder);
		areaDeTexto.setBounds(73, 63, 720, 385);
		panel.add(areaDeTexto);
		
		JPanel btnNuevo = new JPanel();
		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nuevoArchivo();
			}
		});
		btnNuevo.setLayout(null);
		btnNuevo.setBackground(SystemColor.textInactiveText);
		btnNuevo.setBounds(10, 63, 53, 36);
		panel.add(btnNuevo);
		
		labelNuevo = new JLabel("");
		labelNuevo.setIcon(new ImageIcon(Editor.class.getResource("/com/aluracursos/editor/imagenes/icons8-new-copy-24.png")));
		labelNuevo.setBounds(0, 0, 53, 36);
		btnNuevo.add(labelNuevo);
		labelNuevo.setBackground(SystemColor.textInactiveText);
		labelNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		labelNuevo.setForeground(Color.WHITE);
		labelNuevo.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnAbrir = new JPanel();
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirArchivo();
			}
		});
		btnAbrir.setLayout(null);
		btnAbrir.setBackground(SystemColor.textInactiveText);
		btnAbrir.setBounds(12, 109, 53, 36);
		panel.add(btnAbrir);
		
		labelAbrir = new JLabel("");
		labelAbrir.setIcon(new ImageIcon(Editor.class.getResource("/com/aluracursos/editor/imagenes/icons8-opened-folder-24.png")));
		labelAbrir.setBounds(0, 0, 53, 36);
		btnAbrir.add(labelAbrir);
		labelAbrir.setBackground(SystemColor.textInactiveText);
		labelAbrir.setHorizontalAlignment(SwingConstants.CENTER);
		labelAbrir.setForeground(Color.WHITE);
		labelAbrir.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnImprimir = new JPanel();
		btnImprimir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				imprimir();
			}
		});
		btnImprimir.setLayout(null);
		btnImprimir.setBackground(SystemColor.textInactiveText);
		btnImprimir.setBounds(10, 201, 53, 36);
		panel.add(btnImprimir);
		
		JLabel labelAbrir_1 = new JLabel("");
		labelAbrir_1.setBounds(0, 0, 53, 36);
		btnImprimir.add(labelAbrir_1);
		labelAbrir_1.setIcon(new ImageIcon(Editor.class.getResource("/com/aluracursos/editor/imagenes/icons8-print-24.png")));
		labelAbrir_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelAbrir_1.setForeground(Color.WHITE);
		labelAbrir_1.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelAbrir_1.setBackground(SystemColor.textInactiveText);
		
		JPanel btnGuardar = new JPanel();
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				guardarArchivo();
			}
		});
		btnGuardar.setLayout(null);
		btnGuardar.setBackground(SystemColor.textInactiveText);
		btnGuardar.setBounds(10, 155, 53, 36);
		panel.add(btnGuardar);
		
		JLabel labelAbrir_1_1 = new JLabel("");
		labelAbrir_1_1.setBounds(0, 0, 53, 36);
		btnGuardar.add(labelAbrir_1_1);
		labelAbrir_1_1.setIcon(new ImageIcon(Editor.class.getResource("/com/aluracursos/editor/imagenes/icons8-save-24.png")));
		labelAbrir_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		labelAbrir_1_1.setForeground(Color.WHITE);
		labelAbrir_1_1.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelAbrir_1_1.setBackground(SystemColor.textInactiveText);
	}
	
	
	private void nuevoArchivo() {
		int nuevo = JOptionPane.showConfirmDialog(contentPane, "Se perderán los cambios no guardados, desea continuar?");
		if (nuevo != 1) {
			areaDeTexto.setText("");
		}
	}
	
	private void abrirArchivo() {
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
                 JOptionPane.showMessageDialog(null, evt.getMessage());
             }
         }
         else
             JOptionPane.showMessageDialog(null, "Operacion cancelada");
		
	}
	private void guardarArchivo() {
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
                 JOptionPane.showMessageDialog(null, "Archivo guardado con éxito");
             }
             catch (Exception evt) {
                 JOptionPane.showMessageDialog(null, evt.getMessage());
             }

     } else {
             JOptionPane.showMessageDialog(null, "Operacion cancelada");
         }
	}
	
	private void imprimir() {
		 try {
             areaDeTexto.print();
         }
         catch (Exception evt) {
             JOptionPane.showMessageDialog(null, evt.getMessage());
         }
	}

	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }//GEN-LAST:event_headerMousePressed

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
