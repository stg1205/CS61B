public class NBody {
	
	/* Read the radius in planets.txt */

	public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();

        return in.readDouble();
    } 

    /* Read the data of planets in planets.txt */

    public static Planet[] readPlanets(String file) {
    	In in = new In(file);
    	int num = in.readInt();
    	in.readDouble();
    	Planet[] p = new Planet[num];

    	for (int i = 0; i < num; i += 1) {
    		double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xP, yP, xV, yV, m, img);
    	}
    	return p;
    }


    public static void main(String[] args) {
    	double T = Double.parseDouble(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String filename = args[2]; 
    	/* String filename = "./data/planets.txt"; */
    	double radius = readRadius(filename);
    	Planet[] planets = readPlanets(filename);

    	/* Set scale and draw the background */

    	StdDraw.setScale(-radius, radius);
 		StdDraw.clear();
    	StdDraw.picture(0, 0, "./images/starfield.jpg");

    	/* Draw the planets */

    	for (Planet p: planets) {
			p.draw();
    	}

    	/* Enable buffer */

    	StdDraw.enableDoubleBuffering();

    	/* Start animating */
        int num = planets.length;

    	double time = 0;
    	double[] xForces = new double[num];
    	double[] yForces = new double[num];

    	while (time < T) {
    		for (int i = 0; i < num; i += 1) {
    			xForces[i] = planets[i].calcNetForceExertedByX(planets);
    			yForces[i] = planets[i].calcNetForceExertedByY(planets);
    			planets[i].update(dt, xForces[i], yForces[i]);
    		}
    		StdDraw.picture(0, 0, "./images/starfield.jpg");
    		for (Planet p: planets) {
    			p.draw();
    		}
    		StdDraw.show();
    		StdDraw.pause(10);
    		time += dt;
    	}

    	/* Print out the final data of planets */
    	
    	StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (Planet planet: planets) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  		  planet.xxPos, planet.yyPos, planet.xxVel,
                  		  planet.yyVel, planet.mass, planet.imgFileName);
		}

    }
}