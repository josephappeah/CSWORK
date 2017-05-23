import java.lang.Math;

public class gInt {
	
	public int r = 0;
	public int i = 0;


    public  gInt(int real, int imag) {
        r = real;
		i = imag;
    }
	
    public  gInt(int real) {
        r = real;
    }
	
    public int real() {
		return r;
    }
	
    public int imag() {
        return i; 
    }
	

    /* Returns the sum of r and i */
    public  gInt add( gInt rhs ){
		return new gInt(rhs.r + r, rhs.i + i);
    }

    /* Returns the difference of r and i */
    public  gInt multiply( gInt rhs ) {
		return new gInt((r*rhs.r) - (i*rhs.i), (r*rhs.i) + (i*rhs.r));
    }
	
	public float norm(){
		return (float)Math.sqrt((r*r) + (i*i));
	}

}
