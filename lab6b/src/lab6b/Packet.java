package lab6b;

/**
 * <b>Title:</b> Lab 6b:<br>
 * <b>Filename:</b> Packet.java<br>
 * <b>Date Written:</b> November 18, 2018<br>
 * <b>Due Date:</b> November 18, 2018<br>
 *
 * <p>
 * <b>Description:</b><br>
 * packet class which has a position value and a message
 * associated with each instance;
 * comparable by position numbers to create a sequence of
 * messages/strings
 * </p>
 *
 * @author Ung Jae Yun
 */


public class Packet implements Comparable<Packet> {

	private int pos;
	private String msg;

	
	public Packet(int pos, String msg) {
		this.pos = pos;
		this.msg = msg;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public int compareTo(Packet p) {
		
		return this.getPos() - p.getPos();
	}
	
	@Override
	public String toString() {
		return String.format("%d %s", pos, msg);
	}

}
