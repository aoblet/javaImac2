package fr.upem.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Clock extends JPanel{
	private long m_timestamp;
	private int m_ray;
	private Color m_pointsColor;
	private Color m_backgroundColor;
	private boolean m_autoRefresh;
	private Thread m_thAutoRefresh;
	private JLabel m_dateFormatted;
	
	public Clock(int ray, long timestamp) throws IllegalArgumentException{
		m_autoRefresh = true;
		m_ray = ray;
		if(timestamp<0)
			throw new IllegalArgumentException();
		m_timestamp = timestamp;
		
		m_pointsColor = Color.black;
		m_backgroundColor = Color.white;
		m_dateFormatted = new JLabel();
		this.setKeyListener();
		this.activeAutoRefresh();
		this.setFocusable(true);
	}
	
	public Thread getThreadAutoRefresh(){
		return m_thAutoRefresh;
	}
	
	public boolean getStateAutoRefresh(){
		return m_autoRefresh;
	}
	
	public void setAutoRefresh(boolean flag){
		m_autoRefresh = flag;
	}
	public long getTimestamp() {
		return m_timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.m_timestamp = timestamp;
	}
	
	public int[] getCoordLineFromTime(String type, int time, int [] centerClock){
		float lineLength = 1;
		float maxValue = 60;
		
		if(type.equals("hour")){
			maxValue = 12;
			lineLength = 0.6f;
		}
		else if (type.equals("minute"))
			lineLength = 0.8f;
		
		return new int[]{
				(int)(Math.round(m_ray*lineLength*Math.cos(((time/maxValue*2*Math.PI) + Math.PI/2))))*-1 + centerClock[0],
				(int)(Math.round(m_ray*lineLength*Math.sin(((time/maxValue*2*Math.PI) + Math.PI/2))))*-1 + centerClock[1]
		};
	}
	
	public void paintHours(Graphics g, int[] centerClock){
		g.setColor(Color.black);
		int x, y;
		for(int i = 1; i< 12; ++i){
			x = (int)(Math.round(m_ray*Math.cos((i/12.f*2*Math.PI) + Math.PI/2)))*-1 + centerClock[0];
			y = (int)(Math.round(m_ray*Math.sin((i/12.f*2*Math.PI) + Math.PI/2)))*-1 + centerClock[1];
			g.drawString(String.valueOf(i), x, y);
		}
		g.drawString("12", 250, 10);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		int center = 250; //hardcoded
		
		int[] centerClock = new int[]{center, center};
		int diameter = m_ray * 2;
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date(m_timestamp));
		int hour = cal.get(Calendar.HOUR); 
		int min = cal.get(Calendar.MINUTE); 
		int sec = cal.get(Calendar.SECOND); 
	
		g.clearRect(0, 0, 500, 500);
		g.setColor(m_backgroundColor);
		g.fillOval(0, 0, diameter, diameter);
		g.setColor(Color.black);
		g.drawOval(0, 0, diameter, diameter);
		
		//paint points
		g.setColor(m_pointsColor);
		int[] tmpCoord = this.getCoordLineFromTime("hour", hour, centerClock); 
		g.drawLine(centerClock[0], centerClock[1], tmpCoord[0], tmpCoord[1]);
		
		tmpCoord = this.getCoordLineFromTime("minute", min, centerClock); 
		g.drawLine(centerClock[0], centerClock[1], tmpCoord[0], tmpCoord[1]);
		
		tmpCoord = this.getCoordLineFromTime("sec", sec, centerClock); 
		g.drawLine(centerClock[0], centerClock[1], tmpCoord[0], tmpCoord[1]);
		
		this.paintDot(g, centerClock);
		this.paintHours(g, centerClock);
	}
	
	public void paintDot(Graphics g, int [] centerClock){
		int xS,yS, xE, yE;
		
		for(int i = 1; i<61 ; ++i){
			xS = (int)(Math.round(m_ray*Math.cos(i/60.0*2*Math.PI + (Math.PI/2))))*-1 + centerClock[0];
			yS = (int)(Math.round(m_ray*Math.sin(i/60.0*2*Math.PI + (Math.PI/2))))*-1 + centerClock[1];
			
			xE = (xS - centerClock[0]) / 10;
			yE = (yS - centerClock[1]) / 10*-1 ;
			
			
			if((i%5) == 0)
				g.drawLine(xS, yS, xS-xE , yS+yE);
			else
				g.drawOval(xS-3, yS-3, 6, 6);
		}
	}
	
	private void setKeyListener(){
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_B){
					m_pointsColor = Color.black;
					m_backgroundColor = Color.white;
					
				}
				else if (e.getKeyCode() == KeyEvent.VK_C){
					m_pointsColor = Color.cyan;
					m_backgroundColor = Color.yellow;
				}
				else{
					return;
				}
				Clock.this.repaint();
				Clock.this.revalidate();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}
	
	public void activeAutoRefresh(){
		m_thAutoRefresh = new Thread(new Runnable() {
			
			@Override
			public void run() {
				final GregorianCalendar tmpCal = new GregorianCalendar();
				while(true){
					if(m_autoRefresh == false)
						break;
					
					tmpCal.clear();
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							String formatted="";

							Clock.this.setTimestamp(System.currentTimeMillis());
							Clock.this.repaint();
							Clock.this.revalidate();
							
							tmpCal.setTime(new Date((System.currentTimeMillis())));
							formatted = "";
							formatted += tmpCal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" :"";
							formatted += tmpCal.get(Calendar.HOUR_OF_DAY) +":";
							
							formatted += tmpCal.get(Calendar.MINUTE) < 10 ? "0" :"";
							formatted += tmpCal.get(Calendar.MINUTE)+":";
							
							formatted += tmpCal.get(Calendar.SECOND) < 10 ? "0" :"";
							formatted += tmpCal.get(Calendar.SECOND);
							
							Clock.this.m_dateFormatted.setText(formatted);
						}
					});
					
					try{
						Thread.sleep(1000);
					} 
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		m_thAutoRefresh.start();
	}
	
	public JLabel getDateFormatted(){
		return m_dateFormatted;
	}
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		final Clock clock = new Clock(250, System.currentTimeMillis());
	    
		KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    focusManager.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent arg0) {
				if(arg0.getID() == KeyEvent.KEY_RELEASED && arg0.getKeyCode() == KeyEvent.VK_Q){
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					clock.setAutoRefresh(false);
				}
				return false;
			}
		});
	    
	    frame.addWindowListener(new WindowAdapter() {
	    	 public void windowClosing(WindowEvent e){
                 clock.setAutoRefresh(false);
             }
		});
	    
		final JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.add(clock);
		
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
		
		JPanel panelDate = new JPanel();
		panelDate.setLayout(new BoxLayout(panelDate, BoxLayout.X_AXIS));
		panelDate.add(clock.getDateFormatted());
		
		main.add(panelDate);
		main.add(panelButton);
		
		frame.getContentPane().add(main);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520, 600);
		frame.setVisible(true);
	}
}
