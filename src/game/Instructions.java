package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions extends JFrame {

    public Instructions() {
        setTitle("Game Instructions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setBackground(Color.BLACK);
        setResizable(false);        
        setLocationRelativeTo(null);

    
        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(Color.BLACK);
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Game Instructions");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
    
        JLabel upKeyLabel = new JLabel("Up Arrow: Move Up\n");
        upKeyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        upKeyLabel.setForeground(Color.WHITE);
        upKeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        JLabel downKeyLabel = new JLabel("Down Arrow: Move Down\n");
        downKeyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        downKeyLabel.setForeground(Color.WHITE);
        downKeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    
        JLabel leftKeyLabel = new JLabel("Left Arrow: Move Left"+"\n");
        leftKeyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftKeyLabel.setForeground(Color.WHITE);
        leftKeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    
        JLabel rightKeyLabel = new JLabel("Right Arrow: Move Right"+"\n");
        rightKeyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        rightKeyLabel.setForeground(Color.WHITE);
        rightKeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    
        JLabel spacebarLabel = new JLabel("Spacebar: Drop bomb" + "\n");
        spacebarLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        spacebarLabel.setForeground(Color.WHITE);
        spacebarLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        instructionPanel.add(titulo);
        instructionPanel.add(upKeyLabel);
        instructionPanel.add(downKeyLabel);
        instructionPanel.add(leftKeyLabel);
        instructionPanel.add(rightKeyLabel);
        instructionPanel.add(spacebarLabel);
        
        JButton menuButton = new JButton("Go to Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
                dispose();
            }   
        });
instructionPanel.add(menuButton);

        add(instructionPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Instructions();
    }
}
