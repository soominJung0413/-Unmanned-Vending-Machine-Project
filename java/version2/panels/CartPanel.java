package version2.panels;

import java.awt.Component;
import java.util.Map;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CartPanel extends JPanel {
	public CartPanel() {
		this.setBounds(0, 197, 1045, 425);
		this.setLayout(null);
		this.setVisible(false);
	}

	public void addCart(JScrollPane scrollpane) {
		this.add(scrollpane);
	}

	public void setMainPanel(Map<String, Vector<Component>> mapCom) {
		// assembler의 맵핑객체를 풀어내 화면에 등록합니다. CartPanel이므로 Key 는 cart 입니다.
		Vector<Component> list = mapCom.get("cart");
		for (int i = 0; i < list.size(); i++) {
			this.add(list.get(i));
		}
	}

}
