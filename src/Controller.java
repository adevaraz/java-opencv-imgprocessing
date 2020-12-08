import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Controller {

    @FXML
    public ImageView imageView;

    @FXML 
    public BorderPane pane;
    
    @FXML
    public Node right;
    
    public File file;
    public Mat src;
    
    public Image matToImg(Mat mat) {
    	MatOfByte byteMat = new MatOfByte();
    	Imgcodecs.imencode(".png", mat, byteMat);
    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
    	
    	return img;
    }
    
    public void displayImage(Image img) {
    	imageView.setImage(img);
		imageView.setFitWidth(300);
		imageView.setPreserveRatio(true);
    }
    
    /*********************************************
     * DIALOG BOXES
     *********************************************/
    
    public static boolean isNumeric(String str) {
    	try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
    }
    
    @FXML
    protected void dialogConvolution(ActionEvent event) {
    	Label titleLabel = new Label("Convolution Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);

    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.convolution(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    	});
    }
    
    @FXML
    protected void dialogAveraging(ActionEvent event) {
    	Label titleLabel = new Label("Averaging Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.averaging(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    		
    		grid.getChildren().clear();
    	});
    }
    
    @FXML
    protected void dialogGaussianBlur(ActionEvent event) {
    	Label titleLabel = new Label("Gaussian Blur Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.gaussianBlur(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    		
    		grid.getChildren().clear();
    	});
    }
    
    @FXML
    protected void dialogMedianBlur(ActionEvent event) {
    	Label titleLabel = new Label("Median Blur Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.medianBlur(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    		
    		grid.getChildren().clear();
    	});
    }
    
    @FXML
    protected void dialogBilateral(ActionEvent event) {
    	Label titleLabel = new Label("Bilateral Setting");
    	
    	Label diameterLabel = new Label("Diameter ( int <= 9 )");
    	TextField diameterField = new TextField();
    	
    	Label sigmaColLabel = new Label("Sigma Color ( double > 10 )");
    	TextField sigmaColField = new TextField();
    	
    	Label sigmaSpaceLabel = new Label("Sigma Space ( double > 10 )");
    	TextField sigmaSpaceField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, diameterLabel, diameterField,
    			sigmaColLabel, sigmaColField, sigmaSpaceLabel, sigmaSpaceField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(diameterLabel, 0, 1);
    	GridPane.setConstraints(diameterField, 1, 1);
    	GridPane.setConstraints(sigmaColLabel, 0, 2);
    	GridPane.setConstraints(sigmaColField, 1, 2);
    	GridPane.setConstraints(sigmaSpaceLabel, 0, 3);
    	GridPane.setConstraints(sigmaSpaceField, 1, 3);
    	
    	pane.setRight(grid);
    	
    	diameterField.textProperty().addListener((observable, oldValue, newValue) -> {
    		
    		if(isNumeric(newValue) && isNumeric(sigmaColField.getText()) && isNumeric(sigmaSpaceField.getText())) {
    			src = Filtering.bilateral(src, Integer.parseInt(newValue), Double.parseDouble(sigmaColField.getText()),
    					Double.parseDouble(sigmaSpaceField.getText()));
    			
    			displayImage(matToImg(src));
    		}
    	});
    	
		sigmaColField.textProperty().addListener((observable, oldValue, newValue) -> {
		    		
    		if(isNumeric(diameterField.getText()) && isNumeric(newValue) && isNumeric(sigmaSpaceField.getText())) {
    			src = Filtering.bilateral(src, Integer.parseInt(diameterField.getText()), Double.parseDouble(newValue),
    					Double.parseDouble(sigmaSpaceField.getText()));
    			
    			displayImage(matToImg(src));
    		}
    	});
		
		sigmaSpaceField.textProperty().addListener((observable, oldValue, newValue) -> {
			
			if(isNumeric(diameterField.getText()) && isNumeric(sigmaColField.getText()) && isNumeric(newValue)) {
				src = Filtering.bilateral(src, Integer.parseInt(diameterField.getText()), Double.parseDouble(sigmaColField.getText()),
						Double.parseDouble(newValue));
				
				displayImage(matToImg(src));
			}
		});
    }
    
    @FXML
    protected void dialogLaplacian(ActionEvent event) {
    	Label titleLabel = new Label("Laplacian Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.laplacian(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    		
    		grid.getChildren().clear();
    	});
    }
    
    @FXML
    protected void dialogConservative(ActionEvent event) {
    	Label titleLabel = new Label("Conservative Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.conservative(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    		
    		grid.getChildren().clear();
    	});
    }
    
    @FXML
    protected void dialogMinMax(ActionEvent event) {
    	Label titleLabel = new Label("MinMax Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Filtering.minMax(src, (Integer.parseInt(value) * 2 ) + 1);
    			
    			displayImage(matToImg(src));
    		}
    		
    		grid.getChildren().clear();
    	});
    }

    /*********************************************/
    
    @FXML
    protected void dialogSaltPepper(ActionEvent event) {
    	Label titleLabel = new Label("Salt and Pepper Setting");
    	
    	Label intensityLabel = new Label("Intensity");
    	TextField intensityField = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.getChildren().clear();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, intensityLabel, intensityField);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(intensityLabel, 0, 1);
    	GridPane.setConstraints(intensityField, 1, 1);
    	
    	pane.setRight(grid);
    	
    	intensityField.textProperty().addListener((observable, oldValue, newValue) -> {
    		String value = newValue;
    		
    		if(isNumeric(value)) {
    			src = Noise.saltPepper(src, Double.parseDouble(value));
    			
    			displayImage(matToImg(src));
    		}
    	});
    }
    
    /*********************************************/
    
    @FXML
    protected void erodeMorphSetting(ActionEvent event) {
    	Label titleLabel = new Label("Erode Morph Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	Label structureLabel = new Label("Structure Element");
    	RadioButton rectStructureRadio = new RadioButton("Rectangle");
    	RadioButton ellipseStructureRadio = new RadioButton("Ellipse");
    	RadioButton crossStructureRadio = new RadioButton("Cross");
    	
    	ToggleGroup toggleGroup = new ToggleGroup();
    	rectStructureRadio.setToggleGroup(toggleGroup);
    	ellipseStructureRadio.setToggleGroup(toggleGroup);
    	crossStructureRadio.setToggleGroup(toggleGroup);
    	
    	VBox container = new VBox();
    	container.setSpacing(10);
    	container.getChildren().addAll(rectStructureRadio, ellipseStructureRadio, 
    			crossStructureRadio);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField, 
    			structureLabel, container);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	GridPane.setConstraints(structureLabel, 0, 2);
    	GridPane.setConstraints(container, 1, 2);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(newValue) && structureType != -1) {
    					src = Morphology.erode(src, structureType, ((Integer.parseInt(newValue) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    	
    	toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
    		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(kernelSizeField.getText()) && structureType != -1) {
    					src = Morphology.erode(src, structureType, ((Integer.parseInt(kernelSizeField.getText()) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(Error e) {
    				
    			}
    		}
    	});
    }
    
    @FXML
    protected void dilateMorphSetting(ActionEvent event) {
    	Label titleLabel = new Label("Dilate Morph Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	Label structureLabel = new Label("Structure Element");
    	RadioButton rectStructureRadio = new RadioButton("Rectangle");
    	RadioButton ellipseStructureRadio = new RadioButton("Ellipse");
    	RadioButton crossStructureRadio = new RadioButton("Cross");
    	
    	ToggleGroup toggleGroup = new ToggleGroup();
    	rectStructureRadio.setToggleGroup(toggleGroup);
    	ellipseStructureRadio.setToggleGroup(toggleGroup);
    	crossStructureRadio.setToggleGroup(toggleGroup);
    	
    	VBox container = new VBox();
    	container.setSpacing(10);
    	container.getChildren().addAll(rectStructureRadio, ellipseStructureRadio, 
    			crossStructureRadio);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField, 
    			structureLabel, container);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	GridPane.setConstraints(structureLabel, 0, 2);
    	GridPane.setConstraints(container, 1, 2);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(newValue) && structureType != -1) {
    					src = Morphology.dilate(src, structureType, ((Integer.parseInt(newValue) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    	
    	toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
    		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(kernelSizeField.getText()) && structureType != -1) {
    					src = Morphology.dilate(src, structureType, ((Integer.parseInt(kernelSizeField.getText()) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(Error e) {
    				
    			}
    		}
    	});
    }
    
    @FXML
    protected void openingMorphSetting(ActionEvent event) {
    	Label titleLabel = new Label("Opening Morph Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	Label structureLabel = new Label("Structure Element");
    	RadioButton rectStructureRadio = new RadioButton("Rectangle");
    	RadioButton ellipseStructureRadio = new RadioButton("Ellipse");
    	RadioButton crossStructureRadio = new RadioButton("Cross");
    	
    	ToggleGroup toggleGroup = new ToggleGroup();
    	rectStructureRadio.setToggleGroup(toggleGroup);
    	ellipseStructureRadio.setToggleGroup(toggleGroup);
    	crossStructureRadio.setToggleGroup(toggleGroup);
    	
    	VBox container = new VBox();
    	container.setSpacing(10);
    	container.getChildren().addAll(rectStructureRadio, ellipseStructureRadio, 
    			crossStructureRadio);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField, 
    			structureLabel, container);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	GridPane.setConstraints(structureLabel, 0, 2);
    	GridPane.setConstraints(container, 1, 2);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(newValue) && structureType != -1) {
    					src = Morphology.opening(src, structureType, ((Integer.parseInt(newValue) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    	
    	toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
    		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(kernelSizeField.getText()) && structureType != -1) {
    					src = Morphology.opening(src, structureType, ((Integer.parseInt(kernelSizeField.getText()) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(Error e) {
    				
    			}
    		}
    	});
    }
    
    @FXML
    protected void closingMorphSetting(ActionEvent event) {
    	Label titleLabel = new Label("Closing Morph Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	Label structureLabel = new Label("Structure Element");
    	RadioButton rectStructureRadio = new RadioButton("Rectangle");
    	RadioButton ellipseStructureRadio = new RadioButton("Ellipse");
    	RadioButton crossStructureRadio = new RadioButton("Cross");
    	
    	ToggleGroup toggleGroup = new ToggleGroup();
    	rectStructureRadio.setToggleGroup(toggleGroup);
    	ellipseStructureRadio.setToggleGroup(toggleGroup);
    	crossStructureRadio.setToggleGroup(toggleGroup);
    	
    	VBox container = new VBox();
    	container.setSpacing(10);
    	container.getChildren().addAll(rectStructureRadio, ellipseStructureRadio, 
    			crossStructureRadio);
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField, 
    			structureLabel, container);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	GridPane.setConstraints(structureLabel, 0, 2);
    	GridPane.setConstraints(container, 1, 2);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(newValue) && structureType != -1) {
    					src = Morphology.closing(src, structureType, ((Integer.parseInt(newValue) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    	
    	toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
    		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
    			RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
    			String value = null;
    			
    			if(selectedRadioButton != null) {
    				value = selectedRadioButton.getText();
    			}
    			
    			try {
    				int structureType = -1;
    				
    				if(value.equals("Rectangle")) {
    					structureType = Imgproc.MORPH_RECT;
    				} else if(value.equals("Ellipse")) {
    					structureType = Imgproc.MORPH_ELLIPSE;
    				} else if(value.equals("Cross")) {
    					structureType = Imgproc.MORPH_CROSS;
    				}
    				
    				if(isNumeric(kernelSizeField.getText()) && structureType != -1) {
    					src = Morphology.closing(src, structureType, ((Integer.parseInt(kernelSizeField.getText()) * 2) + 1));
    					
    					displayImage(matToImg(src));
    				}
    				
    				grid.getChildren().clear();
    			} catch(Error e) {
    				
    			}
    		}
    	});
    }
    
    /*********************************************/
    
    @FXML
    protected void cannyEdgeSetting(ActionEvent event) {
    	Label titleLabel = new Label("Canny Edge Detection Setting");
    	
    	Label kernelLabel = new Label("Kernel size (2n+1)\n*) input n");
    	TextField kernelSizeField = new TextField();
    	
    	Label threshold1Label = new Label("Threshold 1");
    	TextField threshold1Field = new TextField();
    	
    	Label threshold2Label = new Label("Threshold 2");
    	TextField threshold2Field = new TextField();
    	
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(10, 5, 10, 5));
    	grid.getChildren().addAll(titleLabel, kernelLabel, kernelSizeField, 
    			threshold1Label, threshold1Field, threshold2Label, threshold2Field);
    	
    	GridPane.setConstraints(titleLabel, 1, 0);
    	GridPane.setConstraints(kernelLabel, 0, 1);
    	GridPane.setConstraints(kernelSizeField, 1, 1);
    	GridPane.setConstraints(threshold1Label, 0, 2);
    	GridPane.setConstraints(threshold1Field, 1, 2);
    	GridPane.setConstraints(threshold2Label, 0, 3);
    	GridPane.setConstraints(threshold2Field, 1, 3);
    	
    	pane.setRight(grid);
    	
    	kernelSizeField.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try {
    				if(isNumeric(newValue) && isNumeric(threshold1Field.getText()) && isNumeric(threshold2Field.getText())
    						&& Integer.parseInt(threshold1Field.getText()) >= 10 && Integer.parseInt(threshold2Field.getText()) >= 30) {
    					
    					src = EdgeDetection.canny(src, Integer.parseInt(newValue), Integer.parseInt(threshold1Field.getText()),
    							Integer.parseInt(threshold2Field.getText()));
    					
    					displayImage(matToImg(src));
    				}
    				
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    	
    	threshold1Field.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try {
    				if(isNumeric(kernelSizeField.getText()) && isNumeric(newValue) && isNumeric(threshold2Field.getText())
    						&& Integer.parseInt(newValue) >= 10 && Integer.parseInt(threshold2Field.getText()) >= 30) {
    					
    					src = EdgeDetection.canny(src, Integer.parseInt(kernelSizeField.getText()), Integer.parseInt(newValue),
    							Integer.parseInt(threshold2Field.getText()));
    					
    					displayImage(matToImg(src));
    				}
    				
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    	
    	threshold2Field.textProperty().addListener(new ChangeListener<String>() {
    		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    			
    			try {
    				if(isNumeric(kernelSizeField.getText()) && isNumeric(threshold1Field.getText()) && isNumeric(newValue)
    						&& Integer.parseInt(threshold1Field.getText()) >= 10 && Integer.parseInt(newValue) >= 30 ) {
    					
    					src = EdgeDetection.canny(src, Integer.parseInt(kernelSizeField.getText()), Integer.parseInt(threshold1Field.getText()),
    							Integer.parseInt(newValue));
    					
    					displayImage(matToImg(src));
    				}
    				
    			} catch(NullPointerException e) {
    				
    			}
    		}
    	});
    }
    
    /***************************************************************************************************************************************/

    @FXML
    protected void openImageOnClicked(ActionEvent event) throws FileNotFoundException {
    	System.out.println("Open image..");
    	
    	final FileChooser fileChooser = new FileChooser();
    	MenuItem menu = (MenuItem) event.getTarget();
    	
    	fileChooser.setTitle("Open File");
    	
    	try {
    		file = fileChooser.showOpenDialog(menu.getParentPopup().getOwnerWindow());
        	
        	if(file != null) {
    			Image img = new Image(new FileInputStream(file));
    			
    			displayImage(img);
    			
    			System.out.println("Image loaded..");
    		}
        	
        	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        	src = Imgcodecs.imread(file.toString(), Imgcodecs.IMREAD_ANYCOLOR);
    	} catch (NullPointerException e) {
    		System.out.println("Open image cancelled");
    	}
    }
    
    @FXML
    protected void saveImg(ActionEvent event) {
    	try {
    		Imgcodecs.imwrite(file.getAbsolutePath().toString(), src);
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    	}
    }
    
    @FXML
    protected void saveAs(ActionEvent event) {
    	try {
	    	FileChooser fileChooser = new FileChooser();
	    	MenuItem menu = (MenuItem) event.getTarget();
	    	
	    	fileChooser.setTitle("Save As");
	    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
	    	File writeFile = fileChooser.showSaveDialog(menu.getParentPopup().getOwnerWindow());
	    	
	    	Imgcodecs.imwrite(writeFile.getAbsolutePath().toString(), src);
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    	}
    }
    
    /**
     * COLOR
     */
    @FXML
    protected void grayScale(ActionEvent event) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat();
	    	Imgproc.cvtColor(src, dest, Imgproc.COLOR_RGB2GRAY);
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", dest, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
			System.out.println("Image change to grayscale..");
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    	}
    }
    
    /**
     * FILTERING
     */
    @FXML
    protected void snn(ActionEvent event) {
		try {
			src = Filtering.snn(src);
			displayImage(matToImg(src));
		} catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
		}
    }
    
    /**
     * NOISE
     */
    protected void saltPepper(double intensity) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat();
	    	
	    	for(int i = 0; i < src.rows(); i++) {
	    		for(int j = 0; j < src.cols(); j++) {
	    			Random random = new Random();
	    			double randVal = random.nextDouble();
	    			
	    			double[] data = src.get(i, j);
	    			
	    			if(src.type() != 0) {
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
	    			
	    			src.put(i, j, data);
	    		}
	    	}
	    	
	    	dest = src;
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", dest, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
	    	System.out.println("Salt and pepper noise applied..");
	    	
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    	}
    }
    
    @FXML
    protected void gaussianNoise(ActionEvent event) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat();
	    	Mat noise = new Mat(src.size(), src.type());
	    	MatOfDouble mean = new MatOfDouble();
	    	MatOfDouble dev = new MatOfDouble();
	    	
	    	Core.meanStdDev(src, mean, dev);
	    	Core.randn(noise, mean.get(0, 0)[0], dev.get(0, 0)[0]);
	    	Core.add(src, noise, dest);
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", dest, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
	    	System.out.println("Gaussian noise applied..");
    	
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    	}
    }
    
    @FXML
    protected void speckle(ActionEvent event) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat dest = new Mat();
	    	Mat graySrc = new Mat();
	    	Mat noise = new Mat(src.size(), CvType.CV_64F);
	    	MatOfDouble mean = new MatOfDouble();
	    	MatOfDouble dev = new MatOfDouble();
	    	
	    	// Convert to grayscale
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, graySrc, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		graySrc = src;
	    	}
	    	
	    	Core.meanStdDev(graySrc, mean, dev);
	    	
	    	Mat gauss = new Mat(graySrc.size(), graySrc.type());
	    	Core.randn(gauss, mean.get(0, 0)[0], dev.get(0, 0)[0]);
	    	Core.multiply(graySrc, gauss, noise);
	    	Core.add(graySrc, noise, dest);
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", noise, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
	    	System.out.println("Speckle noise applied..");
	    	
    	} catch(NullPointerException e) {
    		System.out.println("Image not loaded yet");
    	}
    }
    
    /**
     * EDGE DETECTION
     */
    @FXML
    protected void sobel(ActionEvent event) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat graySrc = new Mat(src.rows(), src.cols(), src.type());
	    	Mat dest = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0,0));
	    	
	    	int scale = 1;
	    	int delta = 0;
	    	int ddepth = CvType.CV_16S;
	    	
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, graySrc, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		graySrc = src;
	    	}
	    	
	    	Mat gX = new Mat(),gY = new Mat();
	    	Mat absGX = new Mat(), absGY = new Mat();
	    	
	    	Imgproc.Sobel(graySrc, gX, ddepth, 1, 0, 3, scale, delta);
	    	Imgproc.Sobel(graySrc, gY, ddepth, 0, 1, 3, scale, delta);
	    	
	    	Core.convertScaleAbs(gX,  absGX);
	    	Core.convertScaleAbs(gY, absGY);
	    	
	    	Core.addWeighted(absGX, 0.5, absGY, 0.5, 0, dest);
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", dest, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
	    	System.out.println("Sobel edge detection applied..");
    	
	    } catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
		}
    }
    
    @FXML
    protected void prewitt(ActionEvent event) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat graySrc = new Mat(src.rows(), src.cols(), src.type());
	    	Mat destX = new Mat();
	    	Mat destY = new Mat();
	    	Mat dest = new Mat();
	    	
	    	// Convert to grayscale 
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, graySrc, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		graySrc = src;
	    	}
	    	
	    	int kernelSize = 3;
	    	Mat kernelX = new Mat(kernelSize, kernelSize, CvType.CV_64F) {
	    		{
	    			// col 1
	    			put(0, 0, -1);
	    			put(0, 1, -1);
		    		put(0, 2, -1);
		    		
		    		// col 2
		    		put(1, 0, 0);
	    			put(1, 1, 0);
		    		put(1, 2, 0);
		    		
		    		// col 3
		    		put(2, 0, 1);
	    			put(2, 1, 1);
		    		put(2, 2, 1);
	    		}
	    	};
	    	
	    	Mat kernelY = new Mat(kernelSize, kernelSize, CvType.CV_64F) {
	    		{
	    			// row 1
	    			put(0, 0, 1);
	    			put(1, 0, 1);
		    		put(2, 0, 1);
		    		
		    		// row 2
		    		put(0, 1, 0);
	    			put(1, 1, 0);
		    		put(2, 1, 0);
		    		
		    		// row 3
		    		put(0, 2, -1);
	    			put(1, 2, -1);
		    		put(2, 2, -1);
	    		}
	    	};
	    	
	    	Imgproc.filter2D(graySrc, destX, -1, kernelX);
	    	Imgproc.filter2D(graySrc, destY, -1, kernelY);
	    	
	    	Core.add(destX, destY, dest);
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", dest, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
	    	System.out.println("Prewitt edge detection applied..");
    	
	    } catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
		}
    }
    
    @FXML
    protected void robert(ActionEvent event) {
    	try {
	    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    	Mat graySrc = new Mat(src.rows(), src.cols(), src.type());
	    	Mat destX = new Mat();
	    	Mat destY = new Mat();
	    	Mat dest = new Mat();
	    	
	    	// Convert to grayscale 
	    	if(src.type() != 0) {
	    		Imgproc.cvtColor(src, graySrc, Imgproc.COLOR_RGB2GRAY);
	    	} else {
	    		graySrc = src;
	    	}
	    	
	    	int kernelSize = 2;
	    	Mat kernelX = new Mat(kernelSize, kernelSize, CvType.CV_64F) {
	    		{
	    			// col 1
	    			put(0, 0, 1);
	    			put(0, 1, 0);
		    		
		    		// col 2
		    		put(1, 0, 0);
	    			put(1, 1, -1);
	    		}
	    	};
	    	
	    	Mat kernelY = new Mat(kernelSize, kernelSize, CvType.CV_64F) {
	    		{
	    			// row 1
	    			put(0, 0, 0);
	    			put(1, 0, -1);
		    		
		    		// row 2
		    		put(0, 1, 1);
	    			put(1, 1, 0);
	    		}
	    	};
	    	
	    	Imgproc.filter2D(graySrc, destX, -1, kernelX);
	    	Imgproc.filter2D(graySrc, destY, -1, kernelY);
	    	
	    	Core.add(destX, destY, dest);
	    	
	    	MatOfByte byteMat = new MatOfByte();
	    	Imgcodecs.imencode(".png", dest, byteMat);
	    	Image img = new Image(new ByteArrayInputStream(byteMat.toArray()));
	    	
	    	displayImage(img);
			
			src = dest;
	    	System.out.println("Robert edge detection applied..");
    	
	    } catch(NullPointerException e) {
			System.out.println("Image not loaded yet");
		}
    }
}