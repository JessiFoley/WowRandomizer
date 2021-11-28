package com.jfreyberger;

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
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class MainPanel extends BackgroundPanel {

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
		
		JLabel lblWelcome = new JLabel("Welcome to the WoW Randomizer!");
		lblWelcome.setForeground(new Color(255, 255, 0));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Rockwell", Font.PLAIN, 20));
		add(lblWelcome, BorderLayout.NORTH);
		
		JLabel lblRace = new JLabel("");
		lblRace.setEnabled(false);
		add(lblRace, BorderLayout.WEST);

		JLabel lblClass = new JLabel("");
		lblClass.setEnabled(false);
		add(lblClass, BorderLayout.EAST);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(MainPanel.class.getResource("/images/wowLogo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogo, BorderLayout.CENTER);
		
		JButton btnGo = new JButton("Find me a class");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Character character = new Character();
				
				//Disable the logo
				lblLogo.setIcon(null);
				
				//change the main picture to reflect the chosen class icon
				switch (character.getCharClass()) {
				case "Death Knight":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Death_Knight_Crest.png")));
					break;
				case "Demon Hunter":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Demon_Hunter_Crest.png")));
					break;
				case "Druid":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Druid_Crest.png")));
					break;
				case "Hunter":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Hunter_Crest.png")));
					break;
				case "Mage":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Mage_Crest.png")));
					break;
				case "Monk":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Pandaren_Crest.png")));
					break;
				case "Paladin":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Paladin_Crest.png")));
					break;
				case "Priest":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Priest_Crest.png")));
					break;
				case "Rogue":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Rogue_Crest.png")));
					break;
				case "Shaman":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Shaman_Crest.png")));
					break;
				case "Warlock":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Warlock_Crest.png")));
					break;
				case "Warrior":
					lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Warrior_Crest.png")));
					break;
				default:
					if (character.getFaction().startsWith("A")) {
						lblLogo.setIcon(new ImageIcon(MainPanel.class.getResource("/images/alliance_logo_color.png")));
					} else {
						lblLogo.setIcon(new ImageIcon(MainPanel.class.getResource("/images/horde_logo_color.png")));
					}
					lblLogo.setEnabled(true);
					break;
				}
				
				switch(character.getRace()) {
				case "Dark Iron Dwarf":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Dark_Iron_crest.png")));
					break;
				case "Draenei":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Draenei_Crest.png")));
					break;
				case "Dwarf":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Dwarf_Crest.png")));
					break;
				case "Gnome":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Gnome_Crest.png")));
					break;
				case "Human":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Human_Crest.png")));
					break;
				case "Kul Tiran":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Kul_Tiran_crest.png")));
					break;
				case "Lightforged Draenei":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Lightforged_Draenei_Crest.png")));
					break;
				case "Mechagnome":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Mechagnome_crest.png")));
					break;
				case "Night Elf":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Night_Elf_Crest.png")));
					break;
				case "Alliance Pandaren":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Alliance_Crest.png")));
					break;
				case "Void Elf":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Void_Elf_Crest.png")));
					break;
				case "Worgen":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Worgen-Icon.png")));
					break;
				case "Undead":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Forsaken_Crest.png")));
					break;
				case "Goblin":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Goblin-Icon.png")));
					break;
				case "Highmountain Tauren":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Highmountain_Tauren_Crest.png")));
					break;
				case "Blood Elf":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Icon_of_Blood.png")));
					break;
				case "Mag\'har Orc":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Mag\'har_crest.png")));
					break;
				case "Nightborne":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Nightborne_Crest.png")));
					break;
				case "Orc":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Orc_Crest.png")));
					break;
				case "Horde Pandaren":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Horde_Crest.png")));
					break;
				case "Tauren":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Tauren_Crest.png")));
					break;
				case "Troll":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Troll_Crest.png")));
					break;
				case "Vulpera":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Vulpera_crest.png")));
					break;
				case "Zandalari Troll":
					lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Zandalari_crest.png")));
					break;
				default:
					if (character.getFaction().startsWith("A")) {
						lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Alliance_Crest.png")));
					} else {
						lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Horde_Crest.png")));
					}
					break;
				}
				
				//set race and class icons to enabled
				lblRace.setEnabled(true);
				lblClass.setEnabled(true);
				
				//change the window icon to reflect the chosen faction
				JFrame mainWindow = (JFrame) SwingUtilities.getWindowAncestor(lblLogo);
				
				if (character.getFaction().equals("Alliance"))
				{
					mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/alliance_logo_color.png")));
				} else {
					mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/horde_logo_color.png")));
				}
				
				//finally change the text to inform the user which race/class combination was chosen
				lblWelcome.setText("You should play a: " + character.getRace() + " " + character.getSpec() + " " + character.getCharClass());
			}
		});
		add(btnGo, BorderLayout.SOUTH);
	}

}
