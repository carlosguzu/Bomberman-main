import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class RegisterGUI extends JFrame {

    private String playerID;
    private String playerName;

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
        panel.setLayout(null); // Use null layout for absolute positioning
        getContentPane().add(panel);

        // Etiqueta "Register"
        JLabel titulo = new JLabel("REGISTER");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(125, 10, 125, 25); // Set position and size
        panel.add(titulo);

        // Campo de texto nombre de usuario
        JTextField nombreUsuario = new JTextField();
        nombreUsuario.setBounds(100, 50, 175, 25); // Set position and size
        nombreUsuario.setBackground(Color.WHITE);
        panel.add(nombreUsuario);

        // Campo de texto Generador de ID
        JTextField generadordeID = new JTextField();
        generadordeID.setBounds(100, 90, 175, 25); // Set position and size
        generadordeID.setBackground(Color.WHITE);
        generadordeID.setEditable(false); // Deshabilitado inicialmente
        panel.add(generadordeID);

        // Botón "Generate ID"
        JButton generarID = new JButton("Generate ID");
        generarID.setBounds(100, 130, 175, 25); // Set position and size
        generarID.setBackground(Color.WHITE);
        generarID.setForeground(Color.BLACK);
        panel.add(generarID);

        generarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerID = UUID.randomUUID().toString();
                generadordeID.setText(playerID);
            }
        });

        // Botón "Continue"
        JButton continuar = new JButton("Continue");
        continuar.setBounds(137.5, 170, 100, 25); // Set position and size
        continuar.setBackground(Color.WHITE);
        continuar.setForeground(Color.BLACK);
        panel.add(continuar);

        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar el nombre y ID del jugador en las variables
                playerName = nombreUsuario.getText();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    System.out.println("\nPlayer ID: " + playerID);
                    System.out.println("Player Name: " + playerName);

                    // Simulate starting the game and inserting into the database
                    // Engine.startGame(playerID, playerName);
                    // DatabaseHandler.insertPlayer(playerName, playerID);

                    dispose();
                }
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterGUI());
    }
}
