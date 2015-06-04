package classes;

import java.io.Serializable;

public class Position implements Serializable{

	private int x;
	private int y;

	public Position() {
	}

	public Position(int _x, int _y) {
		x = _x;
		y = _y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "x:" + x + ", y: " + y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position other = (Position) obj;
			return (other.x == x && other.y == y);
		}

		return false;
	}

}
