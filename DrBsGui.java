import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;



public class DrBsGui extends JFrame{
	private static final long serialVersionUID = 1L;

	private Screen screen;

	public DrBsGui() {
		setTitle("Dr Bs Dungeon Crawler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		File fileWithLayout = findRandomLayout();
		screen = new Screen(fileWithLayout);
		this.add(screen);

		pack();

		setVisible(true);
	}

	public DrBsGui(File fileWithLayout) {
		setTitle("Dr Bs Dungeon Crawler - " + fileWithLayout.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screen = new Screen(fileWithLayout);
		this.add(screen);
		
		pack();

		setVisible(true);
	}
	
	public DrBsGui(String mapName, String [] theMap) {
		setTitle(mapName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String [][] theNewMap = new String[theMap.length][theMap[0].length()];
		for(int i = 0; i < theNewMap.length; i++) {
			char[] characters = theMap[i].toCharArray();
			for(int j = 0; j<theNewMap[i].length; j++) {
				theNewMap[i][j] = characters[j]+"";
			}
		}
		
		screen = new Screen(mapName, theNewMap);
		this.add(screen);
		
		pack();

		setVisible(true);
	}

	public DrBsGui(String mapName, String [][] theMap) {
		setTitle(mapName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screen = new Screen(mapName, theMap);
		this.add(screen);
		
		pack();

		setVisible(true);
	}

	@SuppressWarnings("unused")
	private static void loadAllFiles() {
		try {
			File folder = new File("DungeonLayouts");
			String [] files = folder.list();

			for(int i = 0; i < files.length; i++) {
				File f = new File(folder.getCanonicalPath() + "//" + files[i]);
				if(f.isFile()) {
					new DrBsGui(new File(folder.getCanonicalPath() + "//" + files[i]));
					//MyGui g = new MyGui(new File(folder.getCanonicalPath() + "//" + files[i]));
					//g.dispose();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
	}

	private static File findRandomLayout() {
		File f = null;
		try {
			File folder = new File("DungeonLayouts");
			String [] files = folder.list();

			f = new File(folder.getCanonicalPath() + "//" + files[(int)((Math.random()*files.length))]);
			while(f.isDirectory()) {
				f = new File(folder.getCanonicalPath()  + "//" + files[(int)((Math.random()*files.length))]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return f;
	}
	
	
	public static void main(String [] args){
		//loadAllFiles();
		//new MyGui();
		//new MyGui(new File("DungeonLayouts//testMaze.txt"));

		DrBsGui g = new DrBsGui(new File("DungeonLayouts//mediumSafeSearch.txt"));
		mazeTraverser m = new mazeTraverser(g.screen);
		//m.breadthFirst();
		m.depthFirst();
//		new DrBsGui(new File("DungeonLayouts//tinySafeSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//tinySearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//greedySearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//smallSafeSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//smallSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//mediumSafeSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//trickySearch.txt"));

		//These might be too hard
//		new DrBsGui(new File("DungeonLayouts//openSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//boxSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//mediumSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//bigSafeSearch.txt"));
//		new DrBsGui(new File("DungeonLayouts//oddSearch.txt"));
		
//		String[]vacuumWorld =
//			{"wwww",
//			 "wA w",
//			 "wwww"};
//		
//		new MyGui("Vacuum World", vacuumWorld);
	}


}
