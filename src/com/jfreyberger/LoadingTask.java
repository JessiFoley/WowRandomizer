package com.jfreyberger;

import java.awt.Toolkit;
import java.util.concurrent.ExecutionException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class LoadingTask extends SingletonSwingWorker {
	
	JFrame dialogOwner;
	MainPanel mainPanel;
	
	public LoadingTask(MainPanel mainPanel, JFrame dialogOwner) {
		this.dialogOwner = dialogOwner;
		this.mainPanel = mainPanel;
	}
	
    JDialog loadingDialog;
    @Override
    void initAndGo() {
        loadingDialog = new JDialog(dialogOwner);
        JProgressBar jpb = new JProgressBar();
        jpb.setIndeterminate(true);
        loadingDialog.add(jpb);
        loadingDialog.pack();
        loadingDialog.setLocationRelativeTo(dialogOwner);
        loadingDialog.setVisible(true);
        execute(); // This must be put in the initAndGo() method or no-workie
    }
    
    @Override
    protected Object doInBackground() throws Exception {
    	return new Character();
    }
    
    @Override
    protected void done() {
        if(!isCancelled()) {
            try {
                Character character = (Character) get();
                
                mainPanel.lblLogo.setIcon(null);
				
				//change the main picture to reflect the chosen class icon
				switch (character.getCharClass()) {
				case "Death Knight":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Death_Knight_Crest.png")));
					break;
				case "Demon Hunter":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Demon_Hunter_Crest.png")));
					break;
				case "Druid":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Druid_Crest.png")));
					break;
				case "Hunter":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Hunter_Crest.png")));
					break;
				case "Mage":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Mage_Crest.png")));
					break;
				case "Monk":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Pandaren_Crest.png")));
					break;
				case "Paladin":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Paladin_Crest.png")));
					break;
				case "Priest":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Priest_Crest.png")));
					break;
				case "Rogue":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Rogue_Crest.png")));
					break;
				case "Shaman":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Shaman_Crest.png")));
					break;
				case "Warlock":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Warlock_Crest.png")));
					break;
				case "Warrior":
					mainPanel.lblClass.setIcon(new ImageIcon(MainPanel.class.getResource("/images/charClass/Warrior_Crest.png")));
					break;
				default:
					if (character.getFaction().startsWith("A")) {
						mainPanel.lblLogo.setIcon(new ImageIcon(MainPanel.class.getResource("/images/alliance_logo_color.png")));
					} else {
						mainPanel.lblLogo.setIcon(new ImageIcon(MainPanel.class.getResource("/images/horde_logo_color.png")));
					}
					mainPanel.lblLogo.setEnabled(true);
					break;
				}
				
				switch(character.getRace()) {
				case "Dark Iron Dwarf":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Dark_Iron_crest.png")));
					break;
				case "Draenei":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Draenei_Crest.png")));
					break;
				case "Dwarf":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Dwarf_Crest.png")));
					break;
				case "Gnome":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Gnome_Crest.png")));
					break;
				case "Human":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Human_Crest.png")));
					break;
				case "Kul Tiran":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Kul_Tiran_crest.png")));
					break;
				case "Lightforged Draenei":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Lightforged_Draenei_Crest.png")));
					break;
				case "Mechagnome":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Mechagnome_crest.png")));
					break;
				case "Night Elf":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Night_Elf_Crest.png")));
					break;
				case "Alliance Pandaren":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Alliance_Crest.png")));
					break;
				case "Void Elf":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Void_Elf_Crest.png")));
					break;
				case "Worgen":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Worgen-Icon.png")));
					break;
				case "Undead":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Forsaken_Crest.png")));
					break;
				case "Goblin":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Goblin-Icon.png")));
					break;
				case "Highmountain Tauren":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Highmountain_Tauren_Crest.png")));
					break;
				case "Blood Elf":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Icon_of_Blood.png")));
					break;
				case "Mag\'har Orc":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Mag\'har_crest.png")));
					break;
				case "Nightborne":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Nightborne_Crest.png")));
					break;
				case "Orc":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Orc_Crest.png")));
					break;
				case "Horde Pandaren":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Horde_Crest.png")));
					break;
				case "Tauren":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Tauren_Crest.png")));
					break;
				case "Troll":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Troll_Crest.png")));
					break;
				case "Vulpera":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Vulpera_crest.png")));
					break;
				case "Zandalari Troll":
					mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Zandalari_crest.png")));
					break;
				default:
					if (character.getFaction().startsWith("A")) {
						mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/alliance/Alliance_Crest.png")));
					} else {
						mainPanel.lblRace.setIcon(new ImageIcon(MainPanel.class.getResource("/images/race/horde/Horde_Crest.png")));
					}
					break;
				}
				
				//set race and class icons to enabled
				mainPanel.lblRace.setEnabled(true);
				mainPanel.lblClass.setEnabled(true);
				
				//change the window icon to reflect the chosen faction
				if (character.getFaction().equals("Alliance"))
				{
					dialogOwner.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/alliance_logo_color.png")));
				} else {
					dialogOwner.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/horde_logo_color.png")));
				}
				
				//finally change the text to inform the user which race/class combination was chosen
				mainPanel.lblWelcome.setText("You should play a: " + character.getRace() + " " + character.getSpec() + " " + character.getCharClass());
            } catch (ExecutionException | InterruptedException e) {
                loadingDialog.dispose();
                e.printStackTrace();
                return;
            }
            loadingDialog.dispose();
        } else
            loadingDialog.dispose();
    }

}
