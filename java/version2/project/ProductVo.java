package version2.project;

import java.util.Vector;

public class ProductVo {
	public static Vector<ProductVo> ProductVoMap = new Vector<>();

	private VendingMerchineDao vendingMachinDao = new VendingMerchineDao();//ProductVo는 DAO 객체에 의존하게 됩니다.

	private int prodId = 0;
	private String name = "";
	private int kind = 0;
	private int price = 0;
	private int oldPrice = 0;
	private int cost = 0;
	private String content = "";
	private int stock = 0;
	//해당 필드값들 입니다.


	public void getMenuData() {//assembler 의 초반에 나오는 getMenuData메서드입니다. 내부적 의존객체인 DAo를 호출하여 컬렉션에 ProductVo를 저장합니다.
		for (int i = 1; i <= 8; i++) {
			ProductVoMap.add(vendingMachinDao.getProductByProdid(i));
		}
		vendingMachinDao.dbClose();
	}

	public int getProdid() {
		return prodId;
	}

	public void setProdid(int prodid) {
		this.prodId = prodid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOldprice() {
		return oldPrice;
	}

	public void setOldprice(int oldprice) {
		this.oldPrice = oldprice;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
