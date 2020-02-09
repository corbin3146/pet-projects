package fractals;

public class ComplexNumber {
	double x, i;
	
	public double getX() {
		return x;
	}
	
	public double getI() {
		return i;
	}

	public ComplexNumber(double x, double i) {
		this.x = x;
		this.i = i;
	}

	public void print() {
		System.out.println(x + " + " + i + " i");
	}

	public void square() {// use foil (x+i)(x+i)
		double a, b, c;
		// first
		a = x * x;
		// outer, inner
		b = 2 * (x * i);
		// last ... becomes -x
		c = i * i;

		// update the complex number
		x = a - c;
		i = b;
	}
	public void cube() {// use foil (x+i)(x+i)(x+i)
		double a, b, c, d;
		a = x*x*x;
		b = 3*x*i*i;
		c = 3*x*x*i;
		d = i*i*i;
		// update the complex number
		x = a-b;
		i = c-d;
	}

	public void add(double x, double i) {
		this.x += x;
		this.i += i;
	}
	public void add(ComplexNumber comp) {
		this.x += comp.getX();
		this.i += comp.getI();
	}
	public double pithag() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(i, 2));
	}
	
	public void pow(int pow) {
		if(pow ==1) {
			return;
		}else if(pow ==2) {
			square();
			return;
		}
		double c=this.x;
		double d = this.i;
		
		for(int i=1;i<pow;i++) {
			double a = this.x;
			double b = this.i;
			this.x = ((a * c)-(b * d));
			this.i = ((a*d)+(c*b));
		}
		
	}
}
