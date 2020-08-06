public interface ITable {
	public int numOfRows();

	public IRow getRow(int rowNum);

	// add row at the end
	public void modifyRow(int rowNum, IRow row);

	public void addRow(IRow row, int rowNum);

	public void deleteRow(int rowNum);

	public void print();
}
