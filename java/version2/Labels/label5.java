package version2.Labels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class label5 extends JLabel {
	public label5() {
		this.setBounds(121, 81, 113, 74);
		this.setIcon(new ImageIcon(getClass().getResource("image\\햄에그샌드위치1.jpg")));
		this.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
