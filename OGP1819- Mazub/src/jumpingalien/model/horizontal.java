package jumpingalien.model;

public interface horizontal {
	public abstract double calculatePositionX(double dt);
	public abstract double calculateHorizontalVelocity(double dt);
	public abstract void updatePositionX(double dt);
	public abstract void updateHorizontalVelocity(double dt);
}
