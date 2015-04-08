package fr.upem.swing;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Custom Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("My Label, with a short text");
		frame.getContentPane().add(label);
		label.setHorizontalAlignment(JLabel.HORIZONTAL);
		
		frame.getContentPane().add(new JButton("OK"), BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
