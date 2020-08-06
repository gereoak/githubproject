public class Main {

	public static void main(String[] args) {

		// Create a table proxy
		ITable tableProxy = new TableProxy();

		// create the rows to be added
		IRow row1 = new Row();

		// Adding columns to row1
		row1.addColumn("Mike");
		row1.addColumn("Henry");
		row1.addColumn("Pires");

		tableProxy.addRow(row1, 0);

		IRow row2 = new Row();

		// Adding columns to row1
		row2.addColumn("Viera");
		row2.addColumn("Gilberto");
		row2.addColumn("Edu");

		tableProxy.addRow(row2, 1);

		tableProxy.print();

		IRow row3 = new Row();

		// Adding columns to row1
		row3.addColumn("Cole");
		row3.addColumn("Campbell");
		row3.addColumn("Toure");

		tableProxy.modifyRow(1, row3);
		// tableProxy.deleteRow(1);

		System.out.println("\n Modifying row 1");

		tableProxy.print();

	}

}
