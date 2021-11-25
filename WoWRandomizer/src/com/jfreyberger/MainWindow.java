package com.jfreyberger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainWindow {

	private JFrame frmWowRandomizer;

	/**
	 9/* Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmWowRandomizer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWowRandomizer = new JFrame();
		frmWowRandomizer.setTitle("WoW Randomizer");
		frmWowRandomizer.getContentPane().setBackground(Color.DARK_GRAY);
		
		JPanel mainView = new MainPanel();
		mainView.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmWowRandomizer.getContentPane().add(mainView, BorderLayout.CENTER);

		
		
		
		frmWowRandomizer.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/wowIcon.png")));
		frmWowRandomizer.setBounds(100, 100, 800, 641);
		frmWowRandomizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmWowRandomizer.setJMenuBar(menuBar);
		
		JMenu mnFilters = new JMenu("Filters");
		menuBar.add(mnFilters);
		
		JMenu mnRole = new JMenu("Role");
		mnFilters.add(mnRole);
		
		JCheckBoxMenuItem chckbxmntmTank = new JCheckBoxMenuItem("Tank");
		chckbxmntmTank.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setRoleTank(chckbxmntmTank.isSelected());
			}
		});
		chckbxmntmTank.setSelected(true);
		mnRole.add(chckbxmntmTank);
		
		JCheckBoxMenuItem chckbxmntmHealer = new JCheckBoxMenuItem("Healer");
		chckbxmntmHealer.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setRoleHealer(chckbxmntmHealer.isSelected());
			}
		});
		chckbxmntmHealer.setSelected(true);
		mnRole.add(chckbxmntmHealer);
		
		JCheckBoxMenuItem chckbxmntmDPS = new JCheckBoxMenuItem("DPS");
		chckbxmntmDPS.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setRoleDPS(chckbxmntmDPS.isSelected());
			}
		});
		chckbxmntmDPS.setSelected(true);
		mnRole.add(chckbxmntmDPS);
		
		JMenu mnFaction = new JMenu("Faction");
		mnFilters.add(mnFaction);
		
		JCheckBoxMenuItem chckbxmntmAlliance = new JCheckBoxMenuItem("Alliance");
		chckbxmntmAlliance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setFacAlliance(chckbxmntmAlliance.isSelected());
			}
		});
		chckbxmntmAlliance.setSelected(true);
		mnFaction.add(chckbxmntmAlliance);
		
		JCheckBoxMenuItem chckbxmntmHorde = new JCheckBoxMenuItem("Horde");
		chckbxmntmHorde.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setFacHorde(chckbxmntmHorde.isSelected());
			}
		});
		chckbxmntmHorde.setSelected(true);
		mnFaction.add(chckbxmntmHorde);
		
		JMenu mnRace = new JMenu("Allied Race");
		mnFilters.add(mnRace);
		
		JCheckBoxMenuItem chckbxmntmPreferAR = new JCheckBoxMenuItem("Prefer Allied Race");
		chckbxmntmPreferAR.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setPreferAR(chckbxmntmPreferAR.isSelected());
			}
		});
		mnRace.add(chckbxmntmPreferAR);
		
		JMenu mnARFacA = new JMenu("Alliance");
		mnRace.add(mnARFacA);
		
		JCheckBoxMenuItem chckbxmntmDarkIronDwarves = new JCheckBoxMenuItem("Dark Iron Dwarves");
		chckbxmntmDarkIronDwarves.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARDIDwarves(chckbxmntmDarkIronDwarves.isSelected());
			}
		});
		mnARFacA.add(chckbxmntmDarkIronDwarves);
		chckbxmntmDarkIronDwarves.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmKulTirasHuman = new JCheckBoxMenuItem("Kul Tiras Humans");
		chckbxmntmKulTirasHuman.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARKTHumans(chckbxmntmKulTirasHuman.isSelected());
			}
		});
		mnARFacA.add(chckbxmntmKulTirasHuman);
		chckbxmntmKulTirasHuman.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmLightforgedDraenei = new JCheckBoxMenuItem("Lightforged Draenei");
		chckbxmntmLightforgedDraenei.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARLDraenei(chckbxmntmLightforgedDraenei.isSelected());
			}
		});
		mnARFacA.add(chckbxmntmLightforgedDraenei);
		chckbxmntmLightforgedDraenei.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmMechagnome = new JCheckBoxMenuItem("Mechagnomes");
		chckbxmntmMechagnome.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARMechagnomes(chckbxmntmMechagnome.isSelected());
			}
		});
		mnARFacA.add(chckbxmntmMechagnome);
		chckbxmntmMechagnome.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmVoidElves = new JCheckBoxMenuItem("Void Elves");
		chckbxmntmVoidElves.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARVElves(chckbxmntmVoidElves.isSelected());
			}
		});
		mnARFacA.add(chckbxmntmVoidElves);
		chckbxmntmVoidElves.setSelected(true);
		
		JMenu mnARFacH = new JMenu("Horde");
		mnRace.add(mnARFacH);
		
		JCheckBoxMenuItem chckbxmntmHighmountainTauren = new JCheckBoxMenuItem("Highmountain Tauren");
		chckbxmntmHighmountainTauren.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARHTauren(chckbxmntmHighmountainTauren.isSelected());
			}
		});
		mnARFacH.add(chckbxmntmHighmountainTauren);
		chckbxmntmHighmountainTauren.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmMagharOrcs = new JCheckBoxMenuItem("Mag'har Orcs");
		chckbxmntmMagharOrcs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARMOrcs(chckbxmntmMagharOrcs.isSelected());
			}
		});
		mnARFacH.add(chckbxmntmMagharOrcs);
		chckbxmntmMagharOrcs.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmNightborne = new JCheckBoxMenuItem("Nightborne");
		chckbxmntmNightborne.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARNightborne(chckbxmntmNightborne.isSelected());
			}
		});
		mnARFacH.add(chckbxmntmNightborne);
		chckbxmntmNightborne.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmVulpera = new JCheckBoxMenuItem("Vulpera");
		chckbxmntmVulpera.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARVulpera(chckbxmntmVulpera.isSelected());
			}
		});
		mnARFacH.add(chckbxmntmVulpera);
		chckbxmntmVulpera.setSelected(true);
		
		JCheckBoxMenuItem chckbxmntmZandalariTrolls = new JCheckBoxMenuItem("Zandalari Trolls");
		chckbxmntmZandalariTrolls.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Filter.setARZTrolls(chckbxmntmZandalariTrolls.isSelected());
			}
		});
		mnARFacH.add(chckbxmntmZandalariTrolls);
		chckbxmntmZandalariTrolls.setSelected(true);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
