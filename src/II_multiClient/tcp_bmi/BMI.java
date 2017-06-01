package II_multiClient.tcp_bmi;

import java.io.Serializable;

public class BMI implements Serializable {
    private double weight;
    private double height;

    public BMI( double weight, double height ) {
        setWeight( weight );
        setHeight( height );
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
