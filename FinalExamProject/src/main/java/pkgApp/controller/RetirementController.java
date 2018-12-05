package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {
		
	private RetirementApp mainApp = null;
	
	@FXML
	private TextField txtYearsToWork;
	
	@FXML
	private TextField txtAnnualReturnWork;
	
	@FXML
	private TextField txtYearsRetired;
	
	@FXML
	private TextField txtAnnualReturnRetired;
	
	@FXML
	private TextField txtRequiredIncome;
	
	@FXML
	private TextField txtMonthlySSI;
	
	@FXML
	private Label LabSaveEachMonth;
	
	@FXML
	private Label LabNeedToSave;

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
		//Hardcoded clearing.
		System.out.println("Clear pressed");
		txtYearsToWork.setText("");
		txtAnnualReturnWork.setText("");
		txtYearsRetired.setText("");
		txtAnnualReturnRetired.setText("");
		txtRequiredIncome.setText("");
		txtMonthlySSI.setText("");
		LabSaveEachMonth.setText("");
		LabNeedToSave.setText("");
	}

	//Helper function to make sure typing is appropriate.
	public Boolean isOfType(String testStr, String type) {
		try {
			 if (type.equalsIgnoreCase("int")) {
				Integer.parseInt(testStr);
			} else if (type.equalsIgnoreCase("double")) {
				Double.parseDouble(testStr);
			}
			return true;
		} catch(Exception e) {
			return false;
		}
 
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		
		//Control variable for validation.
		boolean shouldBreak = false;
		
		//Checking for appropriate typing.
		if(!isOfType(txtAnnualReturnRetired.getText(), "double")) {
			txtAnnualReturnRetired.setText("Invalid Input (not double)");
			shouldBreak = true;
		}
		if(!isOfType(txtAnnualReturnWork.getText(), "double")) {
			txtAnnualReturnWork.setText("Invalid Input (not double)");
			shouldBreak = true;
		}
		if(!isOfType(txtMonthlySSI.getText(), "double")) {
			txtMonthlySSI.setText("Invalid Input (not double)");
			shouldBreak = true;
		}
		if(!isOfType(txtRequiredIncome.getText(), "double")) {
			txtRequiredIncome.setText("Invalid Input (not double)");
			shouldBreak = true;
		}
		if(!isOfType(txtYearsRetired.getText(), "int")) {
			txtYearsRetired.setText("Invalid Input (not int)");
			shouldBreak = true;
		}
		if(!isOfType(txtYearsToWork.getText(), "int")) {
			txtYearsToWork.setText("Invalid Input (not int)");
			shouldBreak = true;
		}
		//Checking for limits on investments as outlined in the doc.
		if(Double.parseDouble(txtAnnualReturnRetired.getText()) > .03) {
			txtAnnualReturnRetired.setText("Invalid Input (must be at or below .03 (3%))");
			shouldBreak = true;
		}
		if(Double.parseDouble(txtAnnualReturnRetired.getText()) < 0) {
			txtAnnualReturnRetired.setText("Invalid Input (must be at or above 0)");
			shouldBreak = true;
		}
		if(Double.parseDouble(txtAnnualReturnWork.getText()) > .2) {
			txtAnnualReturnWork.setText("Invalid Input (must be at or below .2 (20%))");
			shouldBreak = true;
		}
		if(Double.parseDouble(txtAnnualReturnWork.getText()) < 0) {
			txtAnnualReturnWork.setText("Invalid Input (must be at or above 0)");
			shouldBreak = true;
		}
		//If we encountered an invalid value, then we can't calculate.
		if(shouldBreak) {
			return;
		}
		//Calculating with valid inputs.
		Retirement masterCalc = new Retirement();
		masterCalc.setdAnnualReturnRetired(Double.parseDouble(txtAnnualReturnRetired.getText()));
		masterCalc.setdAnnualReturnWorking(Double.parseDouble(txtAnnualReturnWork.getText()));
		masterCalc.setdMonthlySSI(Double.parseDouble(txtMonthlySSI.getText()));
		masterCalc.setdRequiredIncome(Double.parseDouble(txtRequiredIncome.getText()));
		masterCalc.setiYearsRetired(Integer.parseInt(txtYearsRetired.getText()));
		masterCalc.setiYearsToWork(Integer.parseInt(txtYearsToWork.getText()));
		LabNeedToSave.setText(String.format("%.2f", masterCalc.TotalAmountSaved()));
		LabSaveEachMonth.setText(String.format("%.2f", Math.abs(masterCalc.AmountToSave())));
	}
	
}
