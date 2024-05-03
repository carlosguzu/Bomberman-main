package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.table.DefaultTableModel;

public class RankingPlayers extends JFrame {
    

    private JPanel panelPrincipal;
    private JLabel titulo;
    private JButton botonSalir;

    private Object[][] getRankingData() {

        final String JDBC_URL = "jdbc:mysql://localhost:3306/bomberman";
        final String USERNAME = "root";
        String PASSWORD = "";
        // Cargar el archivo de propiedades
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src\\game\\config.properties")) {
            prop.load(input);
            PASSWORD = prop.getProperty("DB_PASSWORD");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT p.id, p.nickname, m.score, m.date " +
                    "FROM players p " +
                    "JOIN matches m ON p.id = m.player_id " +
                    "ORDER BY m.score DESC";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            java.util.ArrayList<Object[]> rankingData = new java.util.ArrayList<>();
            int place = 1; // initialize place to 1
            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = place++; // increment place for each row
                row[1] = resultSet.getInt(1); // id
                row[2] = resultSet.getString(2); // nickname
                row[3] = resultSet.getInt(3); // score
                row[4] = resultSet.getString(4); // date
                rankingData.add(row);
            }
                

            Object[][] data = new Object[rankingData.size()][4];
            for (int i = 0; i < rankingData.size(); i++) {
                data[i] = rankingData.get(i);
            }

            return data;
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos del ranking: " + e.getMessage());
            return new Object[][] {};
        }
    }

    public RankingPlayers() {

        // Configuración del JFrame
        setTitle("Ranking Of Players");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);        
        setLocationRelativeTo(null);

        // Inicialización de componentes
        panelPrincipal = new JPanel();
        titulo = new JLabel("Ranking Of Players");
        botonSalir = new JButton("Exit");

        // ...

        // Configuración del panel principal
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.BLACK);

        // Configuración del título
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);

        // Obtén los datos de la base de datos
        Object[][] data = getRankingData();
        // Define los nombres de las columnas
        String[] columnNames = { "Place", "ID", "Nickname", "Score", "Date" };
        // Crea la tabla
        JTable table = new JTable(data, columnNames);

        // Crea un JScrollPane y agrega la tabla a él
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.BLACK);

        // Configuración del botón
        botonSalir = new JButton("Exit");
        botonSalir.setBackground(Color.WHITE);
        botonSalir.setForeground(Color.BLACK);
        botonSalir.setFont(new Font("Arial", Font.BOLD, 24));
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
                dispose();
            }
        });

        // Añadir componentes al panel principal
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER); // Agrega el JScrollPane en lugar del JTextArea
        panelPrincipal.add(botonSalir, BorderLayout.SOUTH);

        // Añadir el panel principal al JFrame
        add(panelPrincipal);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        new RankingPlayers();
    }

}
