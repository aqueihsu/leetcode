package leetcode;

public class RandomPointOnCircle_883 {
    private double radius;
    private double x_center;
    private double y_center;
    
    public RandomPointOnCircle_883(double radius, double x_center, double y_center) {
        super();
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        double angle = 2d * Math.PI * Math.random();
        double len = radius * Math.sqrt(Math.random());
        
        double x = len * Math.cos(angle) + x_center;
        double y = len * Math.sin(angle) + y_center;
        return new double[] {x, y};
    }
}
