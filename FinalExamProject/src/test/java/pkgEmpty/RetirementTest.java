package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

public class RetirementTest {

	@Test
	public void TotalAmountSavedTest() {
		Retirement masterCalc = new Retirement();
		masterCalc.setdAnnualReturnRetired(.02);
		masterCalc.setdAnnualReturnWorking(.07);
		masterCalc.setdMonthlySSI(2642);
		masterCalc.setdRequiredIncome(10000);
		masterCalc.setiYearsRetired(20);
		masterCalc.setiYearsToWork(40);
		assertEquals("1454485.55", String.format("%.2f", masterCalc.TotalAmountSaved()));
	}
	
	@Test
	public void AmountToSaveTest() {
		Retirement masterCalc = new Retirement();
		masterCalc.setdAnnualReturnRetired(.02);
		masterCalc.setdAnnualReturnWorking(.07);
		masterCalc.setdMonthlySSI(2642);
		masterCalc.setdRequiredIncome(10000);
		masterCalc.setiYearsRetired(20);
		masterCalc.setiYearsToWork(40);
		assertEquals("554.13", String.format("%.2f", Math.abs(masterCalc.AmountToSave())));
	}

}
