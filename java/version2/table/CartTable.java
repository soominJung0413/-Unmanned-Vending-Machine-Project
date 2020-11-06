package version2.table;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import version2.TextFields.TextFields;

public class CartTable {
	public static Vector<version2.project.ProductVo> ProductVector = version2.TextFields.TextFields.ProductVector;
	private static String[] headings = new String[] { "품번", "품명", "단위가격", "수량", "상품가격" };
	private static String[][] cartItems = { { "", "", "", "", "" } };


	public static JTable table = new JTable(cartItems, headings);
	private static version2.panels.CartPanel cartPanel = new version2.panels.CartPanel();
	public static JScrollPane scrollPane = new JScrollPane();
	private static version2.project.VendingMerchineDao VmDao = new version2.project.VendingMerchineDao();
	private static version2.project.ProductVo pVo = new version2.project.ProductVo();
	//필요한 필드들을 선언해봤습니다.
	

	public static JScrollPane getTable() {
		//getter 는 table을 생성 후 JScrollPane에 넣어 리턴합니다.
		table = new JTable(cartItems, headings);
		table.setSize(780, 150);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(true);
		scrollPane.setBounds(150, 190, 780, 150);
		return scrollPane;
	}

	public static JScrollPane printTable(String[][] data) {
		//밑은 매개값을 받아 스크롤 페인으로 넘겨서 프린트를 하는 메서드입니다.
		table = new JTable(data, headings);
		table.setSize(780, 150);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(true);
		scrollPane.setBounds(150, 190, 780, 150);
		return scrollPane;
	}

	//exitPanel 테이블 수정과정중에서 내부적으로 데이터를 만들어 적용시켰습니다.
	public static JScrollPane getScrollPane() {
		System.out.println(setData().length);
		table = new JTable(setData(), headings);

		table.setSize(780, 150);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(true);
		scrollPane.setBounds(150, 190, 780, 150);
		return scrollPane;
	}

	public static String[][] setData() {
		String[][] preparePrintData = new String[ProductVector.size()][5];
		for (int i = 0; i < ProductVector.size(); i++) {
			preparePrintData[i][0] = String.valueOf(ProductVector.get(i).getProdid());
			preparePrintData[i][1] = ProductVector.get(i).getName();
			preparePrintData[i][2] = String.valueOf(ProductVector.get(i).getPrice());
			preparePrintData[i][3] = String.valueOf(ProductVector.get(i).getStock());
			preparePrintData[i][4] = String.valueOf(ProductVector.get(i).getOldprice());
		}
		return preparePrintData;
	}

}
