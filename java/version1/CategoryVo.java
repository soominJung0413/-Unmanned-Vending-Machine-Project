package version1;

public class CategoryVo {
	int kind;		//상품분류: 샌드위치(10)/샐러드(20)/커피(30)/음료(40)
	String name;	//분류명: 분류명 샌드위치/샐러드/커피/음료
	String detail;	//추가설명
	
	public int getKind() {return kind;}
	public void setKind(int kind) {this.kind = kind;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getDetail() {return detail;}
	public void setDetail(String detail) {this.detail = detail;}
}
