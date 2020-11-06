package version2.Labels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ImageLabel extends JLabel{//ImagePanel 에 들어가는 라벨
	public ImageLabel(String e) {
		setText(e);
		setBounds(98, 245, 870, 66);
		setForeground(Color.BLUE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("맑은 고딕", Font.BOLD, 40));
	}
	
}
