package version2.Labels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class label1 extends JLabel{
		public label1() {
			this.setText("샌드위치");
			this.setBounds(81, 37, 198, 31);
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		}
}
