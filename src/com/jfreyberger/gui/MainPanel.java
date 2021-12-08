package com.jfreyberger.gui;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

import com.jfreyberger.utility.BackgroundPanel;
import com.jfreyberger.utility.LoadingTask;

public class MainPanel extends BackgroundPanel {

	JLabel lblWelcome;
	JLabel lblRace;
	JLabel lblClass;
	JLabel lblLogo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4848455952266752904L;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		super(new ImageIcon(MainPanel.class.getResource("/images/dirtDark.jpg")).getImage(), BackgroundPanel.SCALED);
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout(0, 0));
		
		lblWelcome = new JLabel("Welcome to the WoW Randomizer!");
		lblWelcome.setForeground(new Color(255, 255, 0));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Rockwell", Font.PLAIN, 20));
		add(lblWelcome, BorderLayout.NORTH);
		
		lblRace = new JLabel("");
		lblRace.setEnabled(false);
		add(lblRace, BorderLayout.WEST);

		lblClass = new JLabel("");
		lblClass.setEnabled(false);
		add(lblClass, BorderLayout.EAST);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainPanel.class.getResource("/images/wowLogo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogo, BorderLayout.CENTER);
		
		JButton btnGo = new JButton("Find me a class");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame mainWindow = (JFrame) SwingUtilities.getWindowAncestor(lblLogo);
				
				LoadingTask.runWorker(new LoadingTask(MainPanel.this, mainWindow));
				
				//Character character = LoadingTask.get();
			}
		});
		add(btnGo, BorderLayout.SOUTH);
	}

	public JLabel getLblWelcome() {
		return lblWelcome;
	}

	public JLabel getLblRace() {
		return lblRace;
	}

	public JLabel getLblClass() {
		return lblClass;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}
}
