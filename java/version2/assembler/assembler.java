package version2.assembler;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;

import version2.Button.Btn1;
import version2.Button.Buttons;
import version2.Labels.ImageLabel;
import version2.Labels.labels;
import version2.TextFields.TextFields;
import version2.panels.CartPanel;
import version2.panels.ExitPanel;
import version2.panels.ImagePanel;
import version2.panels.mainFrame;
import version2.panels.mainPanel;
import version2.project.ProductVo;
import version2.table.CartTable;

public class assembler extends Buttons {// 객체 조립기입니다.
	public static Vector<version2.project.ProductVo> ProductVector = new Vector<>();

	private static Map<String, Vector<Component>> mapBtn = Buttons.getButtons();
	private static Map<String, Vector<Component>> mapLabel = labels.getLabels();
	private static Map<String, Vector<Component>> mapTextField = TextFields.getTextFields();
	//<<Buttons Labels TextFields 의 정적메소드로 Map<String, Vector<Component>>을 받아 assembler의 필드에 저장합니다. Swing의 UI 를 다채롭게 받을 수 있고 캐스팅도 가능하여 각각의 판넬에는 하나의 메소드만 있으면 확인이 가능합니다.
	
	private mainFrame mF = new mainFrame();
	private ImagePanel IP = new ImagePanel(new ImageIcon(getClass().getResource("image\\초기화면.jpg")).getImage());
	private mainPanel MP = new mainPanel();
	private CartPanel CP = new CartPanel();
	private ExitPanel EP = new ExitPanel();
	private ImageLabel label1 = new ImageLabel("파란용에 오신 것을 환영합니다 :)");
	private Btn1 btnStart = new Btn1();
	//메인 프레임과 각종 판넬 들입니다. 시작화면의 기본적 UI그냥 대입시키는 방향으로 하였습니다.
	

	public assembler() {// 여기서부터 의존객체들을 조립하기 시작합니다.
		
		setEvent();//<< 버튼들을 관리하는 assembler 인 Buttons 는 모든 버튼의 필드를 가지고 있으므로 버튼이벤트를 구현하기에 적절합니다. 이 메서드로 버튼들의 이벤트를 모두 등록하고 시작합니다. 

		ProductVo g = new ProductVo();
		g.getMenuData();
		//Vo 객체를 만들어 getMenuData 메소드를 실행하고, 메소드 안에서 ProductVo클래스의 필드에 ProductVo객체로 저장됩니다.

		IP.setImagePanel(label1, btnStart);//라벨과 버튼을 동시에 받기위해 세터를 사용했습니다.
		
		mF.setPanel(IP);
		mF.setPanel(MP);
		mF.setPanel(CP);
		mF.setPanel(EP);
		//메인 프레임은 해당 판넬들에 대해서 세터안에서 instanceof 로 여부를 파악한 뒤에 조건에 맞는 Panel을  메인프레임의 필드에 주입시킵니다. 주입하는 이유는 panel을 주입받을때 메인프레임에 저장시키면 메인프레임하나로 판넬 UI이벤트를 처리할 수 있기 때문입니다.

		MP.setMainPanel(mapBtn);
		CP.setMainPanel(mapBtn);
		EP.setMainPanel(mapBtn);

		MP.setMainPanel(mapLabel);
		CP.setMainPanel(mapLabel);
		EP.setMainPanel(mapLabel);

		MP.setMainPanel(mapTextField);
		CP.setMainPanel(mapTextField);
		EP.setMainPanel(mapTextField);
		// 위에 맵핑해놓은 컴포넌트 타입 Map이 String 의 Key 값을 파악하여 조건에 따라 해당 UI를 좌표값으로 뿌리게 됩니다. 각 판넬들은 main / cart / exit 라는 키값을 가지고 있으며 맵핑객체를 넣으면 해당 키값의 컴포넌트를 판넬에 뿌립니다.
		//3개의 매핑에 키값을 두어 판넬에 맞는 UI를 뿌려줄 수 있게 됩니다.

		CP.addCart(CartTable.getTable());
		//카트판넬의 setter 인데 스크롤페인이 매개값입니다. 그렇기 때문에 CartTable의 정적메소드 getTable의 리턴값으로 만들어진 스크롤페인을 받아 등록하게 됩니다.

		btnStart.addActionListener(eventListner -> {
			EventQueue.invokeLater(() -> {
				IP.setVisible(false);
				MP.setVisible(true);
				CP.setVisible(true);
			});
		});//시작버튼은 UI를 assembler에서 다루기에 이벤트가 작성되있으며 UI 변경시 EventQueue.invokeLater에 익명구현객체를 람다로 주어 Ui를 변경합니다.

		mF.setVisible(true);//메인 프레임을 화면에 띄웁니다.
	}


}
