package version2.panels;

import java.awt.Component;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ExitPanel extends JPanel {
		public ExitPanel() {
		this.setBounds(0, 0, 1063, 651);
		this.setLayout(null);
		this.setVisible(false);
	}

	public void addPayPanel(JScrollPane scrollpane) {
		this.add(scrollpane);
	}

	
	public void setMainPanel(Map<String, Vector<Component>> mapLabel) {
		// assembler의 맵핑객체를 풀어내 화면에 등록합니다. ExitPanel이므로 Key 는 exit 입니다.
		Vector<Component> list = mapLabel.get("exit");
		for (int i = 0; i < list.size(); i++) {
			this.add(list.get(i));
		}
	}

	public void setTable() {//테이블을 ScrollPane 채로 받기위한 메서드입니다.
		this.add(version2.table.CartTable.getScrollPane());
	}

}
