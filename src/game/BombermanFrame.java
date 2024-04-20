package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;

public class BombermanFrame extends JFrame implements FloorListener {

	private Floor floor;
	private BombermanComponent bombermanComponent;

	//
	private JLabel scoreLabel;
	//

	public BombermanFrame(final String title, Floor floor) throws HeadlessException {
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

		//
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
		scoreLabel.setBounds(getWidth() - 100, 10, 100, 20);
		scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(scoreLabel);
		//
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

	//
	@Override
	public void floorChanged() {
		// Actualizar estado del juego o interfaz gráfica basado en cambios del piso
		// (ej: actualizar scoreLabel)
		scoreLabel.setText("Score: " + floor.getScore());
	}
	//
}
