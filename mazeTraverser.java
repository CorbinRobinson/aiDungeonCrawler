import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class mazeTraverser {

	Screen s;
	HashMap<String, Boolean> bStates;
	HashMap<String, Boolean> dStates;
	ArrayList<Node> queue;
	Stack<Node> stack;
	int goldLeft;
	int bNodeCount;
	int dPathCost;
	int bNodesExpanded;
	int dNodesExpanded;
	boolean dFound;

	public mazeTraverser(Screen sc) {
		s = sc;
		bStates = new HashMap<String, Boolean>();
		dStates = new HashMap<String, Boolean>();
		queue = new ArrayList<Node>();
		stack = new Stack<Node>();
		goldLeft = sc.startingGold;
		bNodeCount = 0;
		dPathCost = 0;
		bNodesExpanded = 0;
		dNodesExpanded = 0;
		dFound = false;
	}

	public static Tuple<Integer, Integer> recDepth(Node n, mazeTraverser m, ArrayList<Tuple<Integer, Integer>> path) {
		int px = m.stack.peek().getXpos();
		int py = m.stack.peek().getYpos();
		Node currentNode = m.stack.peek();

		String[][] newMap = currentNode.getMap();

		currentNode.removeGold(px, py);
		if (currentNode.getGoldArr().size() == 0) {

			m.dFound = true;
			return new Tuple<Integer, Integer>(n.getXpos(), n.getYpos());
		}
		m.dNodesExpanded++;
		if (!m.dFound && m.s.isValidMove(py - 1, px) && dHash(px, py, px, py - 1, newMap, m, currentNode)) {

			path.add(recDepth(m.stack.peek(), m, path));

		}

		m.dNodesExpanded++;
		if (!m.dFound && m.s.isValidMove(py, px + 1) && dHash(px, py, px + 1, py, newMap, m, currentNode)) {

			path.add(recDepth(m.stack.peek(), m, path));

		}

		m.dNodesExpanded++;
		if (!m.dFound && m.s.isValidMove(py + 1, px) && dHash(px, py, px, py + 1, newMap, m, currentNode)) {

			path.add(recDepth(m.stack.peek(), m, path));

		}
		m.dNodesExpanded++;
		if (!m.dFound && m.s.isValidMove(py, px - 1) && dHash(px, py, px - 1, py, newMap, m, currentNode)) {

			path.add(recDepth(m.stack.peek(), m, path));
		}

		return new Tuple<Integer, Integer>(n.getXpos(), n.getYpos());
	}

	public void depthFirst() {
		int px = s.player.getColLocation();
		int py = s.player.getRowLocation();

		Node n0 = new Node(px, py, null, dPathCost, s.map, s.gold);
		Node n1 = new Node(px, py, n0, ++dPathCost, s.map);
		dStates.put(toString(n1.getMap()), true);
		stack.push(n1);
		// Node n;
		ArrayList<Tuple<Integer, Integer>> path = new ArrayList<Tuple<Integer, Integer>>();
		path.add(recDepth(stack.peek(), this, path));
		path.add(new Tuple<Integer, Integer>(s.player.getColLocation(), s.player.getRowLocation()));
		addMoves(path, this.s);
		System.out.println("Depth First path found (x,y): ");
		for (int i = path.size() - 1; i >= 0; i--)
			System.out.print(path.get(i));
		System.out.println();

		System.out.println("Depth First found all gold after dPathCost: " + dPathCost);
		System.out.println("dNodesExpanded : " + dNodesExpanded);

	}

	public void breadthFirst() {
		int px = s.player.getColLocation();
		int py = s.player.getRowLocation();

		Node n0 = new Node(px, py, null, bNodeCount, s.map, s.gold);
		Node n1 = new Node(px, py, n0, ++bNodeCount, s.map);
		bStates.put(toString(n1.getMap()), true);
		queue.add(n1);
		Node n;

		for (int i = 0; true; i++) {

			px = queue.get(i).getXpos();
			py = queue.get(i).getYpos();

			String[][] newMap = queue.get(i).getMap();

			queue.get(i).removeGold(px, py);
			if (queue.get(i).getGoldArr().size() == 0) {
				n = queue.get(i);
				break;
			}
			bNodesExpanded++;
			if (s.isValidMove(py - 1, px) && bHash(px, py, px, py - 1, newMap, this, queue.get(i))) {

			}
			bNodesExpanded++;
			if (s.isValidMove(py, px + 1) && bHash(px, py, px + 1, py, newMap, this, queue.get(i))) {

			}

			bNodesExpanded++;
			if (s.isValidMove(py + 1, px) && bHash(px, py, px, py + 1, newMap, this, queue.get(i))) {

			}
			bNodesExpanded++;
			if (s.isValidMove(py, px - 1) && bHash(px, py, px - 1, py, newMap, this, queue.get(i))) {

			}

		}
		int bPathCost = 0;
		Stack<Integer> stk = new Stack<Integer>();
		while (n.getNum() > 0) {
			bPathCost++;
			stk.push(n.getXpos());
			stk.push(n.getYpos());
			n = n.getParent();
		}
		
		ArrayList<Tuple<Integer, Integer>> path = toTuple(stk);
		path.add(new Tuple<Integer, Integer>(s.player.getColLocation(), s.player.getRowLocation()));
		addMoves(path, this.s);
		
		System.out.println("Breadth First path (x,y): ");
		PrintStack(stk);
		System.out.println();
		System.out.println("Breadth First found all gold after bPathCost: " + bPathCost);
		System.out.println("bNodesExpanded : " + bNodesExpanded);

	}

	public static void addMoves(ArrayList<Tuple<Integer, Integer>> path, Screen sc) {
		Tuple<Integer, Integer> pos = path.get(path.size()-1);
		for (int i = path.size() - 1; i >= 0; i--) {
			if(pos.getX() > path.get(i).getX()) {
				sc.player.brain.addNextMove(AgentAction.moveLeft);
				System.out.println("added left");
			} else if(pos.getX() < path.get(i).getX()) {
				sc.player.brain.addNextMove(AgentAction.moveRight);
				System.out.println("added right");
			}else if(pos.getY() > path.get(i).getY()) {
				sc.player.brain.addNextMove(AgentAction.moveUp);
				System.out.println("added up");
			}else if(pos.getY() < path.get(i).getY()) {
				sc.player.brain.addNextMove(AgentAction.moveDown);
				System.out.println("added down");
			}
			for(int j=0; j<sc.gold.size(); j++) {
				if(path.get(i).getX() == sc.gold.get(j).getColLocation() && path.get(i).getY() == sc.gold.get(j).getRowLocation()) {
					sc.player.brain.addNextMove(AgentAction.pickupSomething);
					System.out.println("pickup gold");
				}
			}
			pos = path.get(i);
		}
		sc.player.brain.addNextMove(AgentAction.declareVictory);
	}

	public static ArrayList<Tuple<Integer, Integer>> toTuple(Stack<Integer> s) {
		ArrayList<Tuple<Integer, Integer>> a = new ArrayList<Tuple<Integer, Integer>>();
		for(int i=0; i<s.size(); i+=2) {
			a.add(new Tuple<Integer, Integer>(s.get(i), s.get(i+1)));
		}
		return a;
	}

	// this is a stolen method from
	// https://www.geeksforgeeks.org/print-stack-elements-from-top-to-bottom/
	public static void PrintStack(Stack<Integer> s) {

		// If stack is empty
		if (s.empty())
			return;

		// Extract top of the stack
		int y = (int) s.peek();

		// Pop the top element
		s.pop();

		int x = (int) s.peek();

		s.pop();

		// Print the current top
		// of the stack i.e., x
		System.out.print("(" + x + "," + y + ")");

		// Proceed to print
		// remaining stack
		PrintStack(s);

		// Push the element back
		s.push(y);
		s.push(x);
	}

	public static boolean bHash(int px, int py, int npx, int npy, String[][] s, mazeTraverser m, Node n) {
		// px, py = current positions
		// npx, npy = new positions
		String asdf = s[npy][npx];
		s[py][px] = " ";
		s[npy][npx] = "S";
		if (m.bStates.containsKey(toString(s))) {
			s[py][px] = "S";
			s[npy][npx] = asdf;
			return false;
		}
		m.bStates.put(toString(s), true);
		m.queue.add(new Node(npx, npy, n, ++m.bNodeCount, copy(s))); // copy is necesary because of String[][] reference
																		// garbage
		// revert map back
		s[py][px] = "S";
		s[npy][npx] = asdf;
		return true;
	}

	public static boolean dHash(int px, int py, int npx, int npy, String[][] s, mazeTraverser m, Node n) {
		// px, py = current positions
		// npx, npy = new positions
		String asdf = s[npy][npx];
		s[py][px] = " ";
		s[npy][npx] = "S";
		if (m.dStates.containsKey(toString(s))) {
			s[py][px] = "S";
			s[npy][npx] = asdf;
			return false;
		}
		m.dStates.put(toString(s), true);
		m.stack.push(new Node(npx, npy, n, ++m.dPathCost, copy(s))); // copy is necesary because of String[][] reference
																		// garbage
		// revert map back
		s[py][px] = "S";
		s[npy][npx] = asdf;
		return true;
	}

	public static String[][] copy(String[][] s) {
		String[][] asdf = new String[s.length][s[0].length];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				asdf[i][j] = s[i][j];
			}
		}
		return asdf;
	}

	public static String toString(String[][] s) {
		StringBuilder sb = new StringBuilder();
		for (String[] asdf : s) {
			for (String asd : asdf) {
				sb.append(asd);
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
