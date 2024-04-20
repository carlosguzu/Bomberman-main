package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public final class Engine {

	private static final int TIME_STEP = 30;
	private static int width = 10;
	private static int height = 10;
	private static int nrOfEnemies = 5;
	private static Timer clockTimer = null;

	private static int playerID;
	private static String playerName;
	private static int score;

	public static void main(String[] args) {

		try {
			String url = "jdbc:mysql://localhost:3306/bombermandb";
			String user = "root";
			String password = "Me8uiE5oM&";
			java.sql.Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Conexión exitosa a la base de datos");
		} catch (Exception e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}

		SwingUtilities.invokeLater(() -> new Menu().setVisible(true)); // Iniciar la interfaz gráfica en el hilo de
																		// eventos

	}

	public static void startGame(int playerID, String playerName) {
		Floor floor = new Floor(width, height, nrOfEnemies);
		BombermanFrame frame = new BombermanFrame("Bomberman", floor);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		floor.addFloorListener(frame.getBombermanComponent());

		Action doOneStep = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				tick(frame, floor);
			}
		};
		clockTimer = new Timer(TIME_STEP, doOneStep);
		clockTimer.setCoalesce(true);
		clockTimer.start();
	}

	/*
	 * private static void nextLevel(BombermanFrame frame, Floor floor){
	 * clockTimer.stop();
	 * frame.dispose();
	 * startGame();
	 * }
	 */

	private static void gameOver(BombermanFrame frame, Floor floor) {
		clockTimer.stop();
		frame.dispose();
		SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
		score = floor.getTotalScore();
		/*
		 * String sql = INSERT INTO Players(Score_Players) VALUES (?);
		 * PreparedStatement pstmt = con.prepareStatement(sql);
		 * pstmt.setInt(1, score);
		 * pstmt.executeUpdate();
		 */
		System.out.println(score);
		// System.out.println(playerID + floor.nombreUsuario(playerName));
		// Print player ID and name
		// System.out.println("Player ID: " + playerID + ", Name: " + );
	}

	private static void tick(BombermanFrame frame, Floor floor) {
		if (floor.getIsGameOver()) {
			gameOver(frame, floor);
			/*
			 * } else if (floor.getIsNextLevel()){
			 * nextLevel(frame, floor);
			 */
		} else {
			floor.moveEnemies();
			floor.bombCountdown();
			floor.explosionHandler();
			floor.characterInExplosion();
			floor.notifyListeners();
		}
	}
}
