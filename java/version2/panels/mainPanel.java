package version2.panels;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;

public class mainPanel extends JPanel {
	public mainPanel() {
		this.setBounds(0, 0, 1045, 377);
		this.setVisible(false);
		this.setLayout(null);
	}

	public void setMainPanel(Map<String, Vector<Component>> mapLabel) {
		// assembler의 맵핑객체를 풀어내 화면에 등록합니다. mainPanel이므로 Key 는 main 입니다.
		Vector<Component> list = mapLabel.get("main");
		for (int i = 0; i < list.size(); i++) {
			this.add(list.get(i));
		}
	}

}
