import java.io.ByteArrayInputStream;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;

public class EdgeDetection {
	protected static Mat canny(Mat src, int kernelSize, double threshold1, double threshold2) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat graySrc = new Mat(src.rows(), src.cols(), src.type());
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0,0));
	    	Mat edge = new Mat(src.rows(), src.cols(), src.type());
	    	
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, graySrc, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		graySrc = src;
	    	}
	    	
	    	Imgproc.blur(graySrc, edge, new Size(kernelSize, kernelSize));
	    	
	    	Imgproc.Canny(edge, edge, threshold1, threshold2);
	    	
	    	graySrc.copyTo(dest, edge);

	    	System.out.println("Canny edge detection applied..");
    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
}
