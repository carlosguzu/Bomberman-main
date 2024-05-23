package game;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextLevel extends JFrame {
    public NextLevel() {
        // Configuración básica del JFrame
        setTitle("¡LEVEL COMPLETE!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);

        // Crear un panel para el contenido
        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(Color.BLACK);
        panelContenido.setLayout(new BorderLayout());
        panelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear un espacio vacío
        Component espacio = Box.createRigidArea(new Dimension(0, 50));

        // Crear un JLabel para el título
        JLabel titulo = new JLabel("¡LEVEL COMPLETE!");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.BLACK);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        // Crear botones "Comenzar juego", "Ranking Of Players" y "Salir del juego"
        JButton botonComenzar = new JButton("Next Level");
        botonComenzar.setBackground(Color.WHITE);
        botonComenzar.setForeground(Color.BLACK);
        botonComenzar.setFont(new Font("Arial", Font.BOLD, 24));
        botonComenzar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonComenzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int playerID = RegisterGUI.getPlayerID();
                String playerName = RegisterGUI.getPlayerName();

                // Iniciar el iguiente nivel utilizando el ID y el nombre del jugador
                Engine.startGame(playerID, playerName);
                dispose();
            }
        });

        JButton botonMenu = new JButton("Menu");
        botonMenu.setBackground(Color.WHITE);
        botonMenu.setForeground(Color.BLACK);
        botonMenu.setFont(new Font("Arial", Font.BOLD, 24));
        botonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new Menu().setVisible(true)); // Ir al menú
                System.out.println("Score final: " + Engine.cumulativeScore);
                Engine.cumulativeScore = 0; // Reiniciar el puntaje acumulativo
                dispose();
            }
        });

        // Agregar los botones al panel de botones
        panelBotones.add(Box.createVerticalGlue());
        panelBotones.add(botonComenzar);
        panelBotones.add(Box.createRigidArea(new Dimension(0, 20)));
        panelBotones.add(botonMenu);
        panelBotones.add(Box.createVerticalGlue());

        // Agregar el título y los botones al panel de contenido

        // Agregar el espacio y el título al panel de contenido
        panelContenido.add(espacio, BorderLayout.NORTH);
        panelContenido.add(titulo, BorderLayout.NORTH);
        panelContenido.add(panelBotones, BorderLayout.CENTER);

        // Centrar el panel de contenido
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(panelContenido, BorderLayout.CENTER);
        add(panelContenedor);
    }

    public static void main(String[] args) {
        new NextLevel().setVisible(true);
    }

}
