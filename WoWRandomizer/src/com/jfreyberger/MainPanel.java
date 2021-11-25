package com.jfreyberger;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jfreyberger.CharRandomizer;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcome = new JLabel("Welcome to the WoW Randomizer!");
		lblWelcome.setForeground(new Color(255, 255, 0));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Rockwell", Font.PLAIN, 20));
		add(lblWelcome);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/wowLogo.png")));
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblIcon, BorderLayout.NORTH);
		
		JButton btnGo = new JButton("Find me a class");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Character character = new Character();
				
				//change the main picture to reflect the chosen class icon
				switch (character.getCharClass()) {
				case "Death Knight":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Death_Knight_Crest.png")));
					break;
				case "Demon Hunter":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Demon_Hunter_Crest.png")));
					break;
				case "Druid":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Druid_Crest.png")));
					break;
				case "Hunter":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Hunter_Crest.png")));
					break;
				case "Mage":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Mage_Crest.png")));
					break;
				case "Monk":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Pandaren_Crest.png")));
					break;
				case "Paladin":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Paladin_Crest.png")));
					break;
				case "Priest":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Priest_Crest.png")));
					break;
				case "Rogue":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Rogue_Crest.png")));
					break;
				case "Shaman":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Shaman_Crest.png")));
					break;
				case "Warlock":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Warlock_Crest.png")));
					break;
				case "Warrior":
					lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/Warrior_Crest.png")));
					break;
				default:
					if (character.getFaction().startsWith("A")) {
						lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/alliance_logo_color.png")));
					} else {
						lblIcon.setIcon(new ImageIcon(MainPanel.class.getResource("/images/horde_logo_color.png")));
					}
					break;
				}
				
				//change the window icon to reflect the chosen faction
				JFrame mainWindow = (JFrame) SwingUtilities.getWindowAncestor(lblIcon);
				
				if (character.getFaction().equals("Alliance"))
				{
					mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/alliance_logo_color.png")));
				} else {
					mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/horde_logo_color.png")));
				}
				
				//finally change the text to inform the user which race/class combination was chosen
				lblWelcome.setText("You should play a: " + character.getRace() + " " + character.getCharClass());
			}
		});
		add(btnGo, BorderLayout.SOUTH);
		
	}

}
