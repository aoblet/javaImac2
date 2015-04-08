package fr.upem.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AddRemove extends JFrame{
	public static int cptButtonAdded = 1;
	public AddRemove(){
		this.setTitle("AddRemove");
		
		//panels
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
		
		JPanel mainButtons = new JPanel();
		mainButtons.setLayout(new BoxLayout(mainButtons, BoxLayout.X_AXIS));
		
		final JPanel addedButtons = new JPanel();
		addedButtons.setLayout(new BoxLayout(addedButtons, BoxLayout.PAGE_AXIS));
		
		
		//add button
		JButton addB = new JButton("Add");
		addB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JButton toAdd = new JButton(Integer.toString(cptButtonAdded++));

				toAdd.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						toAdd.setVisible(false);
					}
				});
				addedButtons.add(toAdd);
				addedButtons.revalidate();
			}
		});
		
		mainButtons.add(addB);
		
		//reset button
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addedButtons.removeAll();
				addedButtons.revalidate();
			}
		});
		
		mainButtons.add(reset);
		
		wrapperPanel.add(mainButtons);
		wrapperPanel.add(addedButtons);
		this.getContentPane().add(wrapperPanel, BorderLayout.CENTER);
		this.setSize(500, 500);
	}
	
	public static void main(String[] args) {
		AddRemove frame = new AddRemove();
		frame.setVisible(true);
	}
}
