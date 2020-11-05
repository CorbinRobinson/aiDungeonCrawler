import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class GameObject {
	private double colOnGraphics; //x
	private double rowOnGraphics; //y

	private BufferedImage image;
	private int imageWidth;
	private int imageHeight;

	private int tileSize;

	public AgentBrain brain;

	public GameObject(double col, double row, BufferedImage i, int tileSize){
		this(col,row,i,i.getWidth(), i.getHeight(), tileSize);
	}

	public GameObject(double col, double row, BufferedImage i, int imageWidth, int imageHeight, int tileSize){
		setColLocationOnGraphics(col);
		setRowLocationOnGraphics(row);
		setImage(i);
		setImageWidth(imageWidth);
		setImageHeight(imageHeight);

		brain = new AgentBrain();

		this.tileSize = tileSize;
	}

	public void setImageWidthAndHeight(int imageWidth, int imageHeight){
		setImageWidth(imageWidth);
		setImageHeight(imageHeight);
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public double getColLocationOnGraphics() {
		return colOnGraphics;
	}

	public int getColLocation() {
		return (int)(colOnGraphics/tileSize);
	}

	public void setColLocationOnGraphics(double col) {
		this.colOnGraphics = col;
	}

	public void setColLocation(int col) {
		this.colOnGraphics = col*tileSize;
	}

	public void incrementColOnGraphics(double col){
		this.colOnGraphics += col;
	}

	public void incrementCol(double col){
		this.colOnGraphics += col+tileSize;
	}

	public double getRowLocationOnGraphics() {
		return rowOnGraphics;
	}

	public int getRowLocation() {
		return (int)(rowOnGraphics/tileSize);
	}

	public void setRowLocationOnGraphics(double row) {
		this.rowOnGraphics = row;
	}

	public void setRowLocation(int row) {
		this.rowOnGraphics = row*tileSize;
	}

	public void incrementRowOnGraphics(double row){
		this.rowOnGraphics += row;
	}

	public void incrementRow(double row){
		this.rowOnGraphics += row+tileSize;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void drawTheImage(Graphics g){
		if(image!= null){
			g.drawImage(image, (int)colOnGraphics,(int)rowOnGraphics, imageWidth, imageHeight, null);
		}
	}

	public void setNextMove(KeyEvent k, Screen s) {
		int keyEventCode = k.getKeyCode();
		//		System.out.println("Key Event " + keyEventCode);

		if(keyEventCode == KeyEvent.VK_RIGHT || keyEventCode == KeyEvent.VK_D) {
			brain.addNextMove(AgentAction.moveRight);
		}
		else if(keyEventCode == KeyEvent.VK_LEFT || keyEventCode == KeyEvent.VK_A) {
			brain.addNextMove(AgentAction.moveLeft);
		}
		else if(keyEventCode == KeyEvent.VK_UP || keyEventCode == KeyEvent.VK_W) {
			brain.addNextMove(AgentAction.moveUp);
		}
		else if(keyEventCode == KeyEvent.VK_DOWN || keyEventCode == KeyEvent.VK_S) {
			brain.addNextMove(AgentAction.moveDown);
		}
		else if (keyEventCode == KeyEvent.VK_V) {//Player Declares Victory
			brain.addNextMove(AgentAction.declareVictory);
		}
		else if(keyEventCode == KeyEvent.VK_SPACE) {
			//pickup gold/elixer
			brain.addNextMove(AgentAction.pickupSomething);
		}
		else {
			System.out.println("Unknown key event " + keyEventCode);
		}

	}

	public AgentAction getMove() {
		return brain.getNextMove();
	}

	public void search(String [][] theMap) {
		brain.search(theMap);
	}

}
