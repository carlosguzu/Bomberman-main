package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Credits extends JFrame {
    public Credits() {
        // setLocationRelativeTo(null);
        setTitle("Credits");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setBackground(Color.BLACK);
        setResizable(false);        
        setLocationRelativeTo(null);

        JPanel creditsPanel = new JPanel();
        creditsPanel.setBackground(Color.BLACK);
        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Credits");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblCreditos = new JLabel(
                "The functionality of the game was taken from the Gaspar Coding repository. (2021)");
        lblCreditos.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCreditos.setSize(10, 10);
        lblCreditos.setForeground(Color.WHITE);
        lblCreditos.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblCredEst = new JLabel("The total functionality of this application was made by:  ");
        JLabel lblCredEst1 = new JLabel("\nCarlos Mario Guzmán Zúñiga");
        JLabel lblCredEst2 = new JLabel("\nJuan Alejandro Moreno Santos");
        JLabel lblCredEst3 = new JLabel("\nNatalia Rangel Franco");
        JLabel lblCredEst4 = new JLabel("\nNicolas Molina Diaz");
        lblCredEst.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCredEst.setSize(10, 10);
        lblCredEst.setForeground(Color.WHITE);
        lblCredEst.setHorizontalAlignment(SwingConstants.CENTER);
        lblCredEst1.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCredEst1.setSize(10, 10);
        lblCredEst1.setForeground(Color.WHITE);
        lblCredEst1.setHorizontalAlignment(SwingConstants.CENTER);
        lblCredEst2.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCredEst2.setSize(10, 10);
        lblCredEst2.setForeground(Color.WHITE);
        lblCredEst2.setHorizontalAlignment(SwingConstants.CENTER);
        lblCredEst3.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCredEst3.setSize(10, 10);
        lblCredEst3.setForeground(Color.WHITE);
        lblCredEst3.setHorizontalAlignment(SwingConstants.CENTER);
        lblCredEst4.setFont(new Font("Arial", Font.PLAIN, 18));
        lblCredEst4.setSize(10, 10);
        lblCredEst4.setForeground(Color.WHITE);
        lblCredEst.setHorizontalAlignment(SwingConstants.CENTER);
        JButton creditosBM = new JButton("Repositorio");
        creditosBM.setBackground(Color.WHITE);
        creditosBM.setForeground(Color.BLACK);
        creditosBM.setFont(new Font("Arial", Font.BOLD, 24));
        creditosBM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // URL que quieres abrir
                String url = "https://github.com/Gaspared/Bomberman";
                try {
                    // Obtén la instancia de Desktop
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    // Crea un objeto URI con la URL
                    URI uri = new URI(url);
                    // Abre la URL en el navegador predeterminado
                    desktop.browse(uri);
                } catch (IOException | URISyntaxException ex) {
                    // Manejo de excepciones si ocurre algún error al abrir la URL
                    ex.printStackTrace();
                }
            }
        });

        JButton creditosBM_nosotrosButton = new JButton("Repositorio");
        creditosBM_nosotrosButton.setBackground(Color.WHITE);
        creditosBM_nosotrosButton.setForeground(Color.BLACK);
        creditosBM_nosotrosButton.setFont(new Font("Arial", Font.BOLD, 24));
        creditosBM_nosotrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // URL que quieres abrir
                String url = "https://github.com/carlosguzu/Bomberman-main/tree/main/.vscode";
                try {
                    // Obtén la instancia de Desktop
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    // Crea un objeto URI con la URL
                    URI uri = new URI(url);
                    // Abre la URL en el navegador predeterminado
                    desktop.browse(uri);
                } catch (IOException | URISyntaxException ex) {
                    // Manejo de excepciones si ocurre algún error al abrir la URL
                    ex.printStackTrace();
                }
            }
        });
        JButton menuButton = new JButton("Go to Menu");
        menuButton.setBackground(Color.WHITE);
        menuButton.setForeground(Color.BLACK);
        menuButton.setFont(new Font("Arial", Font.BOLD, 24));
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
                dispose();
            }
        });
        JPanel creditosJuegoButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        creditosJuegoButtonsPanel.setBackground(Color.black);
        creditosJuegoButtonsPanel.add(lblCreditos);
        creditosJuegoButtonsPanel.add(creditosBM);

        JPanel creditosNosotrosButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        creditosNosotrosButtonsPanel.setBackground(Color.black);
        creditosNosotrosButtonsPanel.setPreferredSize(new Dimension(700, 200));
        creditosNosotrosButtonsPanel.add(lblCredEst);
        creditosNosotrosButtonsPanel.add(lblCredEst1, BOTTOM_ALIGNMENT);
        creditosNosotrosButtonsPanel.add(lblCredEst2, BOTTOM_ALIGNMENT);
        creditosNosotrosButtonsPanel.add(lblCredEst3, BOTTOM_ALIGNMENT);
        creditosNosotrosButtonsPanel.add(lblCredEst4, BOTTOM_ALIGNMENT);
        creditosNosotrosButtonsPanel.add(creditosBM_nosotrosButton);

        creditsPanel.add(titulo);

        creditsPanel.add(creditosJuegoButtonsPanel, BorderLayout.CENTER);
        creditsPanel.add(creditosNosotrosButtonsPanel, BorderLayout.CENTER);

        // creditsPanel.add(lblCreditos);

        // creditosnosotros.add(creditosBM_nosotrosButton);
        // creditsPanel.add(creditosBM, BorderLayout.NORTH);
        // creditsPanel.add(creditosBM_nosotrosButton);
        creditsPanel.add(menuButton, BorderLayout.WEST);
        add(creditsPanel);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Credits();
    }

}
