package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Engine {

	private static final int TIME_STEP = 30;
	private static int width = 10;
	private static int height = 10;
	private static int nrOfEnemies = 5;
	private static Timer clockTimer = null;

	private static int score;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> new Menu().setVisible(true)); // Iniciar la interfaz gráfica en el hilo de
		// eventos

	}

	public static void startGame(int playerID, String playerName) {
		Floor floor = new Floor(width, height, nrOfEnemies);
		BombermanFrame frame = new BombermanFrame("Bomberman", floor);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		floor.addFloorListener(frame.getBombermanComponent());
		frame.setResizable(false);

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

		int playerID = RegisterGUI.getPlayerID();
		// System.out.println(playerID);
		clockTimer.stop();
		frame.dispose();
		SwingUtilities.invokeLater(() -> new GameOver().setVisible(true));
		score = floor.getTotalScore();

		// Insert player score into database

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
			String query = "UPDATE matches SET score =? WHERE player_id =?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, score);
			statement.setInt(2, playerID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar el puntaje del jugador: " + e.getMessage());
		}

		System.out.println("Score final (por game over): " + score);

	}

	private static void nextLevel(BombermanFrame frame, Floor floor) {
		int playerID = RegisterGUI.getPlayerID();
		clockTimer.stop();
		frame.dispose();
		SwingUtilities.invokeLater(() -> new NextLevel().setVisible(true));		
		score = floor.getTotalScore(); 
		// Insert player score into database
		
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
			String query = "UPDATE matches SET score =? WHERE player_id =?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, score);
			statement.setInt(2, playerID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar el puntaje del jugador: " + e.getMessage());
		}
		
		System.out.println("\nScore del nivel: " + score); // debe ser acumulativo

	}

	private static void tick(BombermanFrame frame, Floor floor) {
		if (floor.getIsGameOver()) {
			gameOver(frame, floor);

		} else if (floor.getIsNextLevel()) {
			nextLevel(frame, floor);

		} else {
			floor.moveEnemies();
			floor.bombCountdown();
			floor.explosionHandler();
			floor.characterInExplosion();
			floor.notifyListeners();
		}
	}

}
