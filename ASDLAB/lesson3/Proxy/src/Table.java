import java.util.ArrayList;

public class Table implements ITable {

	ArrayList<Row> rows = new ArrayList<Row>();

	@Override
	public int numOfRows() {
		return rows.size();
	}

	@Override
	public IRow getRow(int rowNum) {
		return rows.get(rowNum);
	}

	@Override
	public void addRow(IRow row, int rowNum) {
		rows.add(rowNum, (Row) row);
	}

	@Override
	public void deleteRow(int rowNum) {
		rows.remove(rowNum);
	}

	@Override
	public void modifyRow(int rowNum, IRow row) {
		rows.add(rowNum, (Row) row);
	}

	@Override
	public void print() {
		for (int i = 0; i < rows.size(); i++) {
			System.out.println(rows.get(i));
		}

	}

}
