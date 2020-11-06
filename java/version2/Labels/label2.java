package version2.Labels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class label2 extends JLabel{
	public label2() {
		this.setText("샐러드");
		this.setBounds(319, 37, 198, 31);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("맑은 고딕", Font.BOLD, 30));
	}
}
