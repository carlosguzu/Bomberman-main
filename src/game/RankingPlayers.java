package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingPlayers extends JFrame {

    private JPanel panelPrincipal;
    private JLabel titulo;
    private JTextArea cajaResultados;
    private JButton botonSalir;

    public RankingPlayers() {

        // Configuración del JFrame
        setTitle("Ranking Of Players");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Inicialización de componentes
        panelPrincipal = new JPanel();
        titulo = new JLabel("Ranking Of Players");
        cajaResultados = new JTextArea();
        botonSalir = new JButton("Exit");

        // Configuración del panel principal
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(Color.BLACK);

        // Configuración del título
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);

        // Configuración del área de texto
        cajaResultados.setEditable(false);
        cajaResultados.setFont(new Font("Arial", Font.BOLD, 15));
        cajaResultados.setBackground(Color.BLACK);
        cajaResultados.setForeground(Color.WHITE);
        cajaResultados.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Configuración del botón
        JButton botonSalir = new JButton("Exit");
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
        panelPrincipal.add(cajaResultados, BorderLayout.CENTER);
        panelPrincipal.add(botonSalir, BorderLayout.SOUTH);

        // Añadir el panel principal al JFrame
        add(panelPrincipal);

        // Mostrar la ventana
        setVisible(true);

        // Llenar el área de texto con los resultados del ranking
        // (Aquí deberías reemplazar este código con la lógica para obtener
        // los datos del ranking de tu sistema)
        String[] name = { "Juan", "Diego", "Ronaldiño", "Martin" };
        int[] ID = { 123, 131, 102, 319 };
        int[] score = { 491, 581, 292, 291 }; // Guardar los scores de la base de datos en la siguiente variable tipo

        String[] cadenaID = new String[ID.length];
        for (int i = 0; i < ID.length; i++) {
            String cadena = String.valueOf(ID[i]);
            cadenaID[i] = cadena;
        }

        // Iterar sobre el array
        for (int i = 0; i < score.length - 1; i++) {
            // Comparar cada elemento con el siguiente
            for (int j = 0; j < score.length - i - 1; j++) {
                // Si el elemento actual es menor que el siguiente, intercambiarlos
                if (score[j] < score[j + 1]) {
                    int temp = score[j];
                    score[j] = score[j + 1];
                    score[j + 1] = temp;
                    // Intercambio de elementos en "name" y "cadenaID"
                    String tempName = name[j];
                    name[j] = name[j + 1];
                    name[j + 1] = tempName;

                    String tempCadenaID = cadenaID[j];
                    cadenaID[j] = cadenaID[j + 1];
                    cadenaID[j + 1] = tempCadenaID;
                }

                int cont = 0;
                String formattedResult = "";
                for (int x = 0; x < score.length; x++) {
                    formattedResult += "#" + ++cont + " - " + name[x] + "#" + cadenaID[x] + " - " + score[x] + "\n";
                }

                // cajaResultados.setAlignmentX(Component.CENTER_ALIGNMENT);
                cajaResultados.setText(formattedResult);

            }

        }

        //

        //

    }

    public static void main(String[] args) {
        new RankingPlayers();
    }

}
