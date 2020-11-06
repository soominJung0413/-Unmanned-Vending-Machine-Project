package version2.TextFields;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import version2.panels.mainFrame;
import version2.project.ProductVo;


public class TextFields {
	//TextFields 는 TextField들의 객체를 맵핑하고 컨트롤하기 위한 클래스입니다.
	public static Vector<version2.project.ProductVo> ProductVector = new Vector<>();//DB로 받은 데이터를 주기위한 저장공간입니다.
	
	private static Hashtable<String, Vector<Component>> TextFileldMap = new Hashtable<>();
	//TextField의 매핑객체입니다.

	private static TextField1 textHamEgg = new TextField1();
	private static TextField2 textEggSalad = new TextField2();
	private static TextField3 textAmericano = new TextField3();
	private static TextField4 textCoke = new TextField4();
	private static TextField5 textAvocado = new TextField5();
	private static TextField6 textChicken = new TextField6();
	private static TextField7 textLatte = new TextField7();
	private static TextField8 textJuice = new TextField8();

	public static Hashtable<String, Vector<Component>> getTextFields() {
		TextFileldMap.put("main", new Vector<Component>());
		TextFileldMap.put("cart", new Vector<Component>());
		TextFileldMap.put("exit", new Vector<Component>());

		TextFileldMap.get("main").add(textHamEgg);
		TextFileldMap.get("main").add(textEggSalad);
		TextFileldMap.get("main").add(textAmericano);
		TextFileldMap.get("main").add(textCoke);
		TextFileldMap.get("main").add(textAvocado);
		TextFileldMap.get("main").add(textChicken);
		TextFileldMap.get("main").add(textLatte);
		TextFileldMap.get("main").add(textJuice);
		
		//Buttons 와 Labels와 같이 키값과 Vector<Component> 로 매핑합니다.
		return TextFileldMap;
	}

	protected static void mainBtnAction(ActionEvent e) {
		//Buttons 의 actionPerformed 재정의 과정 중  btnMap.get("main").contains(e.getSource()) 즉 main판넬의 버튼일 경우 발생하는 이벤트처리입니다.
		//버튼 이벤트 처리중에 TextField의 UI처리도 있기에 받아오게 됬습니다.
		
		try {
			for (int i = 0; i < TextFileldMap.get("main").size(); i++) {
				if (!((((JTextField) TextFileldMap.get("main").get(i)).getText()).equals(""))) {
					// main을 키로하는 Vector<Component>의 i번째 요소를 가져와 JTextFiled로 캐스팅하고 그 값의 getText() 값이 "" 빈칸이 아니라면
					Integer.valueOf(((JTextField) (TextFileldMap.get("main").get(i))).getText().toString());
					//Integer로 파싱을 시도해봅니다. 시도중에 NumberFormatException 이 발생되면 숫자가 아니니 컨버팅하지않고 초기값으로 갑니다.
				}
			}
			
			//예외가 발생하지 않았다면 해당블록으로 내려왔을거라 생각이듭니다.
			for (int i = 0; i < TextFileldMap.get("main").size(); i++) {
				if (!((((JTextField) TextFileldMap.get("main").get(i)).getText()).equals(""))) {
					//다시 for 문이 돌아 같은 조건으로 빈칸이 아닌 TextField를 확인해봅니다.
					
					saveVo((JTextField) TextFileldMap.get("main").get(i));
					//존재한다면 main을 키로하는 Vector<Component>의 i번째 요소를 가져와 JTextFiled로 캐스팅한  JButton을 매개값으로 saveVo를 호출합니다.
					
					//테이블 출력을 준비하는 preparePrintTable 의 리턴값은 String[][] 이며 그 값을 매개값으로 mainFrame의 정적메소드인 PrintTable을 호출합니다.
					mainFrame.PrintTable(preparePrintTable());
				}
			}
		} catch (NumberFormatException e1) {
			EventQueue.invokeLater(() -> {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
			});
		} finally {
			EventQueue.invokeLater(() -> {//for문을 돌리고 해당 Component 맵핑객체에 모든 텍스트를 초기화해줍니다.
				for (int i = 0; i < TextFileldMap.get("main").size(); i++) {
					((JTextField) (TextFileldMap.get("main").get(i))).setText("");
				}
			});
		}
	}

