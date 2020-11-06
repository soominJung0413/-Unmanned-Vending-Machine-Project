package version1;

public class ProductVo {
	private int prodid;		//상품번호
	private String name;	//상품명
	private int kind;		//상품 분류 (샌드위치/샐러드/커피/음료)
	private int price;		//가격
	private int oldprice;	//이전가격
	private int cost;		//원가
	private String content; //추가정보
	private int stock; 		//제고 수량
	
	public int getProdid() {return prodid;}
	public void setProdid(int prodid) {this.prodid = prodid;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getKind() {return kind;}
	public void setKind(int kind) {this.kind = kind;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public int getOldprice() {return oldprice;}
	public void setOldprice(int oldprice) {this.oldprice = oldprice;}
	public int getCost() {return cost;}
	public void setCost(int cost) {this.cost = cost;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public int getStock() {return stock;}
	public void setStock(int stock) {this.stock = stock;}

}
