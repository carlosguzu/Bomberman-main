package game;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterGUI extends JFrame {
    // Variables globales para almacenar el ID y nombre del jugador
    private int playerID;
    private String playerName;

    public RegisterGUI() {
        // Propiedades de la ventana
        setTitle("Register");
        setSize(375, 265);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        getContentPane().add(panel, BorderLayout.CENTER);

        // Etiqueta "Register"
        JLabel titulo = new JLabel("            Register            ");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.NORTH);

        // Campo de texto nombre de usuario
        JTextField nombreUsuario = new JTextField();
        nombreUsuario.setPreferredSize(new Dimension(200, 30));
        nombreUsuario.setBackground(Color.WHITE);
        panel.add(nombreUsuario, BorderLayout.CENTER);

        // Campo de texto2 Generador de ID
        JTextField generadordeID = new JTextField();
        generadordeID.setPreferredSize(new Dimension(200, 30));
        generadordeID.setBackground(Color.WHITE);
        generadordeID.setEditable(false);// Deshabilitado inicialmente
        panel.add(generadordeID, BorderLayout.CENTER);
        //
        generadordeID.setEditable(false);
        panel.add(generadordeID, BorderLayout.CENTER);
        //

        // Botón "Continue"
        JButton continuar = new JButton("Continue");
        continuar.setBackground(Color.WHITE);
        continuar.setForeground(Color.BLACK);
        continuar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(continuar, BorderLayout.SOUTH);

        //
        // Botón "Generate ID"
        JButton generarID = new JButton("Generate ID");
        generarID.setBackground(Color.WHITE);
        generarID.setForeground(Color.BLACK);
        generarID.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(generarID, BorderLayout.CENTER);

        generarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerID = generateRandomID();//
                generadordeID.setText(String.valueOf(generateRandomID()));
                continuar.setEnabled(true);//
            }
        });
        //

        continuar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Guardar el nombre y ID del jugador en las variables
                playerName = nombreUsuario.getText();//
                Engine.startGame(playerID, playerName); // Llama a startGame() cuando se hace clic en el botón

                System.out.println(playerID);
                System.out.println(playerName);

                dispose();
            }

        });

        //

        //

        // Posicionamiento del textfield y el botón
        // Se crea un "Box" para agrupar el textfield y el botón
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20)); // Separación entre el título y el textfield
        box.add(generadordeID);
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(generarID);
        box.add(Box.createVerticalStrut(20));// Separación entre el textfield y el botón
        box.add(nombreUsuario);
        box.add(Box.createRigidArea(new Dimension(0, 15)));
        box.add(continuar);
        box.add(Box.createRigidArea(new Dimension(0, 15)));

        // Se añade el "Box" al panel principal
        // panel.add(box, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Use BoxLayout for better positioning
        panel.add(box);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new RegisterGUI();
    }

    private int generateRandomID() {
        // Generate a random integer
        Random random = new Random();
        return random.nextInt(100000); // Adjust the upper bound as needed
    }
}
