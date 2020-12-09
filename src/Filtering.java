import java.io.ByteArrayInputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.scene.image.Image;

public class Filtering {
	protected static Mat convolution(Mat src, int kernelSize) {
		try {
    		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type());
	    	
	    	Mat kernel = Mat.eye(kernelSize, kernelSize, CvType.CV_32F);
	    	
	    	Imgproc.filter2D(src, dest, -1, kernel);

			System.out.println("Image convolved..");
			
			return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		return null;
    	}
	}
	
	protected static Mat averaging(Mat src, int kernelSize) {
    	try {
    		Mat dest = new Mat();
        	Point anchor = new Point(-1, -1);
	    	Imgproc.blur(src, dest, new Size(kernelSize, kernelSize), anchor, Core.BORDER_DEFAULT);

			System.out.println("Image averaged..");
			
			return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat gaussianBlur(Mat src, int kernelSize) {
    	try {
    		Mat dest = new Mat();
    		
	    	Imgproc.GaussianBlur(src, dest, new Size(kernelSize, kernelSize), 0, 0, Core.BORDER_DEFAULT);
	    	
			System.out.println("Image blurred..");
			
			return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat medianBlur(Mat src, int kernelSize) {
		try {
			Mat dest = new Mat();
			
	    	Imgproc.medianBlur(src, dest, kernelSize);

			System.out.println("Image blurred..");
			
			return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat bilateral(Mat src, int diameter, double sigmaColor, double sigmaSpace) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat();
	    	
	    	if(diameter <= 9) {
	    		Imgproc.bilateralFilter(src, dest, diameter, sigmaColor, sigmaSpace, Core.BORDER_DEFAULT);
	    	}
	    	
	    	System.out.println("Image filtered..");
	    	
	    	return dest;
	    	
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat laplacian(Mat src, int kernelSize) {
		try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat srcGray = new Mat();
	    	Mat dest = new Mat();
	
	    	int delta = 0;
	    	int scale = 1;
	    	int ddepth = CvType.CV_16S;
	    	
	    	Imgproc.GaussianBlur(src, src, new Size(kernelSize, kernelSize), 0, 0, Core.BORDER_DEFAULT);
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		srcGray = src;
	    	}
	    	
	    	Mat absDest = new Mat();
	    	Imgproc.Laplacian(srcGray, dest, ddepth, kernelSize, scale, delta, Core.BORDER_DEFAULT);
	    	Core.convertScaleAbs(dest, absDest);

	    	System.out.println("Image sharpened..");
	    	
	    	return dest;
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    		
    		return null;
    	}
	}
	
	protected static Mat conservative(Mat src, int kernelSize) {
		int
			row = src.rows(),
			col = src.cols();
		Mat srcGray = new Mat();
		
		try {
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		srcGray = src;
	    	}
	    	
	    	Mat dest = srcGray.clone();
	    	
	    	for(int i = 0; i < row; i++) {
	    		for(int j = 0; j < col; j++) {
	    			Core.MinMaxLocResult result = null;
	    			
	    			if(i >= (kernelSize/2) && i <= (row-(kernelSize/2)) && j >= (kernelSize/2) && j <= (col-(kernelSize/2))) {
	    				Mat submat = srcGray.submat(i - (kernelSize/2), i + (kernelSize/2), j - (kernelSize/2), j + (kernelSize/2));
	    				
	    				result = Core.minMaxLoc(submat);
	    			}
	    			
	    			double[] matData = srcGray.get(i, j);
	    			
	    			if(result != null) {
	        			if(matData[0] > result.maxVal) {
	        				matData[0] = result.maxVal;
	        			} else if(matData[0] < result.minVal) {
	        				matData[0] = result.minVal;
	        			}
	    			}
	    			
	    			dest.put(i, j, matData);
	    		}
	    	}
	    	
	    	System.out.println("Conservative filter applied..");
	    	
	    	return dest;
		} catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
			
			return null;
		}
	}
	
	protected static Mat minMax(Mat src, int kernelSize) {
		int
			row = src.rows(),
			col = src.cols();
		Mat srcGray = new Mat();
		
		try {
			if(src.type() != 0) {
				Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_RGB2GRAY);
			} else {
				srcGray = src;
			}
			
			Mat dest = new Mat(srcGray.size(), srcGray.type());
			
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					Core.MinMaxLocResult result = null;
					
					if(i >= (kernelSize/2) && i <= (row-(kernelSize/2)) && j >= (kernelSize/2) && j <= (col-(kernelSize/2))) {
						Mat submat = srcGray.submat(i - (kernelSize/2), i + (kernelSize/2), j - (kernelSize/2), j + (kernelSize/2));
						
						result = Core.minMaxLoc(submat);
					}
					
					double[] matData = srcGray.get(i, j);
					
					if(result != null) {
						double rate = Math.round((result.maxVal + result.minVal) / 2);
						
		    			if(matData[0] > rate) {
		    				matData[0] = result.maxVal;
		    			} else if(matData[0] < rate) {
		    				matData[0] = result.minVal;
		    			}
					}
					
					dest.put(i, j, matData);
				}
			}
			
			System.out.println("MinMax filter applied..");
			
			return dest;
		} catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
			
			return null;
		}
	}
	
	protected static Mat snn(Mat src) {
		int
			kernel = 3,
			row = src.rows(),
			col = src.cols();
		Mat srcGray = new Mat();
		
		try {
			if(src.type() != 0) {
				Imgproc.cvtColor(src, srcGray, Imgproc.COLOR_RGB2GRAY);
			} else {
				srcGray = src;
			}
			
			Mat dest = new Mat(srcGray.size(), srcGray.type());
			
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < col; j++) {
					if(i >= (kernel/2) && i <= (row-2) && j >= (kernel/2) && j <= (col-2)) {
						Mat submat = srcGray.submat(i - 1, i + 2, j - 1, j + 2);
						
						double center = submat.get(submat.cols()/2, submat.rows()/2)[0];
						
						double
								north, south,
								east, west,
								northwest, southeast,
								northeast, southwest;
						
						if(submat.get(0, 1)[0] > submat.get(2, 1)[0]) {
							north = submat.get(0, 1)[0];
							south = submat.get(2, 1)[0];
						} else {
							north = submat.get(2, 1)[0];
							south = submat.get(0, 1)[0];
						}
						
						if(submat.get(1, 2)[0] > submat.get(1, 0)[0]) {
							east = submat.get(1, 2)[0];
							west = submat.get(1, 0)[0];
						} else {
							east = submat.get(1, 0)[0];
							west = submat.get(1, 2)[0];
						}
						
						if(submat.get(0, 0)[0] > submat.get(2, 2)[0]) {
							northwest = submat.get(0, 0)[0];
							southeast = submat.get(2, 2)[0];
						} else {
							northwest = submat.get(2, 2)[0];
							southeast = submat.get(0, 0)[0];
						}
						
						if(submat.get(0, 2)[0] > submat.get(2, 0)[0]) {
							northeast = submat.get(0, 2)[0];
							southwest = submat.get(2, 0)[0];
						} else {
							northeast = submat.get(2, 0)[0];
							southwest= submat.get(0, 2)[0];
						}
						
						double avg = 0.0;
						
						if(center <  north && center > south && (Math.abs(north - center) != Math.abs(south - center))) {
							avg += Math.abs(north - center) > Math.abs(south - center) ? south : north;
						} else {
							avg += center;
						}
						
						if(center < east && center > west && (Math.abs(east - center) != Math.abs(west - center))) {
							avg += Math.abs(east - center) > Math.abs(west - center) ? west : east;
						} else {
							avg += center;
						}
						
						if(center < northwest && center > southeast && (Math.abs(northwest - center) != Math.abs(southeast - center))) {
							avg += Math.abs(northwest - center) > Math.abs(southeast - center) ? southeast : northwest;
						} else {
							avg += center;
						}
						
						if(center < northeast && center > southwest && (Math.abs(northeast - center) != Math.abs(southwest - center))) {
							avg += Math.abs(northeast - center) > Math.abs(southwest - center) ? southwest : northeast;
						} else {
							avg += center;
						}
						
						dest.put(i, j, Math.round(avg/4));
					} else {
						dest.put(i, j, srcGray.get(i, j)[0]);
					}
				}
			}
			
			System.out.println("SNN filter applied..");
			
			return dest;
		} catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
			
			return null;
		}
	}
}
