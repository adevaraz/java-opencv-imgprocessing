import java.io.ByteArrayInputStream;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;

public class Morphology {
	protected static Mat erode(Mat src, int structure, int kernelSize) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type());
	    	Mat structureElement = Imgproc.getStructuringElement(structure, new Size(kernelSize, kernelSize));
	    	
	    	Imgproc.erode(src, dest, structureElement);

	    	System.out.println("Image erode..");
	    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat dilate(Mat src, int structure, int kernelSize) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type());
	    	Mat structureElement = Imgproc.getStructuringElement(structure, new Size(kernelSize, kernelSize));
	    	
	    	Imgproc.dilate(src, dest, structureElement);
	    	
	    	System.out.println("Image dilate..");
	    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat opening(Mat src, int structure, int kernelSize) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type());
	    	Mat structureElement = Imgproc.getStructuringElement(structure, new Size(kernelSize, kernelSize));
	    	
	    	Imgproc.morphologyEx(src, dest, Imgproc.MORPH_OPEN, structureElement);

	    	System.out.println("Morphology opening..");
	    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat closing(Mat src, int structure, int kernelSize) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type());
	    	Mat structureElement = Imgproc.getStructuringElement(structure, new Size(kernelSize, kernelSize));
	    	
	    	Imgproc.morphologyEx(src, dest, Imgproc.MORPH_CLOSE, structureElement);
	    	
	    	System.out.println("Morphology closing..");
    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
}
