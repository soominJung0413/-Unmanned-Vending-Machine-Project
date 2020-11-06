package version2.Button;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import version2.TextFields.TextFields;
import version2.panels.mainFrame;

public class Buttons extends TextFields implements ActionListener {
	private static Hashtable<String, Vector<Component>> btnMap;// 버튼의 맵핑 객체입니다. 

	private static Btn1 startBtn = new Btn1();
	protected static Btn2 btnHamEgg = new Btn2();
	protected static Btn3 btnEggSalad = new Btn3();
	protected static Btn4 btnAmericano = new Btn4();
	protected static Btn5 btnCoke = new Btn5();
	protected static Btn6 btnAvocado = new Btn6();
	protected static Btn7 btnChicken = new Btn7();
	protected static Btn8 btnLatte = new Btn8();
	protected static Btn9 btnJuice = new Btn9();
	protected static Btn10 btnPay = new Btn10();
	protected static Btn11 Confirm = new Btn11();
	//해당 버튼 클래스를 객체화 하여 필드에 선언합니다.

	public static Hashtable<String, Vector<Component>> getButtons() {
		btnMap = new Hashtable<String, Vector<Component>>();
		btnMap.put("main", new Vector<Component>());
		btnMap.put("cart", new Vector<Component>());
		btnMap.put("exit", new Vector<Component>());

		btnMap.get("main").add(btnHamEgg);
		btnMap.get("main").add(btnEggSalad);
		btnMap.get("main").add(btnAmericano);
		btnMap.get("main").add(btnCoke);
		btnMap.get("main").add(btnAvocado);
		btnMap.get("main").add(btnChicken);
		btnMap.get("main").add(btnLatte);
		btnMap.get("main").add(btnJuice);
		btnMap.get("cart").add(btnPay);
		btnMap.get("exit").add(Confirm);

		// JButton 의  맵핑객체를 리턴하는 메소드입니다. 해당 키값에 Vector<Component>를 주어 Map을 구성하고 또 그안에 JButton 객체들을 키값에 맞게 집어넣습니다. 그 후에  assembler가 호출하면 assembler의 필드로 가게됩니다.
		//리턴타입은 Hashtable<String, Vector<Component>> 입니다.
		return btnMap;
	}

	protected static void setEvent() {
		btnHamEgg.addActionListener(new Buttons());
		btnEggSalad.addActionListener(new Buttons());
		btnAmericano.addActionListener(new Buttons());
		btnCoke.addActionListener(new Buttons());
		btnAvocado.addActionListener(new Buttons());
		btnChicken.addActionListener(new Buttons());
		btnLatte.addActionListener(new Buttons());
		btnJuice.addActionListener(new Buttons());
		btnPay.addActionListener(new Buttons());
		Confirm.addActionListener(new Buttons());
		//해당 버튼의 이벤트를 등록합니다. 이 클래스 자체가 ActionListener의 구현객체 임으로 해당 클래스를 생성하여 매개값으로 전달합니다. 
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트 액션 의 구현입니다. 모든 버튼이 공유하기에 나누어줄 필요가 있습니다.  나누어지는 값은 JButton 맵핑 객체의 Key 값입니다. 
		
		if (btnMap.get("main").contains(e.getSource())) {
			mainBtnAction(e);//TextField의 assembler인 TextFields 에 있는 UI처리 메소드입니다.
			
			//Map의 Key값으로 main을 주고 그 Vector가 e.getSource 의 컴포넌트 객체를 가지고 있는지 판단하면 어느 판넬의 버튼인지 1차식별이 가능하게 됩니다.
			
		} else if (btnMap.get("cart").contains(e.getSource())) {
			if (e.getSource().equals(btnPay)) {
				mainFrame.TurnOnPay();//UI의 변경을 담당하는 mainFrame의 메서드 입니다.  버튼 이벤트에 UI 변경이 필요하다면 호출합니다.
			}

		} else if (btnMap.get("exit").contains(e.getSource())) {
			if (e.getSource().equals(Confirm)) {
				mainFrame.TurnOnMain();
			}
		}
	}

}
