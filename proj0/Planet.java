public class Planet {
	/* Planet properties */
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/* First construtor */

	public Planet(double xP, double yP, double xV, 
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/* Second construtor (copy) */

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/* Calculate the distance between two planets */
	
	public double calcDistance(Planet p) {
		double dis;
		dis = Math.sqrt(Math.pow((xxPos - p.xxPos), 2)
			+ Math.pow((yyPos - p.yyPos), 2));
		return dis;
	}

	/* Calculate the force exerted on this planet by the given planet */

	public double calcForceExertedBy(Planet p) {
		double g = 6.67e-11;
		double force;
		force = (g * mass * p.mass) / Math.pow(calcDistance(p), 2);

		return force;
	}

	/* Calculate the force exerted in X and Y directions */

	public double calcForceExertedByX(Planet p) {
		double x = p.xxPos - xxPos;
		return calcForceExertedBy(p) * x / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double y = p.yyPos - yyPos;
		return calcForceExertedBy(p) * y / calcDistance(p);
	}

	/* Take in an array of Planets and calculate the net X and net Y force
	   exerted by all planets in that array */
	
	public double calcNetForceExertedByX(Planet[] p) {
		double xForce = 0;
		for (Planet every_p: p) {
			if (!this.equals(every_p)) {
				xForce += calcForceExertedByX(every_p);
			}
		}
		return xForce;
	}

	public double calcNetForceExertedByY(Planet[] p) {
		double yForce = 0;
		for (Planet every_p: p) {
			if (!this.equals(every_p)) {
				yForce += calcForceExertedByY(every_p);
			}
		}
		return yForce;
	}

	/* determines how much the forces exerted on the 
	planet will cause that planet to accelerate, and 
	the resulting change in the planet’s velocity 
	and position in a small period of time dt. */

	public void update(double dt, double xForce, double yForce) {
		double xAcc = xForce / mass;
		double yAcc = yForce / mass;
		xxVel += dt * xAcc;
		yyVel += dt * yAcc;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}

}
