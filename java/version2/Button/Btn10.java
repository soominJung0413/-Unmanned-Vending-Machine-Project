package version2.Button;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Btn10 extends JButton{
	public Btn10() {
		this.setText("결제");
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setBounds(391, 350, 143, 27);
	}
}
