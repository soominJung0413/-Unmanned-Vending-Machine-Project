package version2.Labels;

import java.awt.Component;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public class labels {
	private static Hashtable<String, Vector<Component>> labelMap = new Hashtable<>();
	//라벨의 assembler 인 labels 입니다. 객체 매핑외에 특별한 점은 없습니다. 똑같이 정적메소드인 getter로 assembler에 맵핑한 컬렉션을 제공합니다.
	
	public static Hashtable<String, Vector<Component>> getLabels() {
		labelMap.put("main", new Vector<Component>());
		labelMap.put("cart", new Vector<Component>());
		labelMap.put("exit", new Vector<Component>());
		
		labelMap.get("main").add(new label1());
		labelMap.get("main").add(new label2());
		labelMap.get("main").add(new label3());
		labelMap.get("main").add(new Label4());
		labelMap.get("main").add(new label5());
		labelMap.get("main").add(new label6());
		labelMap.get("main").add(new label7());
		labelMap.get("main").add(new label8());
		labelMap.get("main").add(new label9());
		labelMap.get("main").add(new label10());
		labelMap.get("main").add(new label11());
		labelMap.get("main").add(new label12());
		labelMap.get("main").add(new label13());
		labelMap.get("main").add(new label14());
		labelMap.get("main").add(new label15());
		labelMap.get("main").add(new label16());
		labelMap.get("main").add(new label17());
		labelMap.get("main").add(new label18());
		labelMap.get("main").add(new label19());
		labelMap.get("main").add(new label20());
		labelMap.get("main").add(new label21());
		labelMap.get("main").add(new label22());
		labelMap.get("main").add(new label23());
		labelMap.get("main").add(new label24());
		labelMap.get("main").add(new label25());
		labelMap.get("main").add(new label26());
		labelMap.get("main").add(new label27());
		labelMap.get("main").add(new label28());
		labelMap.get("main").add(new label29());
		labelMap.get("main").add(new label30());
		labelMap.get("main").add(new label31());
		labelMap.get("main").add(new label32());
		labelMap.get("main").add(new label33());
		labelMap.get("main").add(new label34());
		labelMap.get("main").add(new label35());
		labelMap.get("main").add(new label36());
		labelMap.get("main").add(new label37());
		labelMap.get("main").add(new label38());
		labelMap.get("main").add(new label39());
		labelMap.get("main").add(new label40());
		labelMap.get("main").add(new label41());
		labelMap.get("main").add(new label42());
		labelMap.get("main").add(new label43());
		labelMap.get("main").add(new label44());
		
		
		
		labelMap.get("cart").add( new label45());
		labelMap.get("exit").add(new label46());
		labelMap.get("exit").add(new label47());
		
		return  labelMap;
	}
}
