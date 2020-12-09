import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Noise {
	protected static Mat saltPepper(Mat src, double intensity) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = src.clone();
	    	
	    	for(int i = 0; i < src.rows(); i++) {
	    		for(int j = 0; j < src.cols(); j++) {
	    			Random random = new Random();
	    			double randVal = random.nextDouble();
	    			
	    			double[] data = dest.get(i, j);
	    			
	    			if(dest.type() != 0) {
	    				if(randVal < intensity/2) {
		    				//pepper
		    				data[0] = 0;
		    				data[1] = 0;
		    				data[2] = 0;
		    			} else if(randVal < intensity){
		    				//salt
		    				data[0] = 255;
		    				data[1] = 255;
		    				data[2] = 255;
		    			}
	    			} else {
	    				if(randVal < intensity/2) {
		    				//pepper
		    				data[0] = 0;
		    			} else if(randVal < intensity){
		    				//salt
		    				data[0] = 255;
		    			}
	    			}
	    			
	    			dest.put(i, j, data);
	    		}
	    	}

	    	System.out.println("Salt and pepper noise applied..");
	    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
}
