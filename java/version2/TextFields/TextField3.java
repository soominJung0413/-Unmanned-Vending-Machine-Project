package version2.TextFields;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextField3 extends JTextField{//이친구도 메인판넬에 갑니다.
	public TextField3() {
		this.setBounds(677, 157, 40, 24);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		this.setColumns(10);
	}
}
