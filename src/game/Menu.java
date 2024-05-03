package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        // Configuración básica del JFrame
        setTitle("Bomberman Version 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setBackground(Color.BLACK);
        //bloqeuar 
        setResizable(false);
        setLocationRelativeTo(null);

        // Crear un panel para el contenido
        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(Color.BLACK);
        panelContenido.setLayout(new BorderLayout());
        panelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        // Crear un JLabel para el título
        JLabel titulo = new JLabel("Bomberman 1.0");
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); 
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.BLACK);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        // Crear botones "Comenzar juego", "Ranking Of Players" y "Salir del juego"
        
        JButton botonComenzar = new JButton("Start Game 💣");
        botonComenzar.setBackground(Color.WHITE);
        botonComenzar.setForeground(Color.BLACK);
        botonComenzar.setFont(botonComenzar.getFont().deriveFont(Font.BOLD, 24)); 
        botonComenzar.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonComenzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterGUI(); // Crea una nueva instancia de RegisterGUI
                dispose();
            }
        });

        JButton botonRanking = new JButton("Ranking Of Players 🏆");
        botonRanking.setBackground(Color.WHITE);
        botonRanking.setForeground(Color.BLACK);
        botonRanking.setFont(botonRanking.getFont().deriveFont(Font.BOLD, 24)); 
        botonRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RankingPlayers();
                dispose();
            }
        });
        JButton BotonCreditos = new JButton("Credits 👥");
        BotonCreditos.setBackground(Color.WHITE);
        BotonCreditos.setForeground(Color.BLACK);
        BotonCreditos.setFont(BotonCreditos.getFont().deriveFont(Font.BOLD, 24)); 
        BotonCreditos.setAlignmentX(Component.CENTER_ALIGNMENT);
        BotonCreditos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Credits();
                dispose();
            }
        });
        JButton botonSalir = new JButton("Exit Game ✖️");
        botonSalir.setBackground(Color.WHITE);
        botonSalir.setForeground(Color.BLACK);
        botonSalir.setFont(botonSalir.getFont().deriveFont(Font.BOLD, 24)); 
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.addActionListener(e -> System.exit(0));
        // Creacion del boton JButton
        JButton topRightButton = new JButton("Instructions 📜");
        topRightButton.setBackground(Color.WHITE);
        topRightButton.setForeground(Color.BLACK); 
        topRightButton.setFont(topRightButton.getFont().deriveFont(Font.BOLD, 24)); 
        topRightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        topRightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Instructions();
                dispose();
            }
        });
        // Agregar los botones al panel de botones
        panelBotones.add(Box.createVerticalGlue());
        panelBotones.add(botonComenzar);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonRanking);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(topRightButton);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(BotonCreditos);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonSalir);
        panelBotones.add(Box.createVerticalGlue());

        // Agregar el título y los botones al panel de contenido
        panelContenido.add(titulo, BorderLayout.NORTH);
        panelContenido.add(panelBotones, BorderLayout.CENTER);

        // Crear el JPanel que contiene el JButton Instructions
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new BorderLayout());

        // Centrar el panel de contenido
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(panelContenido, BorderLayout.CENTER);
        add(panelContenedor);

        // Añadir el boton a la parte ESTE(EAST) del JPanel
        // topPanel.add(topRightButton, BorderLayout.EAST);

        // Añadir el nuevo JPanel al JPanel existente mediante el: BorderLayout.NORTH
        // constraint
        panelContenedor.add(topPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new Menu().setVisible(true);
    }
}
