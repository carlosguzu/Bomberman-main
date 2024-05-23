
package game;
import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    
        public Menu() {
            // Configuración básica del JFrame
            setTitle("Bomberman Version 1.0");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 400);
            setResizable(false);
            setLocationRelativeTo(null);
    
            // Crear un panel de fondo personalizado

            

            BackgroundPanel panelContenido = new BackgroundPanel("src/backgrounds/bomb1.png"); // Reemplaza con la ruta de tu imagen
            panelContenido.setLayout(new BorderLayout());
    
            // Crear un JLabel para el título
            JLabel titulo = new JLabel(" ");
            titulo.setFont(new Font("Arial", Font.BOLD, 36)); 
            titulo.setForeground(Color.WHITE);
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
    
            // Crear un panel para los botones
            JPanel panelBotones = new JPanel();
            panelBotones.setOpaque(false);
            panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
    
            // Crear botones "Comenzar juego", "Ranking Of Players", "Credits" y "Salir del juego"
            JButton botonComenzar = new JButton("Start Game 💣");
            estilizarBoton(botonComenzar);
            botonComenzar.addActionListener(e -> {
                new RegisterGUI(); // Crea una nueva instancia de RegisterGUI
                dispose();
            });
    
            JButton botonRanking = new JButton("Ranking Of Players 🏆");
            estilizarBoton(botonRanking);
            botonRanking.addActionListener(e -> {
                new RankingPlayers();
                dispose();
            });
    
            JButton botonCreditos = new JButton("Credits 👥");
            estilizarBoton(botonCreditos);
            botonCreditos.addActionListener(e -> {
                new Credits();
                dispose();
            });
    
            JButton botonSalir = new JButton("Exit Game ✖️");
            estilizarBoton(botonSalir);
            botonSalir.addActionListener(e -> System.exit(0));
    
            JButton topRightButton = new JButton("Instructions 📜");
            estilizarBoton(topRightButton);
            topRightButton.addActionListener(e -> {
                new Instructions();
                dispose();
            });
    
            // Agregar los botones al panel de botones
            panelBotones.add(Box.createVerticalGlue());
            panelBotones.add(botonComenzar);
            panelBotones.add(Box.createRigidArea(new Dimension(0, 18)));
            panelBotones.add(botonRanking);
            panelBotones.add(Box.createRigidArea(new Dimension(0, 18)));
            panelBotones.add(topRightButton);
            panelBotones.add(Box.createRigidArea(new Dimension(0, 18)));
            panelBotones.add(botonCreditos);
            panelBotones.add(Box.createRigidArea(new Dimension(0, 18)));
            panelBotones.add(botonSalir);
            panelBotones.add(Box.createVerticalGlue());
    
            // Agregar el título y los botones al panel de contenido
            panelContenido.add(titulo, BorderLayout.NORTH);
            panelContenido.add(panelBotones, BorderLayout.CENTER);
    
            // Centrar el panel de contenido
            JPanel panelContenedor = new JPanel();
            panelContenedor.setLayout(new BorderLayout());
            panelContenedor.setOpaque(false);
            panelContenedor.add(panelContenido, BorderLayout.CENTER);
            add(panelContenedor);
        }
    
        private void estilizarBoton(JButton boton) {
            boton.setBackground(Color.WHITE);
            boton.setForeground(Color.BLACK);
            boton.setFont(boton.getFont().deriveFont(Font.BOLD, 16));
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
        }
    }
    
    class BackgroundPanel extends JPanel {
        private Image imagen;
    
        public BackgroundPanel(String rutaImagen) {
            try {
                imagen = new ImageIcon(rutaImagen).getImage();
            } catch (Exception e) {
                System.out.println("No se pudo cargar la imagen de fondo.");
            }
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    
    

    public static void main(String[] args) {
        new Menu().setVisible(true);
    }
}
