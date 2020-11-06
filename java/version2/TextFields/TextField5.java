package version2.TextFields;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextField5 extends JTextField{
	public TextField5() {
		this.setBounds(194, 313, 40, 24);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.setColumns(10);
	}
}
