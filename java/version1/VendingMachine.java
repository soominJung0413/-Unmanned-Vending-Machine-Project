package version1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VendingMachine implements ActionListener {

	CategoryVo[] category = new CategoryVo[4]; 
	ProductVo[] product = new ProductVo[8];
	
	ShoppingCart cart;
	static int orderid = 0;
	
	JFrame frame;
	ImagePanel initPanel, menuPanel, payPanel, receiptPanel;
	JButton btnEntrance;
	JButton btnCnfHamEgg, btnCnfAvocado, btnCnfEggSalad, btnCnfChicken, btnCnfAmericano, btnCnfLatte, btnCnfCoke, btnCnfJuice;
	JButton btnLogin;
	
	JTextField cntHamEgg, cntAvocado, cntEggSalad, cntChicken, cntAmericano, cntLatte, cntCoke, cntJuice;
	JTextField IdField;
	
	JPasswordField PasswordField;
	JTable tblSales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendingMachine window = new VendingMachine(); // Main class인 VendingMachine 객체 생성
					window.frame.setVisible(true);	// 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VendingMachine() {								// VendingMachine 생성자

		Dao dao = new Dao(); 								// Dao 객체생성 후 prodDao변수가 참조
		for (int i = 1; i <= 4; i++) {						// CategoryVo타입 배열인 category[]에 category 현황 대입
			category[i-1] = dao.getCategoryByKind(i*10); 
		}
		for (int i = 1; i <= 8; i++) {
			product[i-1] = dao.getProductByProdid(i);			// ProductVo타입 배열인 product[]에 product 현황 대입
		}
		cart = new ShoppingCart();							// ShoppingCart객체 생성 후 cart변수가 참조(주문별 하나 생성됨)
		initialize();										// 스윙 구성 기본 메소드인 initialized() 호출
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();								// JFrame 객체 생성
		frame.setBounds(100, 100, 1063, 651);				// JFrame 크기 설정 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Window close시 JFrame 종료 설정
		frame.getContentPane().setLayout(null);				// JFrame layout에 null을 지정하고 setBounds로 크기 설정하여 Absolute Layout이 됨
		frame.setLocationRelativeTo(null);					// JFrame이 가운데서 뜨게 설정
		frame.setResizable(false);
		
		// 초기화면인 initPanel 설정: JPanel을 상속받아 ImagePanel class를 만들었고, 배경화면으로 init.jpg가 보이도록 설정
		initPanel = new ImagePanel(new ImageIcon(getClass().getResource("image\\init.jpg")).getImage());
		initPanel.setBounds(0, 0, 1063, 651);
		frame.getContentPane().add(initPanel);				// JFrame에  initPanel 부착
		initPanel.setLayout(null);							// setBounds와 합쳐서 Absolute Layout이 되게 null로 설정
		initPanel.setVisible(true);							// 최초 수행시 initPanel만 보이도록 설정
		
		// menuPanel설정: 상품 선택 화면, ImagePanel 객체로 생성
		menuPanel = new ImagePanel(new ImageIcon(getClass().getResource("image\\init1.jpg")).getImage());
		menuPanel.setBounds(0, 0, 1063, 651);
		frame.getContentPane().add(menuPanel);				// JFrame에 menuPanel 부착
		//menuPanel.setOpaque(false);						// menuPanel을 투명하게 설정하는 것이나 현재는 필요 없음 
		menuPanel.setLayout(null);							// setBounds와 합쳐서 Absolute Layout이 되게 null로 설정
		menuPanel.setVisible(false);						// 최초 수행시 보이지 않도록 false로 설정							
		
		// payPanel설정: 결제 진행 화면, ImagePanel 객체로 생성
		payPanel = new ImagePanel(new ImageIcon(getClass().getResource("image\\init1.jpg")).getImage());
		payPanel.setBounds(0, 0, 1063, 651);
		frame.getContentPane().add(payPanel);				// JFrame에 payPanel 부착
		payPanel.setLayout(null);							// setBounds와 합쳐서 Absolute Layout이 되게 null로 설정
		payPanel.setVisible(false);							// 최초 수행시 보이지 않도록 false로 설정
		
		// receiptPanel설정: 영수증 화면, ImagePanel 객체로 생성
		receiptPanel = new ImagePanel(new ImageIcon(getClass().getResource("image\\init1.jpg")).getImage());
		receiptPanel.setBounds(0, 0, 1063, 651);
		frame.getContentPane().add(receiptPanel);			// JFrame에 receiptPanel 부착
		receiptPanel.setLayout(null);						// setBounds와 합쳐서 Absolute Layout이 되게 null로 설정
		receiptPanel.setVisible(false);						// 최초 수행시 보이지 않도록 false로 설정

		JLabel lbInit = new JLabel("Blue Dragon에 오신 것을 환영합니다"); // 초기화면 환영 인사 라벨
		lbInit.setBounds(98, 245, 870, 66);
		lbInit.setForeground(Color.BLUE);
		lbInit.setHorizontalAlignment(SwingConstants.CENTER);	// 글자를 라벨 중앙 정렬
		lbInit.setFont(new Font("맑은 고딕", Font.BOLD, 40));		// 맑은 고딕, 볼드체 및 크기 설정
		initPanel.add(lbInit);									// initPanel 부착

		btnEntrance = new JButton("주문하시려면 눌러 주세요");		// 메뉴 화면으로 진입하는 Button 설정
		btnEntrance.setBounds(335, 348, 404, 66);
		btnEntrance.setForeground(Color.BLACK);				// 글자색 파란색으로 설정
		btnEntrance.setBackground(Color.ORANGE);			// 바탕색을 오렌지색으로 설정
		btnEntrance.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 글꼴 및 크기 지정
		initPanel.add(btnEntrance);								// initPanel에 부착
		btnEntrance.addActionListener(new ActionListener() {	// Button을 누를 경우 menuPanel 켜짐
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initPanel.setVisible(false);
				menuPanel.setVisible(true);
				cart.cartPrint(menuPanel, btnEntrance);
			}
		});

		// 상품 카테고리 라벨 설정
		JLabel sand = new JLabel(category[0].getName());
		sand.setBounds(81, 37, 198, 31);
		sand.setForeground(Color.YELLOW);						// 글자색 파란색으로 설정
		sand.setHorizontalAlignment(SwingConstants.CENTER);		// 글자를 라벨 중앙 정렬
		sand.setFont(new Font("맑은 고딕", Font.BOLD, 30));			// 글꼴 및 크기 지정
		menuPanel.add(sand);									// 메뉴판넬에 부착

		JLabel salad = new JLabel(category[1].getName());
		salad.setBounds(319, 37, 198, 31);
		salad.setForeground(Color.YELLOW);
		salad.setHorizontalAlignment(SwingConstants.CENTER);
		salad.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		menuPanel.add(salad);

		JLabel coffee = new JLabel(category[2].getName());
		coffee.setBounds(563, 37, 198, 31);
		coffee.setForeground(Color.BLACK);
		coffee.setHorizontalAlignment(SwingConstants.CENTER);
		coffee.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		menuPanel.add(coffee);

		JLabel drink = new JLabel(category[3].getName());
		drink.setBounds(813, 37, 198, 31);
		drink.setForeground(Color.BLACK);
		drink.setHorizontalAlignment(SwingConstants.CENTER);
		drink.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		menuPanel.add(drink);
		
		// 햄에그샌드위치 메뉴 생성
		JLabel hamegg = new JLabel("");							// 그림부착 위해  글자는 입력하지 않음
		hamegg.setBounds(121, 81, 113, 74);
		hamegg.setIcon(new ImageIcon(getClass().getResource("image\\Hamegg.jpg")));// JLabel에 상품그림부착
		menuPanel.add(hamegg);									// menuPanel에 부착
		
		JLabel lbHamEgg = new JLabel(product[0].getName());				// 상품명 라벨 생성
		lbHamEgg.setBounds(14, 95, 117, 18);					// 위치 및 크기 설정
		lbHamEgg.setForeground(Color.YELLOW);					// 글자색 노란색 설정
		lbHamEgg.setFont(new Font("맑은 고딕", Font.BOLD, 15));		// 글자체 및 크기 설정
		menuPanel.add(lbHamEgg);								// memuPanel 부착
		
		JLabel hameggPrice = new JLabel(product[0].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		hameggPrice.setBounds(25, 120, 68, 18);
		hameggPrice.setForeground(Color.YELLOW);
		hameggPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		hameggPrice.setHorizontalAlignment(SwingConstants.RIGHT); // 글자 정렬
		menuPanel.add(hameggPrice);

		JLabel won1 = new JLabel("원");
		won1.setBounds(101, 120, 37, 18);
		won1.setForeground(Color.YELLOW);
		won1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won1);

		cntHamEgg = new JTextField();
		cntHamEgg.setBounds(194, 157, 40, 24);
		cntHamEgg.setHorizontalAlignment(SwingConstants.CENTER);
		cntHamEgg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntHamEgg);

		btnCnfHamEgg = new JButton("확인");
		btnCnfHamEgg.setBounds(121, 185, 105, 27);
		btnCnfHamEgg.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfHamEgg);

		JLabel lbHamEggCnt = new JLabel("수량입력");
		lbHamEggCnt.setBounds(125, 157, 62, 24);
		lbHamEggCnt.setForeground(Color.YELLOW);
		lbHamEggCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbHamEggCnt);
		
		// 아보카도 샌드위치 메뉴 생성
		JLabel avocado = new JLabel("");
		avocado.setBounds(121, 237, 113, 74);
		avocado.setIcon(new ImageIcon(getClass().getResource("image\\Avocado.jpg")));
		avocado.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(avocado);
		
		JLabel lbAvocado = new JLabel(product[1].getName());
		lbAvocado.setBounds(0, 249, 131, 18);
		lbAvocado.setForeground(Color.YELLOW);
		lbAvocado.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbAvocado);
		
		JLabel lbAvocadoPrice = new JLabel(product[1].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbAvocadoPrice.setBounds(25, 276, 68, 18);
		lbAvocadoPrice.setForeground(Color.BLACK);
		lbAvocadoPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAvocadoPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbAvocadoPrice);

		JLabel won2 = new JLabel("원");
		won2.setBounds(101, 276, 37, 18);
		won2.setForeground(Color.BLACK);
		won2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won2);

		cntAvocado = new JTextField();
		cntAvocado.setBounds(194, 313, 40, 24);
		cntAvocado.setHorizontalAlignment(SwingConstants.CENTER);
		cntAvocado.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntAvocado);

		btnCnfAvocado = new JButton("확인");
		btnCnfAvocado.setBounds(121, 338, 105, 27);
		btnCnfAvocado.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfAvocado);

		JLabel lbAvocadoCnt = new JLabel("수량입력");
		lbAvocadoCnt.setBounds(125, 310, 62, 24);
		lbAvocadoCnt.setForeground(Color.BLACK);
		lbAvocadoCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbAvocadoCnt);
		
		// 에그샐러드 메뉴 생성
		JLabel eggsalad = new JLabel("");
		eggsalad.setBounds(363, 80, 113, 74);
		eggsalad.setIcon(new ImageIcon(getClass().getResource("image\\Eggsalad.jpg")));
		eggsalad.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(eggsalad);
		
		JLabel lbEggSald = new JLabel(product[2].getName());
		lbEggSald.setBounds(278, 97, 117, 18);
		lbEggSald.setForeground(Color.BLACK);
		lbEggSald.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbEggSald);
		
		JLabel lbEggSalPrice = new JLabel(product[2].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbEggSalPrice.setBounds(267, 120, 68, 18);
		lbEggSalPrice.setForeground(Color.BLACK);
		lbEggSalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbEggSalPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbEggSalPrice);

		JLabel won3 = new JLabel("원");
		won3.setBounds(343, 120, 37, 18);
		won3.setForeground(Color.BLACK);
		won3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won3);

		cntEggSalad = new JTextField();
		cntEggSalad.setBounds(436, 157, 40, 24);
		cntEggSalad.setHorizontalAlignment(SwingConstants.CENTER);
		cntEggSalad.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntEggSalad);

		btnCnfEggSalad = new JButton("확인");
		btnCnfEggSalad.setBounds(363, 185, 105, 27);
		btnCnfEggSalad.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfEggSalad);

		JLabel lbEggSalCnt = new JLabel("수량입력");
		lbEggSalCnt.setBounds(367, 157, 62, 24);
		lbEggSalCnt.setForeground(Color.YELLOW);
		lbEggSalCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbEggSalCnt);

		// 치킨샐러드 메뉴 생성
		JLabel chikensalad = new JLabel("");
		chikensalad.setBounds(363, 237, 113, 74);
		chikensalad.setIcon(new ImageIcon(getClass().getResource("image\\Chickensalad.jpg")));
		chikensalad.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(chikensalad);
		
		JLabel lbChickSal = new JLabel(product[3].getName());
		lbChickSal.setBounds(278, 246, 117, 18);
		lbChickSal.setForeground(Color.YELLOW);
		lbChickSal.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbChickSal);
		
		JLabel lbChickPrice = new JLabel(product[3].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbChickPrice.setBounds(267, 276, 68, 18);
		lbChickPrice.setForeground(Color.BLACK);
		lbChickPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbChickPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbChickPrice);

		JLabel won4 = new JLabel("원");
		won4.setBounds(343, 276, 37, 18);
		won4.setForeground(Color.BLACK);
		won4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won4);
		
		cntChicken = new JTextField();
		cntChicken.setBounds(436, 313, 40, 24);
		cntChicken.setHorizontalAlignment(SwingConstants.CENTER);
		cntChicken.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntChicken);

		btnCnfChicken = new JButton("확인");
		btnCnfChicken.setBounds(363, 338, 105, 27);
		btnCnfChicken.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfChicken);

		JLabel lbChickSalad = new JLabel("수량입력");
		lbChickSalad.setBounds(367, 310, 62, 24);
		lbChickSalad.setForeground(Color.BLACK);
		lbChickSalad.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbChickSalad);
		
		// 아메리카노 메뉴 생성
		JLabel americano = new JLabel("");
		americano.setBounds(604, 80, 113, 74);
		americano.setIcon(new ImageIcon(getClass().getResource("image\\Americano.jpg")));
		americano.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(americano);
		
		JLabel lbAmericano = new JLabel(product[4].getName());
		lbAmericano.setBounds(527, 97, 117, 18);
		lbAmericano.setForeground(Color.BLACK);
		lbAmericano.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbAmericano);
		
		JLabel lbAmericanoPrice = new JLabel(product[4].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbAmericanoPrice.setBounds(508, 120, 68, 18);
		lbAmericanoPrice.setForeground(Color.BLACK);
		lbAmericanoPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAmericanoPrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbAmericanoPrice);
		
		JLabel won5 = new JLabel("원");
		won5.setBounds(584, 120, 37, 18);
		won5.setForeground(Color.BLACK);
		won5.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won5);

		cntAmericano = new JTextField();
		cntAmericano.setBounds(677, 157, 40, 24);
		cntAmericano.setHorizontalAlignment(SwingConstants.CENTER);
		cntAmericano.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntAmericano);

		btnCnfAmericano = new JButton("확인");
		btnCnfAmericano.setBounds(604, 185, 105, 27);
		btnCnfAmericano.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfAmericano);

		JLabel lbAmericanoCnt = new JLabel("수량입력");
		lbAmericanoCnt.setBounds(608, 157, 62, 24);
		lbAmericanoCnt.setForeground(Color.BLACK);
		lbAmericanoCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbAmericanoCnt);

		// 라떼 메뉴 생성
		JLabel latte = new JLabel("");
		latte.setBounds(604, 237, 113, 74);
		latte.setIcon(new ImageIcon(getClass().getResource("image\\Latte.jpg")));
		latte.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(latte);
		
		JLabel lbLatte = new JLabel(product[5].getName());
		lbLatte.setBounds(527, 246, 76, 18);
		lbLatte.setForeground(Color.BLACK);
		lbLatte.setHorizontalAlignment(SwingConstants.CENTER);
		lbLatte.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbLatte);
		
		JLabel lbLattePrice = new JLabel(product[5].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbLattePrice.setBounds(508, 276, 68, 18);
		lbLattePrice.setForeground(Color.BLACK);
		lbLattePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbLattePrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbLattePrice);

		JLabel won6 = new JLabel("원");
		won6.setBounds(584, 276, 37, 18);
		won6.setForeground(Color.BLACK);
		won6.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won6);
		
		cntLatte = new JTextField();
		cntLatte.setBounds(677, 313, 40, 24);
		cntLatte.setHorizontalAlignment(SwingConstants.CENTER);
		cntLatte.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntLatte);

		btnCnfLatte = new JButton("확인");
		btnCnfLatte.setBounds(604, 338, 105, 27);
		btnCnfLatte.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfLatte);

		JLabel lbLatteCnt = new JLabel("수량입력");
		lbLatteCnt.setBounds(608, 310, 62, 24);
		lbLatteCnt.setForeground(Color.BLACK);
		lbLatteCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbLatteCnt);
		
		
		// 콜라 메뉴 생성
		JLabel coke = new JLabel("");
		coke.setBounds(844, 80, 113, 74);
		coke.setIcon(new ImageIcon(getClass().getResource("image\\Coke.jpg")));
		coke.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(coke);
		
		JLabel lbCoke = new JLabel(product[6].getName());
		lbCoke.setBounds(768, 97, 76, 18);
		lbCoke.setForeground(Color.YELLOW);
		lbCoke.setHorizontalAlignment(SwingConstants.CENTER);
		lbCoke.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbCoke);
		
		JLabel lbCokePrice = new JLabel(product[6].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbCokePrice.setBounds(748, 120, 68, 18);
		lbCokePrice.setForeground(Color.YELLOW);
		lbCokePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbCokePrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbCokePrice);
		
		JLabel won7 = new JLabel("원");
		won7.setBounds(824, 120, 37, 18);
		won7.setForeground(Color.YELLOW);
		won7.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won7);

		cntCoke = new JTextField();
		cntCoke.setBounds(917, 157, 40, 24);
		cntCoke.setHorizontalAlignment(SwingConstants.CENTER);
		cntCoke.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntCoke);

		btnCnfCoke = new JButton("확인");
		btnCnfCoke.setBounds(844, 185, 105, 27);
		btnCnfCoke.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfCoke);

		JLabel lbCokeCnt = new JLabel("수량입력");
		lbCokeCnt.setBounds(848, 157, 62, 24);
		lbCokeCnt.setForeground(Color.YELLOW);
		lbCokeCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbCokeCnt);
		
		// 쥬스메뉴 생성
		JLabel juice = new JLabel("");
		juice.setBounds(844, 237, 113, 74);
		juice.setIcon(new ImageIcon(getClass().getResource("image\\Juice.jpg")));
		juice.setHorizontalAlignment(SwingConstants.CENTER);
		menuPanel.add(juice);
		
		JLabel lbJuice = new JLabel(product[7].getName());
		lbJuice.setBounds(768, 246, 76, 18);
		lbJuice.setForeground(Color.BLACK);
		lbJuice.setHorizontalAlignment(SwingConstants.CENTER);
		lbJuice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbJuice);

		JLabel lbJuicePrice = new JLabel(product[7].getPrice()+""); // ProductVo객체인 product[]의 price를 거져와 라벨값으로 설정
		lbJuicePrice.setBounds(748, 276, 68, 18);
		lbJuicePrice.setForeground(Color.BLACK);
		lbJuicePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lbJuicePrice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbJuicePrice);

		JLabel won8 = new JLabel("원");
		won8.setBounds(824, 276, 37, 18);
		won8.setForeground(Color.BLACK);
		won8.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(won8);

		cntJuice = new JTextField();
		cntJuice.setBounds(917, 313, 40, 24);
		cntJuice.setHorizontalAlignment(SwingConstants.CENTER);
		cntJuice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(cntJuice);

		btnCnfJuice = new JButton("확인");
		btnCnfJuice.setBounds(844, 338, 105, 27);
		btnCnfJuice.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(btnCnfJuice);

		JLabel lbJuiceCnt = new JLabel("수량입력");
		lbJuiceCnt.setBounds(848, 310, 62, 24);
		lbJuiceCnt.setForeground(Color.BLACK);
		lbJuiceCnt.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuPanel.add(lbJuiceCnt);
		
		// menuPanel에서 상품을 선택("확인"버튼)할 경우 아래 ActionListener를 통해 actionPerformed메소드가 호출됨
		btnCnfHamEgg.addActionListener(this);
		btnCnfAvocado.addActionListener(this);
		btnCnfEggSalad.addActionListener(this);
		btnCnfChicken.addActionListener(this);
		btnCnfAmericano.addActionListener(this);
		btnCnfLatte.addActionListener(this);
		btnCnfCoke.addActionListener(this);
		btnCnfJuice.addActionListener(this);
		
		JLabel lbCart = new JLabel("장바구니");
		lbCart.setBounds(14, 426, 149, 40);
		lbCart.setForeground(Color.BLACK);
		lbCart.setHorizontalAlignment(SwingConstants.CENTER);
		lbCart.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		menuPanel.add(lbCart);
		
		JButton btnGoInitFromCart = new JButton("처음으로 돌아가기"); // 처음으로 돌아가기를 누를 경우 초기화 됨
		btnGoInitFromCart.setForeground(Color.BLACK);
		btnGoInitFromCart.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnGoInitFromCart.setBounds(140, 549, 201, 27);
		menuPanel.add(btnGoInitFromCart);
		btnGoInitFromCart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							VendingMachine window = new VendingMachine();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
		});
		
		JButton btnPay = new JButton("결제하기");
		btnPay.setForeground(Color.BLACK);
		btnPay.setBounds(376, 549, 143, 27);
		btnPay.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		menuPanel.add(btnPay);
		btnPay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cart.cartTot == 0) {					// 장바구니가 비어 있는데 결제하기를 누를 경우 팝업메시지 표시
					JOptionPane.showMessageDialog(null, "상품을 선택하시기 바랍니다");
					return;
				} else {
					menuPanel.setVisible(false);			// 장바구니에 상품이 담겨 있을 경우  menuPanel을 끄고 
					payPanel.setVisible(true);				// 결제를 위해 payPanel을 염
					cart.cartPrint(payPanel, btnPay);		// 장바구니 상품을 계산하여 Table형식으로 Print
				}
			}
		});

		JButton btnCancel = new JButton("취소");
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnCancel.setBounds(548, 549, 143, 27);
		menuPanel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {	// 취소하기를 누를 경우, 장바구니 클리어 및 클리어 된 내용 확인 위해 Print
			
			@Override
			public void actionPerformed(ActionEvent e) {	// setText를 통해 남아 있는 수량을 지움
				cart.items.clear();
				cart.cartPrint(menuPanel, btnCancel);
				cntHamEgg.setText("");cntAvocado.setText("");cntEggSalad.setText(""); cntChicken.setText("");
				cntAmericano.setText(""); cntLatte.setText("");cntCoke.setText("");cntJuice.setText("");
			}
			
		});
		
		JLabel lbltotSum = new JLabel("총계");
		lbltotSum.setBounds(730, 543, 92, 37);
		lbltotSum.setForeground(Color.BLACK);
		lbltotSum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbltotSum.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(lbltotSum);
				
		JLabel lbPay = new JLabel("결제하기");
		lbPay.setForeground(Color.BLUE);
		lbPay.setHorizontalAlignment(SwingConstants.CENTER);
		lbPay.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lbPay.setBounds(477, 112, 143, 27);
		payPanel.add(lbPay);
		
		JButton payConfirm = new JButton("결제확인");
		payConfirm.setForeground(Color.BLACK);
		payConfirm.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		payConfirm.setBounds(477, 441, 143, 27);
		payPanel.add(payConfirm);
		payConfirm.addActionListener(new ActionListener() { // payPanel에서 결제확인버튼을 누를 경우
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "결재가 완료되었습니다");
				payPanel.setVisible(false);					// payPanel 끄고
				receiptPanel.setVisible(true);				// receiptPanel 열고
				cart.cartPrint(receiptPanel, payConfirm);	// 결제한 상품 및 금액 보여 줌
				orderid++;									// Static 변수인 orderid 증가 시킴
				Dao db = new Dao();							// DB의 ordered테이블 삽입 및 product테이블 업데이트를 위해 DB연결
				int prodid = 0, count = 0;
				for (int i = 0; i < cart.items.size(); i++) {	// 장바구니에 있는 상품갯수 까지 for문을 돌려
					prodid = cart.items.get(i).getProdid();
					count = cart.items.get(i).getCount();
					db.insertOrdered(orderid, prodid, count, "CASH");//ordered테이블에 주문내용 삽입
					db.updateStock(prodid, product[prodid - 1].getStock() - count); //product테이블의 재고량 감소시킴
				}
			}
		});
		
		JLabel lbReceipt = new JLabel("영수증");
		lbReceipt.setForeground(Color.BLUE);
		lbReceipt.setHorizontalAlignment(SwingConstants.CENTER);
		lbReceipt.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lbReceipt.setBounds(476, 112, 143, 27);
		receiptPanel.add(lbReceipt);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy년 MM월 dd일 hh시 mm분 ss초");
		JLabel lbDate = new JLabel(sdf.format(now));
		lbDate.setForeground(Color.YELLOW);
		lbDate.setHorizontalAlignment(SwingConstants.CENTER);
		lbDate.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		lbDate.setBounds(700, 125, 200, 27);
		receiptPanel.add(lbDate);
		
		JButton btngoBackOrder = new JButton("처음으로 돌아가기");
		btngoBackOrder.setForeground(Color.BLACK);
		btngoBackOrder.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btngoBackOrder.setBounds(476, 539, 170, 27);
		receiptPanel.add(btngoBackOrder);
		btngoBackOrder.addActionListener(new ActionListener() { // 처음으로 돌아가기를 누를 경우 초기화됨

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							VendingMachine window = new VendingMachine();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

		});
		
		JLabel lblGoodbye = new JLabel("이용해 주셔서 감사합니다");
		lblGoodbye.setForeground(Color.BLUE);
		lblGoodbye.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblGoodbye.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoodbye.setBounds(230, 401, 639, 96);
		receiptPanel.add(lblGoodbye);
		

		///////////////////////////////////////////////////// Login 페이지 시작
		ImagePanel LoginPanel = new ImagePanel(new ImageIcon(getClass().getResource("image\\init.jpg")).getImage());
		LoginPanel.setBackground(Color.WHITE);
		LoginPanel.setBounds(0, 0, 1047, 622);
		frame.getContentPane().add(LoginPanel);
		LoginPanel.setLayout(null);
		LoginPanel.setVisible(false);
		
		ImagePanel managerPanel = new ImagePanel(new ImageIcon(getClass().getResource("image\\Bluedragon.jpg")).getImage());
		managerPanel.setBounds(0, 0, 1063, 651);
		frame.getContentPane().add(managerPanel);
		managerPanel.setLayout(null);
		managerPanel.setVisible(false);
		
		// 초기화면의 "관리자로그인" 버튼 설정
		btnLogin = new JButton("관리자로그인");					// 관리자로그인 Button 객체 생성
		btnLogin.setForeground(Color.YELLOW);				// 글자색 노란색으로 설정
		btnLogin.setBackground(Color.BLUE);					// 바탕색 파란색으로 설정 
		btnLogin.setBounds(35, 20, 168, 41);
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 20));// 글꼴 및 크기 지정
		initPanel.add(btnLogin);							// initPanel에 부착
		btnLogin.setFocusPainted(false);					// 버튼 Focus 비활성화
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				payPanel.setVisible(false);
				initPanel.setVisible(false);
				LoginPanel.setVisible(true);

			}

		});

		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		lblLogIn.setBounds(270, 346, 288, 62);
		LoginPanel.add(lblLogIn);

		JLabel lblId = new JLabel("ID: ");
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblId.setBounds(191, 418, 66, 43);
		LoginPanel.add(lblId);
		
		IdField = new JTextField();
		IdField.setFont(new Font("굴림", Font.PLAIN, 22));
		IdField.setBounds(270, 418, 288, 43);
		LoginPanel.add(IdField);

		JLabel lblPw = new JLabel("PW: ");
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblPw.setBounds(191, 482, 66, 43);
		LoginPanel.add(lblPw);
		
		PasswordField = new JPasswordField();
		PasswordField.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		PasswordField.setBounds(270, 482, 288, 43);
		LoginPanel.add(PasswordField);

		JButton Loginbtn = new JButton("로그인");
		Loginbtn.setForeground(new Color(255, 255, 224));
		Loginbtn.setBackground(new Color(0, 0, 139));
		Loginbtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		Loginbtn.setBounds(579, 463, 133, 62);
		LoginPanel.add(Loginbtn);
		Loginbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Dao d = new Dao();
				if (d.confirmManagerID(IdField.getText(), new String(PasswordField.getPassword())) == 1) {
					LoginPanel.setVisible(false);
					managerPanel.setVisible(true);
					JOptionPane.showMessageDialog(null, "로그인 성공");
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
			}

		});

		JButton btnGoInit = new JButton("처음화면으로 돌아가기");
		btnGoInit.setForeground(new Color(255, 255, 224));
		btnGoInit.setBackground(new Color(0, 0, 139));
		btnGoInit.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		btnGoInit.setBounds(12, 22, 267, 41);
		LoginPanel.add(btnGoInit);
		btnGoInit.setFocusPainted(false);					// 버튼 Focus 비활성화
		btnGoInit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							VendingMachine window = new VendingMachine();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

		});

		////////////////////////////////////////////////// Login 페이지 끝
		//생성자 및 안에 넣을 값 불러오기.
		Dao d = new Dao();         
		Vector col = new Vector();
		col.add("품번");
		col.add("품명");
		col.add("판매수량");
		col.add("매출");
		col.add("마진");
		col.add("재고량");
				
		DefaultTableModel model = new DefaultTableModel(d.getSales(), col);

		tblSales = new JTable(model); // 여기에 데이터값을 집어넣었음.
		//스크롤팬
		JScrollPane scrollPane = new JScrollPane(tblSales); // 여기에 테이블을 집어넣음.
		scrollPane.setBounds(114, 182, 803, 279);
		managerPanel.add(scrollPane);
		tableSalesSet();
		
		//매출이라는 라벨
		JLabel lbSales = new JLabel("매출"); 
		lbSales.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lbSales.setBounds(484, 107, 130, 57);
		managerPanel.add(lbSales);
				
		// 돌아가는 버튼
		JButton btnGoInitFromSales = new JButton("처음화면으로 돌아가기");
		btnGoInitFromSales.setForeground(new Color(255, 255, 224));
		btnGoInitFromSales.setFont(new Font("맑은 고딕", Font.BOLD, 21));
		btnGoInitFromSales.setBackground(new Color(0, 0, 139));
		btnGoInitFromSales.setBounds(0, 0, 267, 57);
		managerPanel.add(btnGoInitFromSales);
		btnGoInitFromSales.setFocusPainted(false);

		
		//돌아가는 버튼 액션
		btnGoInitFromSales.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							VendingMachine window = new VendingMachine();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

		});
		
	}
	
	public void tableSalesSet() {
		// 이동과 길이조절 여러개 선택 되는 것을 방지한다
		tblSales.getTableHeader().setReorderingAllowed(false);
		tblSales.getTableHeader().setResizingAllowed(false);
		tblSales.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// 컬럼 정렬에 필요한 메서드
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);

		// 컬럼별 사이즈 조절 & 정렬
		tblSales.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblSales.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		tblSales.getColumnModel().getColumn(1).setPreferredWidth(10);
		tblSales.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		tblSales.getColumnModel().getColumn(2).setPreferredWidth(10);
		tblSales.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		tblSales.getColumnModel().getColumn(3).setPreferredWidth(10);
		tblSales.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
		tblSales.getColumnModel().getColumn(4).setPreferredWidth(20);
		tblSales.getColumnModel().getColumn(4).setCellRenderer(celAlignCenter);
		tblSales.getColumnModel().getColumn(5).setPreferredWidth(20);
		tblSales.getColumnModel().getColumn(5).setCellRenderer(celAlignCenter);
	} // jTableRefresh : 테이블 옵션 설정하는 메서드

	@Override
	public void actionPerformed(ActionEvent e) { // menuPanel에서 상품수량적고 "확인"버튼 클릭시 수행
		int count = 0;
		Item item = new Item();					// 상품객체 생성
		JButton btn = (JButton) e.getSource();	// 버튼 source를 btn변수로 참조
		int prodid = 0;

		if (btn.equals(btnCnfHamEgg)) {			// 헴에그샌드위치 버튼일 경우
			prodid = 1;							// 상품번호에 1 대입
			try {
				count = Integer.parseInt(cntHamEgg.getText()); // 수량을 파싱하여 count 변수에 대입
				item.setCount(count);			// 상품객체의 count변수에 수량 입력	
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다."); // 문자를 적거나 비어있을 경우 Exception에러 발생
				cntHamEgg.setText("");			// 햄에그샌드위치의 수량 적는 란에 "" 표시하고
				return;							// return함
			}
		} else if (btn.equals(btnCnfAvocado)) { // 이하 유사함
			prodid = 2;
			try {
				count = Integer.parseInt(cntAvocado.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntAvocado.setText("");
				return;
			}
		} else if (btn.equals(btnCnfEggSalad)) {
			prodid = 3;
			try {
				count = Integer.parseInt(cntEggSalad.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntEggSalad.setText("");
				return;
			}
		} else if (btn.equals(btnCnfChicken)) {
			prodid = 4;
			try {
				count = Integer.parseInt(cntChicken.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntChicken.setText("");
				return;
			}
		} else if (btn.equals(btnCnfAmericano)) {
			prodid = 5;
			try {
				count = Integer.parseInt(cntAmericano.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntAmericano.setText("");
				return;
			}
		} else if (btn.equals(btnCnfLatte)) {
			prodid = 6;
			try {
				count = Integer.parseInt(cntLatte.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntLatte.setText("");
				return;
			}
		} else if (btn.equals(btnCnfCoke)) {
			prodid = 7;
			try {
				count = Integer.parseInt(cntCoke.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntCoke.setText("");
				return;
			}
		} else if (btn.equals(btnCnfJuice)) {
			prodid = 8;
			try {
				count = Integer.parseInt(cntJuice.getText());
				item.setCount(count);
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "수량이 제대로 입력되지 않았습니다.");
				cntJuice.setText("");
				return;
			}
		}

		//상품객체를 참조하는 item변수를 통해 상품객체에 다른 데이터들을 저장
		item.setProdid(prodid);
		item.setName(product[prodid - 1].getName());
		item.setPrice(product[prodid - 1].getPrice());
		item.setItemTotPrice(product[prodid - 1].getPrice() * count);

		// 장바구니에 동일한 상품이 있을 경우 기존 상품객체의 수량 및 상품가격만을 업데이트하고, 동일한 상품이 없을 경우 현재의 객체를 장바구니에 add함
		boolean found = false;

		for (int i = 0; i < cart.items.size(); i++) {
			
			if (prodid == cart.items.get(i).getProdid()) {
				found = true;
				cart.items.get(i).setCount(cart.items.get(i).getCount() + item.getCount());
				cart.items.get(i).setItemTotPrice(cart.items.get(i).getItemTotPrice() + item.getItemTotPrice());
			}
		}

		if (!found)	cart.items.add(item); // 장바구니에 동일한 상품이 없을 경우 add
		cart.cartPrint(menuPanel,btn);	// 장바구니 내용 Print
	}
}

class ImagePanel extends JPanel { // JPanel 상속받아 개인적인 ImagePanel class 만듬
	private Image img;

	public ImagePanel(Image img) { // jpg이미지를 받아 ImagePanel 객체를 생성하는 생성자
		this.img = img;
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null))); // size정하기, 불러온 img의 최대갑으로 설정
		setLayout(null);
	}

	public int getWidth() {return img.getWidth(null);}

	public int getHeight() {return img.getHeight(null);}
	
	@Override
	public void paintComponent(Graphics g) { // JPanel의 함수를 재정의. Background image를 넣을 수 있게 해 줌
		g.drawImage(img, 0, 0, null); 		// g.drawImage(img,x,y,image observer)
	}
}

class Item {	// menuPanel에서 상품수량 적고 확인 버튼 누를때 생성되는 VO Class임
	private int prodid;
	private String name;
	private int price;
	private int count;
	private int itemTotPrice;

	public int getProdid() {return prodid;}
	public void setProdid(int prodid) {this.prodid = prodid;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}
	public int getItemTotPrice() {return itemTotPrice;}
	public void setItemTotPrice(int itemTotPrice) {this.itemTotPrice = itemTotPrice;}
}

class ShoppingCart {	// 최초에 VendingMachine class실행시 생성되는 장바구니겍체를 만드는 class로 Item객체가 생성되면 뒤이어 장바구니객체에 저장됨 
	ArrayList<Item> items = new ArrayList<>(); // 상품 선택시  items라는 ArrayList 참조변수에 add됨
	int cartTot;	// 장바구니 총 금액을 계산하기 위한 필드
	JTable table;	// 장바구니 내용을 print할때 사용하기 위한 JTable 필드

	public String[][] getItemList() {	// 외부에서 getItemList()메소드 호출시 ArrayList에 있는 Item객체들을 String배열로 바꿔서 return해 줌
		String[][] itemList = new String[items.size()][5];
		cartTot = 0;
		for (int i = 0; i < items.size(); i++) {
			itemList[i][0] = items.get(i).getProdid() + "";
			itemList[i][1] = items.get(i).getName();
			itemList[i][2] = items.get(i).getPrice() + "";
			itemList[i][3] = items.get(i).getCount() + "";
			itemList[i][4] = items.get(i).getItemTotPrice() + "";
			cartTot += items.get(i).getItemTotPrice();
		}
		return itemList;
	}

	public void cartPrint(JPanel panel, JButton btn) { // 장바구니에 있는 item객체들을 JTable형식으로 보여 줌
		String[] headings = new String[] { "품번", "품명", "단위가격", "수량", "상품가격" };
		String[][] cartItems =this.getItemList();
		int yPosition = 150;
		
		if(btn.getText().equals("확인") || btn.getText().equals("취소") || btn.getText().equals("주문하시려면 눌러 주세요")) {
			yPosition = 377;
		}
		
		DefaultTableModel model = new DefaultTableModel(cartItems, headings) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model); // (table 레코드, columns 제목)
		//table = new JTable(cartItems, headings);
		table.setSize(780, 150);
		// table.setPreferredScrollableViewportSize(new Dimension(780, 150));
		jTableSet();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(true);
		scrollPane.setBounds(140, yPosition+12, 780, 150);
		
		panel.add(scrollPane); // JScrollpane을 해야 앞에 추가했던 것들이 적용됨

		JLabel lbltotSum = new JLabel("총계");
		lbltotSum.setBounds(730, yPosition+166, 92, 37);
		lbltotSum.setForeground(Color.YELLOW);
		lbltotSum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbltotSum.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lbltotSum);

		JTextField totSum = new JTextField();
		totSum.setHorizontalAlignment(SwingConstants.RIGHT);
		totSum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		totSum.setBounds(780, yPosition+174, 116, 24);
		panel.add(totSum);
		totSum.setText(this.cartTot + "");

	}

	public void jTableSet() { // JTable 정렬
		// 이동과 길이조절 여러개 선택 되는 것을 방지한다
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// 컬럼 정렬에 필요한 메서드
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);

		// 컬럼별 사이즈 조절 & 정렬
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setCellRenderer(celAlignCenter);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setCellRenderer(celAlignCenter);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setCellRenderer(celAlignCenter);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setCellRenderer(celAlignCenter);
	} // jTableRefresh : 테이블 옵션 설정하는 메서드
}