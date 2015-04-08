package fr.upem.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Discovery {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Discovery");
		JButton button = new JButton("BUTTON");
		button.setToolTipText("My wonderful button");
		button.setForeground(Color.RED);
		button.setBackground(Color.GREEN);
		
		JTextField textField = new JTextField();
		textField.setText("My custom textfield disabled");
		textField.setEnabled(false);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JTextArea textArea = new JTextArea();
		textArea.setText("My custom textArea");
		textArea.setCursor(Cursor.getDefaultCursor());
		
		
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
