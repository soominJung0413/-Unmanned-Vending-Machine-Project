package version2.TextFields;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextField2 extends JTextField { //메인 판넬에 들어갑니다.
	public TextField2() {
		this.setBounds(436, 157, 40, 24);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.setColumns(10);
	}
}
