package game;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.util.Random;

import game.Menu;

public class RegisterGUI extends JFrame {

    // Variables globales para almacenar el ID y nombre del jugador
    private static int playerID;

    public static int getPlayerID() {
        return playerID;
    }
    private String playerName;

    private Player player;

    static class DatabaseHandler {
        static final String JDBC_URL = "jdbc:mysql://localhost:3306/bomberman";
        static final String USERNAME = "root"; // USER
        static String PASSWORD = ""; // PASSWORD

        // Insert a new player into the database
        public static void insertPlayer(String playerName, int playerID) {
            // Cargar el archivo de propiedades
            Properties prop = new Properties();
            try (InputStream input = new FileInputStream("src\\game\\config.properties")) {
                prop.load(input);
                PASSWORD = prop.getProperty("DB_PASSWORD");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                System.out.println("Ingresado exitosamente a la base de datos");

                // insert id and nickname
                String query = "INSERT INTO players (id, nickname) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, playerID);
                statement.setString(2, playerName);
                statement.executeUpdate();

                // Insert into matches

                String query3 = "INSERT INTO matches (player_id, score, date) VALUES (?, 0, now())";
                PreparedStatement statement3 = connection.prepareStatement(query3);
                statement3.setInt(1, playerID);
                statement3.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            }
        }

    }

    public RegisterGUI() {

        // Propiedades de la ventana
        setTitle("Register");
        setSize(375, 265);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);        
        setLocationRelativeTo(null);

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
                playerID = generateRandomID();
                generadordeID.setText(String.valueOf(playerID));

                // continuar.setEnabled(true);//
            }
        });
        //

        continuar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Guardar el nombre y ID del jugador en las variables
                playerName = nombreUsuario.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    Engine.startGame(playerID, playerName); // Llama a startGame() cuando se hace clic en el botón


                    System.out.println("\nPlayer ID: " + playerID);
                    System.out.println("Player Name: " + playerName);

                    // Insert player into database
                    DatabaseHandler.insertPlayer(playerName, playerID);

                    dispose();
                }
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
        return random.nextInt(1000000000); // Adjust the upper bound as needed
    }
}
