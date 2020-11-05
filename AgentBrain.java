import java.util.LinkedList;
import java.util.Queue;

public class AgentBrain {

	public Queue<AgentAction> nextMoves;

	public AgentBrain() {
		nextMoves = new LinkedList<AgentAction>();
	}
	
	public void addNextMove(AgentAction nextMove) {
		this.nextMoves.add(nextMove);
	}

	public void clearAllMoves() {
		nextMoves = new LinkedList<AgentAction>();
	}

	public AgentAction getNextMove() {
		if(nextMoves.isEmpty()) {
			return AgentAction.doNothing;
		}
		return nextMoves.remove();
	}
	
	
	public void search(String [][] theMap) {
		
		//TODO - Figure out if you need more parameters and then add all the moves
		
		//Possible things to add to your moves
//		nextMoves.add(AgentAction.doNothing);
//		nextMoves.add(AgentAction.doNothing);
//		nextMoves.add(AgentAction.doNothing);
//		nextMoves.add(AgentAction.doNothing);
//		nextMoves.add(AgentAction.doNothing);
//		nextMoves.add(AgentAction.moveRight);
//		nextMoves.add(AgentAction.moveDown);
//		nextMoves.add(AgentAction.moveLeft);
//		nextMoves.add(AgentAction.moveUp);
//		nextMoves.add(AgentAction.pickupSomething);
//		nextMoves.add(AgentAction.doNothing);
//		nextMoves.add(AgentAction.declareVictory);
		
		
		
	
		
	}
	
	
}
