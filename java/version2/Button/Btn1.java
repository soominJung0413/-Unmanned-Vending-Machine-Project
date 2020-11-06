package version2.Button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Btn1 extends JButton{
	public Btn1() {
		setText("주문하시려면 눌러주세요.");//<주문버튼
		setBounds(335, 348, 404, 66);
		setForeground(Color.BLUE);
		setBackground(Color.ORANGE);
		setFont(new Font("맑은 고딕", Font.BOLD, 30));
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
}
