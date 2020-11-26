public class Body {
	public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }

    public Body(Body b) {
    	xxPos = b.xxPos;
    	yyPos = b.yyPos;
    	xxVel = b.xxVel;
    	yyVel = b.yyVel;
    	mass = b.mass;
    	imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
    	double dx = xxPos-b.xxPos;
    	double dy = yyPos-b.yyPos;
    	double result = Math.pow(dx*dx+dy*dy, 0.5);
    	return result;
    }

    public double calcForceExertedBy(Body b) {
    	double gfactor = 6.67 * Math.pow(10, -11);
    	double distance_power2 = Math.pow(this.calcDistance(b),2);
    	double force = gfactor * this.mass * b.mass / distance_power2;
    	return force;
    }

    public double calcForceExertedByX(Body b) {
    	double totalforce = this.calcForceExertedBy(b);
    	double distance = this.calcDistance(b);
    	double dx = b.xxPos - this.xxPos;
    	double forceX = totalforce * dx / distance;
    	return forceX;
    }

    public double calcForceExertedByY(Body b) {
    	double totalforce = this.calcForceExertedBy(b);
    	double distance = this.calcDistance(b);
    	double dy = b.yyPos - this.yyPos;
    	double forceY = totalforce * dy / distance;
    	return forceY;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
    	double netforceX = 0.0;
    	for(int i = 0; i < allBodys.length; i++) {
    		if(this.equals(allBodys[i]))
    			continue;
    		else {
     			double fx = this.calcForceExertedByX(allBodys[i]);
        		netforceX = netforceX + fx;
    		}
    	}
    	return netforceX;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
    	double netforceY = 0.0;
    	for(int i = 0; i < allBodys.length; i++) {
    		if(this.equals(allBodys[i]))
    			continue;
    		else {
    			double fy = this.calcForceExertedByY(allBodys[i]);
         		netforceY = netforceY + fy;
    		}
    	}
    	return netforceY;
    }

    public void update(double dt, double fX, double fY) {
    	double ax = fX / this.mass;
    	double ay = fY / this.mass;
    	this.xxVel = this.xxVel + dt * ax;
    	this.yyVel = this.yyVel + dt * ay;
    	this.xxPos = this.xxPos + dt * this.xxVel;
    	this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
    	String picture = "images/" + this.imgFileName;
    	StdDraw.picture(this.xxPos, this.yyPos, picture);
    }
}