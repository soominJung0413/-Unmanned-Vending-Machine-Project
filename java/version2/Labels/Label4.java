package version2.Labels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label4 extends JLabel{
	public Label4() {
		this.setText("음료");
		this.setBounds(813, 37, 198, 31);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 30));
	}
}
