public class TableProxy implements ITable {

	ITable table = new Table();

	@Override
	public int numOfRows() {
		return table.numOfRows();
	}

	@Override
	public IRow getRow(int rowNum) {
		return table.getRow(rowNum);
	}

	@Override
	public void addRow(IRow row, int rowNum) {
		table.addRow(row, rowNum);
	}

	@Override
	public void deleteRow(int rowNum) {
		synchronized (table.getRow(rowNum)) {
			table.deleteRow(rowNum);
		}
	}

	@Override
	public void modifyRow(int rowNum, IRow row) {
		synchronized (table.getRow(rowNum)) {
			table.deleteRow(rowNum);
			table.modifyRow(rowNum, row);
		}

	}

	@Override
	public void print() {
		table.print();

	}

}
