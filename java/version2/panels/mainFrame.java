package version2.panels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class mainFrame extends JFrame {
	private static ImagePanel ImagePanel;
	private static mainPanel mainPanel;
	private static CartPanel cartPanel;
	private static ExitPanel exitPanel;
	private static version2.project.ProductVo productVo;
	//Setter의 매개값으로 주어지는 Panel 들을 확인하여 주입하게 됩니다.

	
	public mainFrame() {
		setBounds(100, 100, 1063, 651);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}

	

	public void setPanel(JPanel panel) {
		if (panel instanceof ImagePanel) {
			ImagePanel = (version2.panels.ImagePanel) panel;
		} else if (panel instanceof mainPanel) {
			mainPanel = (version2.panels.mainPanel) panel;
		} else if (panel instanceof CartPanel) {
			cartPanel = (CartPanel) panel;
		} else if (panel instanceof ExitPanel) {
			exitPanel = (ExitPanel) panel;
		}
		this.getContentPane().add(panel);
		return;
		// 판넬들을 구별하여 알맞은 필드에 할당하며 마지막으로 해당 판넬을 mainFrame에 등록하는 Setter 입니다. 
	}

	
	// 판넬의 UI처리를 한 클래스 내에서 다루기 위해 setter로 주입받은 판넬들을 사용하게 됩니다.
	public static void TurnOnPay() {
		EventQueue.invokeLater(() -> {
			exitPanel.setTable();
			exitPanel.setVisible(true);
			ImagePanel.setVisible(false);
			mainPanel.setVisible(false);
			cartPanel.setVisible(false);
		});
	}

	public static void TurnOnMain() {//종료.
		EventQueue.invokeLater(() -> {
			JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {}
			System.exit(0);
		});
	}

	public static void PrintTable(String[][] check) {
		// <TextFields 에서 사용될 PrintTable 입니다. 카트 판넬에 해당 JScrollPane을 등록합니다. printTable()은 CartTable에 있는 메서드로 
		//매개변수를 받아 테이블을 생성한 뒤 바로 JScrollPane 으로 리턴합니다.
		cartPanel.addCart(version2.table.CartTable.printTable(check));
	}

}
