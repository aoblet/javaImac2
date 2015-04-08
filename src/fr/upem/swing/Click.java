package fr.upem.swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import sun.awt.VerticalBagLayout;


public class Click {
	public static JButton changeFrameTitle(String label, final String replace, final JFrame frame){
		JButton button = new JButton(label);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setTitle(replace);
			}
		});
		return button;
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		BoxLayout box = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(box);

		JButton button = new JButton("Test");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Test Click");
			}
		});
		
		
		frame.getContentPane().add(button);
		
		frame.getContentPane().add(Click.changeFrameTitle("Un", "**Un**", frame));
		frame.getContentPane().add(Click.changeFrameTitle("Deux", "**Deux**", frame));
		frame.getContentPane().add(Click.changeFrameTitle("Trois", "**Trois**", frame));
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
