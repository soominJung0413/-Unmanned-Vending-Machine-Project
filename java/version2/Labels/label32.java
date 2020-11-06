package version2.Labels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class label32 extends JLabel{
	public label32() {
		this.setText("콜라");
		this.setBounds(768, 97, 76, 18);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	}
}
