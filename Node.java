import java.util.ArrayList;

public class Node {

	private int xpos;
	private int ypos;
	private String[][] map;
	private Node parent;
	private int num;
	private ArrayList<GameObject> gold;

	public Node(int x, int y, Node p, int n, String[][] s, ArrayList<GameObject> garr) {
		xpos = x;
		ypos = y;
		map = s;
		parent = p;
		num = n;
		gold = garr;
	}

	public Node(int x, int y, Node p, int n, String[][] s) {
		xpos = x;
		ypos = y;
		map = s;
		parent = p;
		num = n;
		gold = copy(parent.getGoldArr());
	}

	public static ArrayList<GameObject> copy(ArrayList<GameObject> oldGold) {
		ArrayList<GameObject> newGold = new ArrayList<GameObject>();
		for (GameObject asdf : oldGold) {
			newGold.add(asdf);
		}
		return newGold;
	}

	public void removeGold(int px, int py) {
		for (int j = 0; j < gold.size(); j++) {
			if (px == gold.get(j).getColLocation() && py == gold.get(j).getRowLocation()) {
				// System.out.println("Picking up gold at "+px+",
				// "+py+"***************************************************************\n");
				gold.remove(j);
			}
		}
	}

	public ArrayList<GameObject> getGoldArr() {
		return gold;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}

	public Node getParent() {
		return parent;
	}

	public int getNum() {
		return num;
	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public void remove(int x, int y) {
		map[y][x] = "";
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String[] asdf : map) {
			for (String asd : asdf) {
				sb.append(asd);
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