	private static void checkSaveVO(JTextField jf, int prodID) {
		//saveVo 의 진행 중에서 넘어온 JTextField와 고유 값인 ProductId값을 가지고 checkSaveVO가 실행되게 됩니다.
		
		
		for (int i = 0; i < ProductVector.size(); i++) {
		//첫번째는 데이터 베이스에서 받은 물품이 고객에 의헤 선택되어 등록되어있다면, 중복을 일으키면 테이블에 행이 늘어나게 되어 불필요하다고 느껴 ProductVector.get(i).getProdid() 즉, ProductVector 첫번째인자 값은 ProductVo 객체고 그 getProdid() 의 리턴 값은
			//매개변수로 넘어온 상품의 ProductId 값과 일치함을 가지고 해당 메뉴가 컬렉션에 존재하는지 아닌지를 판별할 수 있게 됩니다.
			
			if (ProductVector.get(i).getProdid() == prodID) {
			//만약 등록된 메뉴가 존재한다면 ProductVector 해당인자 값은 ProductVo 객체고 그 Stock 값을 조정하는데 그 매개 값으로 ProductVector 첫번째인자 값인 ProductVo 객체의 Stock 값과 매개값으로 받은 TextField에 적힌 숫자를 Integer타입으로 변환하여 더한 값을 등록합니다.
				//즉 제품의 개수의 중복시 개수만 올려줍니다.
				ProductVector.get(i).setStock(ProductVector.get(i).getStock() + Integer.valueOf(jf.getText()));
				
		//동일합니다. OldPrice 값은 판매가격이며 ProductVector 해당인자 값은 ProductVo 객체고 그 OldPrice 값을 조정하는데 그 매개 값으로 원래가격에 + 원래가격 * TextField의 숫자값을 값으로 줍니다.
				ProductVector.get(i).setOldprice(ProductVector.get(i).getOldprice()
						+ (ProductVo.ProductVoMap.get(prodID - 1).getPrice() * (Integer.valueOf(jf.getText()))));
				return;
			}
		}
		
		//원래의 데이터 베이스 값이 아니기에 원하는 값으로 초기설정해준 것입니다.
		ProductVo.ProductVoMap.get(prodID - 1).setStock(Integer.valueOf(jf.getText()));
		ProductVo.ProductVoMap.get(prodID - 1)
				.setOldprice(ProductVo.ProductVoMap.get(prodID - 1).getPrice() * (Integer.valueOf(jf.getText())));
		ProductVector.add(ProductVo.ProductVoMap.get(prodID - 1));
	}

	
	
	private static void saveVo(JTextField jf) {
		//SaveVo는 mainBtnAction에서 넘어온 매개변수와 해당 텍스트 필드값이 맞는지 확인 한 후 맞다면 데이터베이스에서 준 ProductId 값과 넘어온 JTextField를 매개값으로
		//checkSaveVO를 호출합니다. 텍스트필드를 관리하는 클래스이기에 한번에 조사할 필요가 없어 equals 의 매개값으로 필드를 줬습니다.
		if (jf.equals(textHamEgg)) {
			checkSaveVO(jf, 1);
		} else if (jf.equals(textEggSalad)) {
			checkSaveVO(jf, 3);
		} else if (jf.equals(textAmericano)) {
			checkSaveVO(jf, 5);
		} else if (jf.equals(textCoke)) {
			checkSaveVO(jf, 7);
		} else if (jf.equals(textAvocado)) {
			checkSaveVO(jf, 2);
		} else if (jf.equals(textChicken)) {
			checkSaveVO(jf, 4);
		} else if (jf.equals(textLatte)) {
			checkSaveVO(jf, 6);
		} else if (jf.equals(textJuice)) {
			checkSaveVO(jf, 8);
		}
	}

	public static String[][] preparePrintTable() {
		
		String[][] preparePrintData = new String[ProductVector.size()][5];
		for (int i = 0; i < ProductVector.size(); i++) {
			preparePrintData[i][0] = String.valueOf(ProductVector.get(i).getProdid());
			preparePrintData[i][1] = ProductVector.get(i).getName();
			preparePrintData[i][2] = String.valueOf(ProductVector.get(i).getPrice());
			preparePrintData[i][3] = String.valueOf(ProductVector.get(i).getStock());
			preparePrintData[i][4] = String.valueOf(ProductVector.get(i).getOldprice());
		}
		return preparePrintData;
	}

}
