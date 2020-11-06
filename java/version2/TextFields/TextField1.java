package version2.TextFields;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextField1 extends JTextField{//메인판넬에 들어가야합니다!
	public TextField1() {
		this.setBounds(194, 157, 40, 24);
		this.setHorizontalAlignment(SwingConstants.CENTER); //
		this.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.setColumns(10);
	}
}
