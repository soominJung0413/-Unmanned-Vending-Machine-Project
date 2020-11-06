package version2.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImagePanel extends JPanel{//초기화면판넬 필요한것은 라벨 버튼
	private ImagePanel imgPanel;
	private Image image;
		
	public ImagePanel(Image image) {
			this.image = image;
			setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		    setLayout(null);
		    imgPanel = this;
		    setComponent();
		}
		
		public void paintComponent(Graphics g) { 
			g.drawImage(image, 0, 0, null); 
		}
		private void setComponent() {
			setLayout(null);
			imgPanel.setBounds(0, 0, 1045, 612);
			imgPanel.setLayout(null);
			imgPanel.setVisible(true);
		}
	public void setImagePanel(JLabel Lbl1,JButton Btn) {
		imgPanel.add(Lbl1);
		imgPanel.add(Btn);
	}
}
