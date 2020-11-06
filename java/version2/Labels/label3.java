package version2.Labels;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class label3 extends JLabel{
		public label3() {
			this.setText("커피");
			this.setBounds(563, 37, 198, 31);
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		}
}
