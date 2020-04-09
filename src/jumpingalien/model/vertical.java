package jumpingalien.model;

public interface vertical {
	public abstract double calculatePositionY(double dt);
	public abstract double calculateVerticalVelocity(double dt);
	public abstract void updatePositionY(double dt);
	public abstract void updateVerticalVelocity(double dt);
}
