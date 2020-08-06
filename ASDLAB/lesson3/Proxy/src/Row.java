import java.util.ArrayList;

public class Row implements IRow {

	ArrayList<String> columns = new ArrayList<String>();

	@Override
	public void addColumn(String str) {
		columns.add(str);
	}

	@Override
	public String toString() {
		return columns.toString();
	}

}
