package fr.upem.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OneCheckBox extends JFrame {
	private JPanel m_main;
	private JCheckBox m_tmpSelected;
	
	public OneCheckBox(){
		this.setTitle("Home box");
		
		m_main = new JPanel();
		m_main.setLayout(new BoxLayout(m_main, BoxLayout.Y_AXIS));
		m_main.add( this.createCheckBox("SuperMan"));
		m_main.add( this.createCheckBox("SpiderMan"));
		m_main.add( this.createCheckBox("Zorro"));
		m_main.add( this.createCheckBox("Hulk"));
		
		this.getContentPane().add(m_main);
		this.setSize(500, 500);
	}
	
	public void unselectAll(){
		for(Component c: m_main.getComponents()){
			JCheckBox cb = (JCheckBox) c;
			cb.setSelected(false);
		}
	}
	
	public void unselectTmp(){
		if(m_tmpSelected != null){
			m_tmpSelected.setSelected(false);
			m_tmpSelected = null;
		}
	}
	
	public JCheckBox createCheckBox(String label){
		final JCheckBox cb = new JCheckBox(label);
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				OneCheckBox.this.unselectAll();
				OneCheckBox.this.unselectTmp();
				cb.setSelected(true);
				m_tmpSelected = cb;
			}
		});
		return cb;
	}
	public static void main(String[] args) {
		OneCheckBox one = new OneCheckBox();
		one.setVisible(true);
	}
}
