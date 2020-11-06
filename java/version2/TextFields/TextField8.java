package version2.TextFields;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextField8 extends JTextField{//메인 판넬!
	public TextField8() {
		this.setBounds(917, 313, 40, 24);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.setColumns(10);
	}
}
