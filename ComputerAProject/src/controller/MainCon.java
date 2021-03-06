package controller;

import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Main;
import model.OpCode;
import model.Sprite;
import model.SystemModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import model.Runtime;
public class MainCon implements EventHandler<ActionEvent>{


	/**System model.*/
	static SystemModel system = new SystemModel();
	/**Graphics context for the animation.*/
	private GraphicsContext emulator;

	@FXML
	private Button power;
	@FXML
	private Button pause;
	@FXML
	private Button play;
	@FXML
	private Button back;
	@FXML
	private Button forward;
	@FXML
	private Canvas emulatorDisplay;
	@FXML
	private Label instPointerValue;
	@FXML
	private Label stackPointerValue;
	@FXML
	private Label flagsValue;
	@FXML
	private Label register0Value;
	@FXML
	private Label register1Value;
	@FXML
	private Label register2Value;
	@FXML
	private Label register3Value;
	@FXML
	private Slider progressBar;
	@FXML
	private ComboBox<String> loadFile;
//	@FXML
//	private TextField instBuffer;
	final String DATA_FOLDER_PATH = "Resources/";


	public void initialize(){
	//create graphic for scene
	play.setOnAction(e->this.printFileName());
	populateComboBox();
	setEmulator(emulatorDisplay.getGraphicsContext2D());
	//Main.getStage().setHeight(800.0);
	//Main.getStage().setWidth(1280.0);
//		this.loadFile.setPromptText("Select file to load");
//		loadFile.setItems().addAll("does this show");
	
	loadFile.setOnAction(e->this.handleFileChange());
	}
	
	
	public void handleFileChange() {
		Scanner scan = null;
		try {
			scan = new Scanner (new File(loadFile.getValue()));
			while(scan.hasNextLine()) {
				String[] line = scan.nextLine().split(" ");
				
				if(line[0].length()  == 1) {
					
					
				}else {
					OpCode ops = new OpCode(loadFile.getValue());
					//OpCode ops = new OpCode("opcode.txt");
					scan.close();
					break;
				}
			}
		}catch(FileNotFoundException e) {
			System.err.println("File in loadFile couldn't be found!");
		}
	}
	
	public void populateComboBox() {
		ObservableList<String> list = FXCollections.observableArrayList();
		final File dataFolder = new File(DATA_FOLDER_PATH);
		
		for(final File file : dataFolder.listFiles()) {
			System.out.println(file.getName());
			list.add(file.getName());
		}
		
		loadFile.setItems(list);
		
	}
	
	
	
	private void printFileName() {
		System.out.println(loadFile.getValue());
		
	}
	
	public void UpdateRegisters(){
		//instPointerValue
		//this.stackPointerValue
		//this.flagsValue
		//this.register0Value.setText(U10.array.toString());
		//this.register1Value.setText(U11.array.toString());
		//this.register2Value.setText(U12.array.toString());
		//this.register3Value.setText(U13.array.toString());
	}

	@Override
	public void handle(ActionEvent event) {
		ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
		Button pressed = (Button) event.getSource();
		switch(pressed.getId())	{
			case "pause":
				System.out.print(pressed.getId() +"\n");
				break;
			case "back":
				System.out.print(pressed.getId()+"\n");
				break;
			case "play":
				System.out.print(pressed.getId()+"\n");
				break;
			case "forward":
				System.out.print(pressed.getId()+"\n");
				break;
			case "power":
				
				//system.beginDisplay(this.emulatorDisplay.getGraphicsContext2D());
				//Runtime r = new Runtime();
				//r.doRuntime();
				//start gui rendering here
				
				System.out.print(pressed.getId()+"\n");
				break;

			default:
				System.out.print("invalid case\n");
		}
	}

	/**Gets the display*/
	public GraphicsContext getEmulator() {
		return this.emulatorDisplay.getGraphicsContext2D();
	}
	
	public static SystemModel getSystem(){
		return system;
	}

	/**Sets the display (gameCanvas).*/
	public void setEmulator(GraphicsContext canvasUsed) {
		this.emulator = canvasUsed;
	}
}
