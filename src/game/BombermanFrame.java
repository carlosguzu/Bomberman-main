package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class BombermanFrame extends JFrame implements FloorListener {

    private Floor floor;
    private BombermanComponent bombermanComponent;

    private JLabel scoreLabel;
    private JLabel playerNameLabel;

    public BombermanFrame(final String title, Floor floor, String playerName) throws HeadlessException {
        super(title);
        this.floor = floor;
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        bombermanComponent = new BombermanComponent(floor);
        floor.createPlayer(bombermanComponent, floor);
        setKeyStrokes();

        this.setLayout(new BorderLayout());
        this.add(bombermanComponent, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

        // Crear el panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        playerNameLabel = new JLabel(" Player: " + playerName);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        scoreLabel = new JLabel("Score: 0 " + " ");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 17));
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        topPanel.add(playerNameLabel, BorderLayout.WEST);
        topPanel.add(scoreLabel, BorderLayout.EAST);

        this.add(topPanel, BorderLayout.NORTH); // or frame.add(topPanel, BorderLayout.NORTH);

        // Crear el panel inferior
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            if (askUser("Are you sure you want to quit?")) {
                dispose();
                SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
            }
        });
        bottomPanel.add(quitButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        floor.addFloorListener(this);

    }

    public BombermanComponent getBombermanComponent() {
        return bombermanComponent;
    }

    private boolean askUser(String question) {
        return JOptionPane.showConfirmDialog(null, question, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void setKeyStrokes() {
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
        bombermanComponent.getInputMap().put(stroke, "q");
        bombermanComponent.getActionMap().put("q", quit);
    }

    private final Action quit = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    };

    @Override
    public void floorChanged() {
        // Actualizar estado del juego o interfaz gráfica basado en cambios del piso
        // (ej: actualizar scoreLabel)
        scoreLabel.setText("Score: " + Engine.cumulativeScore + " ");
    }
}
