package models;

public abstract class Car {
    private Position position;
    private int speed;
    private Direction direction;

    public Car(Position position, int speed, Direction direction) {
        this.position=position;
        this.speed=speed;
        this.direction=direction;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
