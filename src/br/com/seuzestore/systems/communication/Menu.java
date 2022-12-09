
package br.com.seuzestore.systems.communication;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.LinkedHashMap;

import java.util.Map;
import java.util.Scanner;

import br.com.seuzestore.systems.model.CPF;
import br.com.seuzestore.systems.model.Cart;
import br.com.seuzestore.systems.model.CategoryEnum;
import br.com.seuzestore.systems.model.CollorEnum;
import br.com.seuzestore.systems.model.DepartmentEnum;
import br.com.seuzestore.systems.model.KindEnum;
import br.com.seuzestore.systems.model.PaymentEnum;
import br.com.seuzestore.systems.model.Product;
import br.com.seuzestore.systems.model.Sell;
import br.com.seuzestore.systems.model.SizeEnum;
import br.com.seuzestore.systems.services.CartService;
import br.com.seuzestore.systems.services.ProductService;
import br.com.seuzestore.systems.services.SellService;

public class Menu  {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BACKGROUND_RED = "\u001B[41m";
	
	static ProductService product = new ProductService();
	
	//VARIAVEIS PARA PRODUTO
	static Integer code;
	static String description, sku, kind, depart, categ, collor, sizeProd;
	//USANDO NO LOOP DO ENUM
	static String status = "OK";
	//USADO NO LOOP DO CPF
	static String resp;
	
	static KindEnum kindEnum;
	static CategoryEnum categEnum;
	static DepartmentEnum departEnum;
	static CollorEnum collorEnum;
	static SizeEnum sizeEnum;
	
	static int quantity;
	static Double price = 0.00;
	
	
	static SellService sellServ = new SellService();
	static CartService cartServ = new CartService(); 
	
	//VARIAVEIS PARA CARRINHO
	static int codSell, codItem, codProd, quantItem, qtTemp;
	
	//VARIAVEIS PARA VENDA
	static String cpf, pay, key;
	static PaymentEnum payment;
	static String descriItem;
	static LocalDateTime dateSell;
	static LocalDateTime dateCurrent;
	static Double vlTotSell = 0.00, vlItem= 0.00;
	static Double totDay = 0.00, totDin = 0.00, totCre = 0.00, totDeb = 0.00, totPix =  0.00;
	static int qtTotItem= 0;
	
	//**
	
	static Map<Integer, Object> mapSellOpen = new LinkedHashMap<>();
	static Map<Integer, Object> mapSellClose = new LinkedHashMap<>();
	static Map<Integer, Object> mapCartTemp = new LinkedHashMap<>();
	
	static Map<Integer, Object> mapListProd = new LinkedHashMap<>();
	static Map<Integer, Object> mapListProdTemp = new LinkedHashMap<>();
	
	
	
	
	Scanner read = new Scanner(System.in);
	
		
		public void getMenu() {
			int op = 0;
			
			do {
				
				try {

					read.nextLine();
					showMenu();
					
					op = read.nextInt();
				}catch(Exception e){
					System.out.println("Opção inválida!!!");
					read.next();
					continue;
				}
							
				switch (op) {
			
				case 1:{
					System.out.println(" ");
					System.out.println("CADASTRO DE PRODUTO");
					System.out.println(" ");
					code = product.getNewCod();
					System.out.println("Código: " + code);
					
					
					read.nextLine();
					//CHAMA METODO DA CLASSE PRODUTO NO SERVICES PARA VERIFICAR CÓDIGO EXISTENTE
					
						//SE JA EXISTE SOLICITAR UPDATE NO DATE
						//SE NOVO SOLICITAR SAVE NO DATE
					
						
						System.out.println("Sku: ");
						sku = read.nextLine().toUpperCase();
						if(!product.validateSku(sku)) {
							System.out.println("SKU incorreto!!!");
							
							break;
						}
				
					
					
					if(product.checkSku(sku)) {
						System.out.println("Produto já cadastrado!!");
						break;
					}
						
					System.out.println("Descrição: ");
					description = read.nextLine().toUpperCase();
				
					try {
						System.out.println("Quantidade: ");
						quantity = read.nextInt();
								
						System.out.println("Valor: ");
						price = read.nextDouble();
					}catch(Exception e) {
						System.out.println("Informação incorreta!!");
						read.next();
						break;
					}
					
					Product prodTemp = new Product(code, description, sku, quantity, price);
					
					if(product.insert(prodTemp)) {
						System.out.println("Produto cadastrado com SUCESSO!!");
						read.nextLine();
					}else {
						System.out.println("Erro ao cadastrar produto!!");
						read.nextLine();
					}
					break;
					
				
				}
				
				case 2:{
					
					showProd();
					break;
				}
				
				case 3:{
					
					showProd();
					
					try {
						System.out.println("Informe código (0 para SAIR): ");
						code = read.nextInt();
						
					}catch(Exception e) {
						System.out.println("Informação incorreta!!");
						break;
					}
					
					if(code == 0) {
						break;
					}
					
					//VERIFICA SE O PRODUTO EXISTE
					if(!product.checkProd(code)) {
						System.out.println("Produto não cadastrado");
						break;
						
					}else {
						if(cartServ.checkProdSell(code)) {
							System.out.println("Produto contido em uma venda, continuar?[S/N]");
							String answer = read.next().toUpperCase();
							
							if(answer.equals("S")) {
								System.out.println("Descrição: ");
								read.nextLine();
								description = read.nextLine().toUpperCase();
								
								System.out.println("Sku: ");
								//sc.nextLine();
								sku = read.nextLine().toUpperCase();
								if(!product.validateSku(sku)) {
									System.out.println("SKU incorreto!!!");
									break;
								}	
								try {
									System.out.println("Quantidade: ");
									quantity = read.nextInt();
											
									System.out.println("Valor: ");
									price = read.nextDouble();
								}catch(Exception e) {
									System.out.println("Informação incorreta!!");
									read.next();
									break;
								}
								
								Product prodTemp = new Product(code, description, sku, quantity, price);
								
								if(product.refresh(prodTemp)) {
									System.out.println("Produto Atualizado com SUCESSO!!");
									read.nextLine();
								}else {
									System.out.println("Erro ao atualizar produtos!!");
									read.nextLine();
								}
								
							}else
								break;
						}else {
							System.out.println("Descrição: ");
							read.nextLine();
							description = read.nextLine().toUpperCase();
							
							System.out.println("Sku: ");
							//sc.nextLine();
							sku = read.nextLine().toUpperCase();
							
							if(!product.validateSku(sku)) {
								System.out.println("SKU incorreto!!!");
								break;
							}	
							try {
								System.out.println("Quantidade: ");
								quantity = read.nextInt();
										
								System.out.println("Valor: ");
								price = read.nextDouble();
							}catch(Exception e) {
								System.out.println("Informação incorreta!!");
								read.next();
								break;
							}
							
							Product prodTemp = new Product(code, description, sku, quantity, price);
							
							if(product.refresh(prodTemp)) {
								System.out.println("Produto Atualizado com SUCESSO!!");
								read.nextLine();
							}else {
								System.out.println("Erro ao atualizar produtos!!");
								read.nextLine();
							}
					
						}
						
										
						break;
						
					}
				
				}
				
				case 4:{
					showProd();
					resp = "0";
					
					try {
						System.out.println("Informe código (0 para SAIR): ");
						code = read.nextInt();
					}catch(Exception e) {
						System.out.println("Informção incorreta!!");
						break;
					}
					
					if(code == 0) {
						break;
					}
					//VERIFICA SE O PRODUTO EXISTE
					
					if(!product.checkProd(code)) {
						System.out.println("Produto não cadastrado");
						read.next();
						break;
						
					}else {
						//TESTAR SE O PRODUTO FOI VENDIDO
						if(!cartServ.checkProdSell(code)) {
							//SE NÃO, DELETAR
							do {
								try {
									System.out.println("Deseja realmente excluir? (S/N) ");
									resp = read.next().toUpperCase();
										
									if(Character.isDigit(resp.charAt(0))) {
										resp = "0";
										throw new Exception("Apenas letras!!!");
											
									}
											 
								}catch (Exception e) {
									System.out.print("Informação incorreta!!");
									resp = "0";
								}
							}while(resp.equals("0") );
							
							if(resp.equals("S")) {
								if(product.delete(code)) {
									System.out.println("Produto Removido com SUCESSO!!");
									read.nextLine();
								}else {
									System.out.println("Erro ao excluir produto!!");
									read.nextLine();
								}
							}
							
						}else
							System.out.println("Produto contido em uma VENDA!!");
							
						
						break;
						
					}
				
				}
				
				case 5:{
					
					//ABRIR UMA VENDA
					setNewSell();
					
					do {
						
						showProdSell();
											
						//CABECALHO VENDA
						getHeadSell();
												
						
						try {
							System.out.println("Informe código (0 para sair): ");
							codProd = read.nextInt();
						}catch(Exception e) {
							System.out.println("Informação incorreta!!");
							break;
						}
						if(codProd == 0) {
							break;
						}
						if(!product.checkProd(codProd)) {
							System.out.println("Código inexistente!!");
							read.nextLine();
							break;
						}
						if(codProd!=0) {
							try {
								System.out.println("Informe quantidade: ");
								quantItem = read.nextInt();
							}catch (Exception e) {
								System.out.println("Informação incorreta!!!");
								break;
							}
							
							
							//TESTAR QUANTIDADE EM ESTOQUE
							if(!product.checkQuant(codProd, quantItem, qtTemp)) {
								System.out.println("Quantidade insufiente no estoque!!");
								break;
							}
							Product pd = (Product) mapListProd.get(codProd);
							codItem = cartServ.getNewCod();
							Double totItem = (quantItem*pd.getPrice());
							String descProd = pd.getDescription();
							
							Cart cartTemp = new Cart(codItem, codSell, codProd, descProd, quantItem, totItem);
							if(cartServ.insert(cartTemp, pd)) {
								System.out.println("Item adicionado com SUCESSO!!");
								read.nextLine();
							}else {
								System.out.println("Erro ao adicionar item!!");
								read.nextLine();
							}
						
						}
						
							
					}while(codProd != 0);
					break;
				}
				
				
				
				case 6:{
					//LISTAR VENDAS EM ABERTO
					mapSellOpen = sellServ.getListSellOpen();
					if(mapSellOpen.size()>0) {
						System.out.println("********************************************************************");
						System.out.println("************************* VENDAS EM ABERTO *************************");
						for(int k: mapSellOpen.keySet()) {
							Sell tempSell = new Sell();
							calcTotSell(k);
							tempSell = (Sell) mapSellOpen.get(k);
				
							System.out.println("");
							System.out.println("Codigo da Venda: " + k);
							System.out.println("Data da Venda  : " + tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear());
							System.out.println("Total da Venda : " +  String.format("%.2f", vlTotSell));
							System.out.println("");					
						
							
						}
						
						try {
							System.out.println("Informe o código da venda(0 para sair): ");
							codSell = read.nextInt();
						}catch(Exception e) {
							System.out.println("Informação incorreta!!!");
							break;
						}
						
						if(codSell == 0) {
							break;
						}
						if(!mapSellOpen.containsKey(codSell)) {
							System.out.println("Código inexistente!!");
							read.nextLine();
							break;
						}
						do {
							
							showProdSell();
							
							//CABECALHO DE VENDA
							getHeadSell();
							
							try {
								System.out.println("Informe código produto(0 para sair): ");
								codProd = read.nextInt();
							}catch(Exception e) {
								System.out.println("Informação incorreta!!! ");
								break;
							}
							
							if(codProd == 0) {
								break;
							}
							if(!product.checkProd(codProd)) {
								System.out.println("Código inexistente!!");
								read.nextLine();
								break;
							}
							
							if(codProd!=0) {
								try {
									System.out.println("Informe quantidade: ");
									quantItem = read.nextInt();
								}catch(Exception e) {
									System.out.println("Informação incorreta!!!");
									break;
								}
								
								Product pd = (Product) mapListProd.get(codProd);
								//VERIFICAR SE O PRODUTO JA EXISTE NA VENDA
								codItem = cartServ.locate(codSell, codProd);
								if(codItem != 0) {
									//TESTAR QUANTIDADE EM ESTOQUE
									Cart cartTemp = (Cart) mapCartTemp.get(codItem);
									qtTemp = cartTemp.getQuantity();
									if(!product.checkQuant(codProd, quantItem, qtTemp)) {
										System.out.println("Quantidade insufiente no estoque!!");
										break;
									}
									//SOMA COM A QUANTIDADE JA EXISTENTE
									cartTemp.setQuantity(cartTemp.getQuantity()+quantItem);
									Cart cartTemp2 = new Cart(codItem, codSell, codProd, null, cartTemp.getQuantity(), null);
									if(cartServ.insert(cartTemp2, pd)) {
										System.out.println("Item adicionado com SUCESSO!!");
										read.nextLine();
										
										
									}else {
										System.out.println("Erro ao adicionar item!!");
										read.nextLine();
										break;
									}
										
									
								}else {
									codItem = cartServ.getNewCod();
									Double totItem = (quantItem*pd.getPrice());
									String descProd = pd.getDescription();
									Cart cartTemp2 = new Cart(codItem, codSell, codProd, descProd, quantItem, totItem);
									if(cartServ.insert(cartTemp2, pd)) {
										System.out.println("Item adicionado com SUCESSO!!");
										read.nextLine();
										
									}else {
										System.out.println("Erro ao adicionar item!!");
										read.nextLine();
										break;
									}
									
								}
													
							}
							
								
						}while(codProd != 0);
					}else {
						setNewSell();
						
						do {
							
							showProdSell();
							
							getHeadSell();
														
							try {
								System.out.println("Informe código produto(0 para sair): ");
								codProd = read.nextInt();
							}catch(Exception e) {
								System.out.println("Informação incorreta!!! ");
								break;
							}
							if(codProd == 0) {
								break;
							}
							if(!product.checkProd(codProd)) {
								System.out.println("Código inexistente!!");
								read.nextLine();
								break;
							}
							
							if(codProd!=0) {
								try {
									System.out.println("Informe quantidade: ");
									quantItem = read.nextInt();
								}catch (Exception e) {
									System.out.println("Informação incorreta!!");
									break;
								}
								
								Product pd = (Product) mapListProd.get(codProd);
								//VERIFICAR SE O PRODUTO JA EXISTE NA VENDA
								codItem = cartServ.locate(codSell, codProd);
								if(codItem != 0) {
									//TESTAR QUANTIDADE EM ESTOQUE
									Cart cartTemp = (Cart) mapCartTemp.get(codItem);
									qtTemp = cartTemp.getQuantity();
									if(!product.checkQuant(codProd, quantItem, qtTemp)) {
										System.out.println("Quantidade insufiente no estoque!!");
										break;
									}
									//SOMA COM A QUANTIDADE JA EXISTENTE
									cartTemp.setQuantity(cartTemp.getQuantity()+quantItem);
									
									Cart cartTemp2 = new Cart(codItem, codSell, codProd, null, cartTemp.getQuantity(), null);
									if(cartServ.insert(cartTemp2, pd)) {
										System.out.println("Item adicionado com SUCESSO!!");
										read.nextLine();
										
									}else {
										System.out.println("Erro ao nao adicionar item!!");
										read.nextLine();
										
									}
								}else {
									codItem = cartServ.getNewCod();
									Double totItem = (quantItem*pd.getPrice());
									String descProd = pd.getDescription();
									Cart cartTemp2 = new Cart(codItem, codSell, codProd, descProd, quantItem, totItem);
									if(cartServ.insert(cartTemp2, pd)) {
										System.out.println("Item adicionado com SUCESSO!!");
										read.nextLine();
										
									}else {
										System.out.println("Erro ao adicionar item!!");
										read.nextLine();
										break;
									}
									
								}
								
							}
							
								
						}while(codProd != 0);
					}
							
					break;
							
				}
				
				case 7:{
					//LISTAR VENDAS EM ABERTO
					mapSellOpen = sellServ.getListSellOpen();
					if(mapSellOpen.size()>0) {
						System.out.println(" ");
						System.out.println("********************************************************************");
						System.out.println("************************* VENDAS EM ABERTO *************************");
						
						for(int k: mapSellOpen.keySet()) {
							Sell tempSell = new Sell();
							calcTotSell(k);
							tempSell = (Sell) mapSellOpen.get(k);
				
							System.out.println("");
							System.out.println("Codigo da Venda: " + k);
							System.out.println("Data da Venda  : " + tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear());
							System.out.println("Total da Venda : " +  String.format("%.2f", vlTotSell));
							System.out.println("");
							
						}
					//FIM LISTAR VENDAS EM ABERTO
						System.out.println("********************************************************************");
						System.out.println("Informe codigo da venda(0 para sair): ");
						codSell = read.nextInt();
						if(codSell == 0) {
							break;
						}
						//LISTAR ITENS DA VENDA
						getItemSell(codSell);
						//FIM LISTA DE ITENS DA VENDA
						read.nextLine();
				
					}else
						System.out.println("Não existe vendas em aberto!!");
						System.out.println("");
					break;
					
				}
				
				case 8:{
					//LISTAR VENDAS EM ABERTO
					mapSellOpen = sellServ.getListSellOpen();
					if(mapSellOpen.size()>0) {
						System.out.println("********************************************************************");
						System.out.println("************************* VENDAS EM ABERTO *************************");
						
						for(int k: mapSellOpen.keySet()) {
							Sell tempSell = new Sell();
							calcTotSell(k);
							tempSell = (Sell) mapSellOpen.get(k);
	
							System.out.println("");
							System.out.println("Codigo da Venda: " + k);
							System.out.println("Data da Venda  : " + tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear());
							System.out.println("Total Venda    : " + String.format("%.2f", vlTotSell));
							System.out.println("");
							
						}
					//FIM LISTAR VENDAS EM ABERTO
						System.out.println("********************************************************************");
						try {
							System.out.println("Informe código da venda(0 para sair): ");
							codSell = read.nextInt();
						}catch (Exception e) {
							System.out.println("Informação incorreta!!!");
							break;
						}
						
						if(codSell == 0) {
							break;
						}
						if(!mapSellOpen.containsKey(codSell)) {
							System.out.println("Código inexistente!!");
							read.nextLine();
							break;
						}
						//LISTAR ITENS DA VENDA
						getItemSell(codSell);
						//FIM LISTA DE ITENS DA VENDA
						read.nextLine();
						
						
						do {
							try {
								System.out.println("Informe código ITEM (0 para sair): ");
								codItem = read.nextInt();
							}catch (Exception e) {
								System.out.println("Informação incorreta!!1");
								break;
							}
							
							if(codItem == 0) {
								break;
							}
							
							if(codItem!=0) {
								if(mapCartTemp.containsKey(codItem)) {
								   Cart cartTemp = (Cart) mapCartTemp.get(codItem);
								   
								   codProd = cartTemp.getCodProd();
								   Product pd = (Product) mapListProd.get(codProd);
								   
								   try {
									   System.out.println("Informe quantidade (0 exclui item): ");
									   quantItem = read.nextInt();
								   }catch (Exception e) {
									   System.out.println("Informação incorreta!!!");
									   break;
								   }
								   
									
									if(quantItem == 0) {
										//CHAMA FUNCAO EXCLUIR ITEM
										if(cartServ.delete(codItem)) {
											System.out.println("Item excluido com SUCESSO!!");
											read.nextLine();
											
										}else {
											System.out.println("Erro ao excluir ITEM!!");
											read.nextLine();
											break;
										}
										
									}else {
										//TESTAR QUANTIDADE EM ESTOQUE
										qtTemp = cartTemp.getQuantity();				
										if(!product.checkQuant(codProd, quantItem, qtTemp)) {
											System.out.println("Quantidade insufiente no estoque!!");
											read.nextLine();
										}else {
											
											if(cartServ.refresh(codItem, codSell, codProd, quantItem, pd)) {
												System.out.println("Item atualizado com SUCESSO!!");
												read.nextLine();
												
											}else {
												System.out.println("Erro ao atulaizar ITEM!!");
												read.nextLine();
												break;
											}
										}
										
									}
								}else {
									System.out.println("Código incorreto!!!");
									break;
								}
								
							}
							
							//LISTAR ITENS DA VENDA
							getItemSell(codSell);
													
						}while(codItem != 0);
					}
							
					break;
							
				}
				
				case 9:{
					//LISTAR VENDAS EM ABERTO
					mapSellOpen = sellServ.getListSellOpen();
					if(mapSellOpen.size()>0) {
						System.out.println("********************************************************************");
						System.out.println("************************* VENDAS EM ABERTO *************************");
						
						for(int k: mapSellOpen.keySet()) {
							Sell tempSell = new Sell();
							calcTotSell(k);
							tempSell = (Sell) mapSellOpen.get(k);
							
							System.out.println("");
							System.out.println("Codigo da Venda: " + k);
							System.out.println("Data da Venda  : " + tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear());
							System.out.println("Total da Venda : " +  String.format("%.2f", vlTotSell));
							System.out.println("");
							
						}
					//FIM LISTAR VENDAS EM ABERTO
						System.out.println("********************************************************************");
						try {
							System.out.println("Informe codigo da venda(0 para sair): ");
							codSell = read.nextInt();
						}catch (Exception e) {
							System.out.println("Informação incorreta!!");
							break;
						}
						
						if(codSell == 0) {
							break;
						}
						if(!mapSellOpen.containsKey(codSell)) {
							System.out.println("Código inexistente!!");
							read.nextLine();
							break;
						}
						
						//LISTAR ITENS DA VENDA
						getItemSell(codSell);
						//FIM LISTA DE ITENS DA VENDA
						read.nextLine();
						
						
						do {
							
							try {
								System.out.println("Informe código item (0 para sair): ");
								codItem = read.nextInt();
							}catch (Exception e) {
								System.out.println("Informação incorreta!!");
								break;
							}
							
							if(codItem == 0) {
								break;
							}
							
							if(codItem!=0) {
								if(mapCartTemp.containsKey(codItem)) {
								   
								   if(cartServ.delete(codItem)) {
									   System.out.println("Item removido com SUCESSO!!!");
									   read.nextLine();
									   
								   }else {
									   System.out.println("Erro ao remover ITEM!!!");
									   read.nextLine();
									   break;
								   }
								  
								}else {
									System.out.println("Código incorreto!!");
									read.nextLine();
									break;
								}
								
							}
							
							//LISTAR ITENS DA VENDA
							getItemSell(codSell);
								
						}while(codItem != 0);
					}
							
					break;
							
				}
				
				case 10:{
					do {
						//LISTAR VENDAS EM ABERTO
						mapSellOpen = sellServ.getListSellOpen();
						if(mapSellOpen.size()>0) {
							System.out.println("********************************************************************");
							System.out.println("************************* VENDAS EM ABERTO *************************");
							
							for(int k: mapSellOpen.keySet()) {
								Sell tempSell = new Sell();
								
								calcTotSell(k);
								
								tempSell = (Sell) mapSellOpen.get(k);
								
								System.out.println("");
								System.out.println("Codigo da Venda: " + k);
								System.out.println("Data da Venda  : " + tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear());
								System.out.println("Total itens    : " + qtTotItem);
								System.out.println("Valor total    : " + String.format("%.2f", vlTotSell));
								
								System.out.println("");
								
							}
						//FIM LISTAR VENDAS EM ABERTO
							System.out.println("********************************************************************");
							try {
								System.out.println("Informe codigo da venda (0 para sair): ");
								codSell = read.nextInt();
							}catch (Exception e) {
								System.out.println("Informação incorreta!!");
								break;
							}
							
							if(codSell == 0) {
								break;
							}
							if(!mapSellOpen.containsKey(codSell)) {
								System.out.println("Código inexistente!!");
								read.nextLine();
								break;
							}
							//LISTAR ITENS DA VENDA
							getItemSell(codSell);
							read.nextLine();
							
												
							do {
								try {
									System.out.println("Deseja realmente excluir? (S/N) ");
									resp = read.next().toUpperCase();
										
									if(Character.isDigit(resp.charAt(0))) {
										resp = "0";
										throw new Exception("Apenas letras!!!");
											
									}
											 
								}catch (Exception e) {
									System.out.print("Informação incorreta!!");
									resp = "0";
								}
							}while(resp.equals("0") );
								
								if(resp.equals("S")){
									for(int k: mapCartTemp.keySet()) {
										Cart cartTemp = (Cart) mapCartTemp.get(k);
										if(cartTemp.getCodSell() == codSell) {
											if(!cartServ.delete(cartTemp.getCodItem())) {
												System.out.println("Erro ao remover item da venda!!!");
												read.nextLine();
												break;
												
											}
										}
									}
									if(sellServ.delete(codSell)) {
										System.out.println("Venda removida com SUCESSO!!!");
										read.nextLine();
										break;
									}else {
										System.out.println("Erro ao remover venda!!!");
										read.nextLine();
										break;
									}
									
								}
						}else {
							System.out.println("Não há vendas em aberto!!");
							read.nextLine();
							break;
						}
							
							
					}while(codSell !=0);
							
					break;
							
				}	
				
				case 11:{
					//LISTAR VENDAS EM ABERTO
					do {
						mapSellOpen = sellServ.getListSellOpen();
						if(mapSellOpen.size()>0) {
							
							Sell tempSell = new Sell();
							System.out.println("********************************************************************");
							System.out.println("************************* VENDAS EM ABERTO *************************");
							
							for(int k: mapSellOpen.keySet()) {
								
								
								calcTotSell(k);
								
								tempSell = (Sell) mapSellOpen.get(k);
								if(!(qtTotItem == 0 && vlTotSell == 0.00)) {
									System.out.println("");
									System.out.println("Codigo da Venda: " + k);
									System.out.println("Data da Venda  : " + tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear() );
									System.out.println("Total itens    : " + qtTotItem);
									System.out.println("Valor total    : " + String.format("%.2f", vlTotSell));
									
									System.out.println("");
								}
															
							}
						//FIM LISTAR VENDAS EM ABERTO
							System.out.println("********************************************************************");
							try {
								System.out.println("Informe codigo da venda (0 para sair): ");
								codSell = read.nextInt();
							}catch (Exception e) {
								System.out.println("Informação incorreta!!!");
								break;
							}
							
							
							if(codSell ==0)
								break;
							
							if(!mapSellOpen.containsKey(codSell)) {
								System.out.println("Código inexistente!!");
								read.nextLine();
								break;
							}
														
							read.nextLine();
							if(mapSellOpen.containsKey(codSell)) {
								tempSell = (Sell) mapSellOpen.get(codSell);
								calcTotSell(codSell); //preencher valor total
								tempSell.setVlTotal(vlTotSell);
								do {
									
									System.out.println("Forma de pagmento: ");
									
									for(PaymentEnum k : PaymentEnum.values()) {
										
										System.out.print(  " | " + k.getReference()  );
									}
									System.out.println("\n Informe uma forma: ");
								
									pay = read.nextLine().toUpperCase();
									
									if(!PaymentEnum.checkPayment(pay)) {
										System.out.println("Opção incorreta!!");
										status = "NOK";
									}
									else {
										payment = PaymentEnum.getPayment(pay);
										status = "OK";
									}
								}while(!status.equals("OK"));
								
								if((payment.getReference().equals("CRE")) || (payment.getReference().equals("DEB")) ) {
									try {
										System.out.print("Informe número do Cartão: ");
										key = read.nextLine();
									}catch (Exception e) {
										System.out.print("Informação incorreta!!!");
										break;
									}
									
									
								}else if(payment.getReference().equals("PIX")) {
									System.out.print("Informe a chave: ");
									key = read.nextLine();
									
								}
								
								do {
									try {
										System.out.println("Deseja cad. CPF? (S/N)");
										resp = read.next().toUpperCase();
										
										
										
										if(Character.isDigit(resp.charAt(0))) {
											
											resp = "0";
											throw new Exception("Apenas letras!!!");
											
										}else { 
											if(!((resp.equals("S")) || (resp.equals("N")))) {
												resp = "0";
												System.out.print("Informação incorreta!!");
											}
										}
											 
									}catch (Exception e) {
										System.out.print("Informação incorreta!!");
										resp = "0";
									}
								}while(resp.equals("0") );
								
								
								
								if(resp.equals("S")) {
									Boolean repeat = false;
									do {
										
										try {
											System.out.print("CPF: ");
											cpf = read.next();	
										}catch (Exception e) {
											System.out.print("Informação incorreta!!");
											continue;
										}
										
										//TESTE CPF
										if(CPF.isCPF(cpf)) {
											if(CPF.checkCpf(cpf)) {
												repeat = true;
											}
										}
										
									}while(repeat == false);
										
								}else
									cpf = "";
								
								tempSell.setCpf(cpf);
								tempSell.setKeyPayment(key);
								//DEBITAR PRODUTO ESTOQUE
								cartServ.debitProd(codSell);
								Sell sellTemp2 = new Sell(tempSell.getCode(), tempSell.getCpf(), tempSell.getkeyPayment(), payment, tempSell.getDate(), tempSell.getVlTotal());
								if(sellServ.refresh(sellTemp2)) {
									System.out.println("Venda finalizada com SUCESSO!!!");
									read.nextLine();
									
								}else {
									System.out.println("Erro ao finalizar a venda!!!");
									read.nextLine();
									break;
								}
							}else {
								System.out.println("");
								System.out.println("Código venda incorreto!!");
								read.nextLine();
							}
						}else {
							System.out.println("");
							System.out.println("Não há vendas em aberto!!");
							read.nextLine();
							break;
						}
						
					}while(codSell !=0);
								
					break;
							
				}
				
				case 12:{
					//LISTAR VENDAS EM ABERTO
					totCre = 0.00;
					totDay = 0.00;
					totDeb = 0.00;
					totDin = 0.00;
					totPix = 0.00;
					
						mapSellClose = sellServ.getList();
						if(mapSellClose.size()>0) {
							Sell tempSell = new Sell();
							System.out.println("HISTORICO DE VENDAS");
							
							
							for(int k: mapSellClose.keySet()) {
								tempSell = (Sell) mapSellClose.get(k);
								if(tempSell.getVlTotal() != 0) {
									calcTotSell(k);
									String date = tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear();
									
									System.out.println(" ");
									System.out.println("===============================================================================");
									System.out.println("=========================       VENDA      ====================================");
									System.out.println(" Data da venda  : " + date );
									System.out.println(" Pagamento      : " + tempSell.getPayment().getReference());
									if(tempSell.getPayment().getReference().equals("CRE") || tempSell.getPayment().getReference().equals("DEB"))
										System.out.println(" Cartão         : " + tempSell.getkeyPayment());
									if(tempSell.getPayment().getReference().equals("PIX") )
										System.out.println(" Chave          : " + tempSell.getkeyPayment());
									if(!tempSell.getCpf().isBlank() )
										System.out.println(" CPF            : " + CPF.formatCpf(tempSell.getCpf()));
									
									System.out.println("===============================================================================");
									System.out.println(" ");
									
									getItemSell(k);
									
									dateCurrent = LocalDateTime.now();
									if(tempSell.getDate().getDayOfMonth() == dateCurrent.getDayOfMonth())
										totDay += tempSell.getVlTotal();
									if(tempSell.getPayment().getReference().equals("DIN"))
										totDin += tempSell.getVlTotal();
									if(tempSell.getPayment().getReference().equals("DEB"))
										totDeb += tempSell.getVlTotal();
									if(tempSell.getPayment().getReference().equals("CRE"))
										totCre += tempSell.getVlTotal();	
									if(tempSell.getPayment().getReference().equals("PIX"))
										totPix += tempSell.getVlTotal();
										
										
								}
								
							}
							System.out.println(" ");
							
							System.out.println("=============================== RESUMO ======================================");
							System.out.println("Total do dia: " + String.format("%.2f", totDay));
							System.out.println("________________________________________________");
							System.out.println("         Pix: " + String.format("%.2f", totPix));
							System.out.println("    Dinheiro: " + String.format("%.2f", totDin));
							System.out.println("     Crédito: " + String.format("%.2f", totCre));
							System.out.println("      Débito: " + String.format("%.2f", totDeb));
							System.out.println("=============================================================================");
							read.nextLine();
						
						}else {
							System.out.println("Não há vendas!!");
							read.nextLine();
							break;
						}
					break;
							
				}
				
				case 13:{
					
					totCre = 0.00;
					totDay = 0.00;
					totDeb = 0.00;
					totDin = 0.00;
					totPix = 0.00;
					LocalDateTime dtSearch = null;
					String paySearch, dt;
					DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu");
					
						mapSellClose = sellServ.getList();
						if(mapSellClose.size()>0) {
							Sell tempSell = new Sell();
							//System.out.println("HISTORICO DE VENDAS");
							
							do {
						
								System.out.println("\n Forma de pagamento: ");
								for(PaymentEnum k : PaymentEnum.values()) {
									
									System.out.print(  " | " + k.getReference()  );
								}
								System.out.println("\n Informe um pagamento: ");
							
								paySearch = read.next().toUpperCase();
								
								if(!PaymentEnum.checkPayment(paySearch)) {
									System.out.println("Opção incorreta!!");
									status = "NOK";
								}else {
									System.out.println("\n Informe uma data (dd/mm/aaaa): ");
									dt = read.next();
									dtSearch = LocalDate.parse(dt, format).atStartOfDay();
									status = "OK";
								}
							}while(status.equals("NOK"));
							
							for(int k: mapSellClose.keySet()) {
								tempSell = (Sell) mapSellClose.get(k);
								if(tempSell.getVlTotal() != 0) {
									calcTotSell(k);
									if(tempSell.getDate().getDayOfMonth() == dtSearch.getDayOfMonth() &&
											tempSell.getDate().getMonthValue() == dtSearch.getMonthValue() &&
											tempSell.getDate().getYear() == dtSearch.getYear() && tempSell.getPayment().getReference().equals(paySearch)){
										String date = tempSell.getDate().getDayOfMonth() + "/" + tempSell.getDate().getMonthValue() + "/" + tempSell.getDate().getYear();
										
										System.out.println(" ");
										System.out.println("===============================================================================");
										System.out.println("=========================       VENDA      ====================================");
										System.out.println(" Data da venda  : " + date );
										System.out.println(" Pagamento      : " + tempSell.getPayment().getReference());
										if(tempSell.getPayment().getReference().equals("CRE") || tempSell.getPayment().getReference().equals("DEB"))
											System.out.println(" Cartão         : " + tempSell.getkeyPayment());
										if(tempSell.getPayment().getReference().equals("PIX") )
											System.out.println(" Chave          : " + tempSell.getkeyPayment());
										if(!tempSell.getCpf().isBlank() )
											System.out.println(" CPF            : " + CPF.formatCpf(tempSell.getCpf()));
										
										System.out.println("===============================================================================");
										System.out.println(" ");
										
										getItemSell(k);
										
										//dateCurrent = LocalDateTime.now();
										if(tempSell.getDate().getDayOfMonth() == dtSearch.getDayOfMonth())
											totDay += tempSell.getVlTotal();
										if(tempSell.getPayment().getReference().equals("DIN"))
											totDin += tempSell.getVlTotal();
										if(tempSell.getPayment().getReference().equals("DEB"))
											totDeb += tempSell.getVlTotal();
										if(tempSell.getPayment().getReference().equals("CRE"))
											totCre += tempSell.getVlTotal();	
										if(tempSell.getPayment().getReference().equals("PIX"))
											totPix += tempSell.getVlTotal();
																		
									}
									
								}
							
							}
							if(totDay != 0) {
								System.out.println(" ");
								
								System.out.println("=============================== RESUMO ======================================");
								System.out.println("Total do dia: " + String.format("%.2f", totDay));
								System.out.println("________________________________________________");
								System.out.println("         Pix: " + String.format("%.2f", totPix));
								System.out.println("    Dinheiro: " + String.format("%.2f", totDin));
								System.out.println("     Crédito: " + String.format("%.2f", totCre));
								System.out.println("      Débito: " + String.format("%.2f", totDeb));
								System.out.println("=============================================================================");
								read.nextLine();
							}else {
								System.out.println("Não há vendas nesse filtro!!");
								read.nextLine();
							}
							
						
						}else {
							System.out.println("Não há vendas FINALIZADAS!!");
							read.nextLine();
							break;
						}
					break;
							
				}
				
				case 14:{
					System.out.println("Obrigado!!");
					break;
				}
				
				
				default:{
					System.out.println("Opção inválida!!");
				}
			}
			
			
			
		}while(op != 14);
			
		}

			private static void showProd() {
		if(mapListProd.isEmpty())
			mapListProd = product.getList();
		
		System.out.println("*********************************************************************************");
		System.out.println("********************************LISTA DE PRODUTOS********************************");
		for(int k: mapListProd.keySet()) {
			Product temp = (Product) mapListProd.get(k);
		
	
			System.out.println("");
			System.out.println("Codigo:       " + k);
			System.out.println("Descrição:    " + temp.getDescription());
			System.out.println("Tipo:         " + temp.getKind().getDesc());
			System.out.println("Categoria:    " + temp.getCategory().getDesc());
			System.out.println("Departamento: " + temp.getDepartment().getDesc());
			System.out.println("Tamanho:      " + temp.getSize().getDesc());
			System.out.println("Cor:          " + temp.getCollor().getDesc());
			System.out.println("Quantidade:   " + temp.getQuantity());
			System.out.println("Valor:        " + String.format("%.2f", temp.getPrice()));
			System.out.println("");
			
		}
		System.out.println("");
		System.out.println("****************************FIM DA LISTA*****************************************");
		System.out.println("");
	}
	
	private static void getItemSell(int codeSell) {
		
		mapCartTemp = cartServ.getList();
		int qt = 0;
		Double tot = 0.00;
		
		System.out.println("                                 ITENS DA VENDA ");
		for(int i: mapCartTemp.keySet()) {
			Cart tempcart = (Cart) mapCartTemp.get(i);
			if(tempcart.getCodSell() == codeSell) {
				qt+= tempcart.getQuantity();
				tot+= tempcart.getVlItem();
				System.out.println("");
				System.out.println("Codigo do item: " + tempcart.getCodItem());
				System.out.println("Descrição:      " + tempcart.getDescription());
				System.out.println("Quantidade:     " + tempcart.getQuantity());
				System.out.println("Total item R$:  " + String.format("%.2f", tempcart.getVlItem()));
				System.out.println("");
			}
				
		}
		System.out.println("********************************************************************************");
		System.out.println("=============================================================================");
		System.out.println("Código venda: " + codeSell + " Quant. itens: " + qt + " Total venda: "+ String.format("%.2f", tot));
		System.out.println("=============================================================================");
			
	
	}

	private static void calcTotSell(int k) {
		
		mapCartTemp = cartServ.getList();
		qtTotItem = 0;
		vlTotSell = 0.00;
		
		for(int i: mapCartTemp.keySet()) {
			Cart tempcart = (Cart) mapCartTemp.get(i);
			if(tempcart.getCodSell() == k) {
				qtTotItem+= tempcart.getQuantity();
				vlTotSell+= tempcart.getVlItem();
			}
		}
		
	}

	private static void getHeadSell() {
		
		mapCartTemp = cartServ.getList();
		int qt = 0;
		Double tot = 0.00;
		for(int i: mapCartTemp.keySet()) {
			Cart tempcart = (Cart) mapCartTemp.get(i);
			if(tempcart.getCodSell() == codSell) {
				qt+= tempcart.getQuantity();
				tot+= tempcart.getVlItem();
			}
		}
			
		System.out.println("=============================================================================");
		System.out.println("Código venda: " + codSell + " Quant. itens: " + qt + " Total venda: "+ String.format("%.2f", tot));
		System.out.println("=============================================================================");
		

	}

	private static void showProdSell() {
		mapListProd = product.getList();
		if(mapCartTemp.isEmpty()) {
				
			System.out.println("LISTA DE PRODUTOS");
			for(int k: mapListProd.keySet()) {
				Product temp = (Product) mapListProd.get(k);
			
				System.out.println("");
				System.out.println("Codigo:       " + k);
				System.out.println("Descrição:    " + temp.getDescription());
				System.out.println("Tipo:         " + temp.getKind());
				System.out.println("Categoria:    " + temp.getCategory());
				System.out.println("Departamento: " + temp.getDepartment());
				System.out.println("Tamanho:      " + temp.getSize());
				System.out.println("Quantidade:   " + temp.getQuantity());
				System.out.println("Valor:        " + String.format("%.2f",temp.getPrice()));
				System.out.println("");
				
			}
			System.out.println("FIM LISTA");
			System.out.println("");// TODO Auto-generated method stub
					
					
		}else {
				
			mapCartTemp = cartServ.getList();
			Cart cartTemp = new Cart();
			Product temp = new Product();
			System.out.println("LISTA DE PRODUTOS");
			for(int k: mapListProd.keySet()) {
				temp = (Product) mapListProd.get(k);
				
				System.out.println("");
				System.out.println("Codigo:       " + k);
				System.out.println("Descrição:    " + temp.getDescription());
				System.out.println("Tipo:         " + temp.getKind());
				System.out.println("Categoria:    " + temp.getCategory());
				System.out.println("Departamento: " + temp.getDepartment());
				System.out.println("Tamanho:      " + temp.getSize());
				for(int y: mapCartTemp.keySet()) {
					cartTemp =  (Cart) mapCartTemp.get(y);
					if(temp.getCode().equals(cartTemp.getCodProd()) && cartTemp.getCodSell().equals(codSell)) {
						qtTemp = temp.getQuantity()-cartTemp.getQuantity();
						break;
					}else
						qtTemp = temp.getQuantity();
						
				}
				System.out.println("Quantidade:   " + qtTemp);
				System.out.println("Valor:        " + String.format("%.2f",temp.getPrice()) );
				System.out.println("");
					
			}
			System.out.println("FIM LISTA");
			System.out.println("");
				
			//ZERAR QUANTIDAE TEPORARIA DO PRODUTO
			qtTemp = 0;
		}
			
	}

		
	

	private static  void setNewSell() {
		codSell = sellServ.getNewCod();
		dateCurrent = LocalDateTime.now();
		vlTotSell = 0.00;
					
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss");
		String dt = dateCurrent.format(formato);
			
		dateSell =  LocalDateTime.parse(dt, formato);
		Sell selltemp = new Sell(codSell, cpf, key, payment, dateSell, vlTotSell);
		//sellServ.insert(codVenda, cpf, key, payment, dataVenda, vlTotVenda);
		if(!sellServ.insert(selltemp)) {
			System.out.println("Erro ao iniciar venda!!");
			
		}
			
	}

		public void showMenu() {
			System.out.println("*********** LOJA SEU ZÉ *************");
			System.out.println("*********     PRODUTO   *************");
			System.out.println("***** 1 - Adicionar           *******");
			System.out.println("***** 2 - Listar              *******");
			System.out.println("***** 3 - Atualizar           *******");
			System.out.println("***** 4 - Remover             *******");
			System.out.println("*************************************");
			System.out.println("*********     VENDAS    *************");
			System.out.println("***** 5  - Nova venda         *******");
			System.out.println("***** 6  - Adicionar item     *******");
			System.out.println("***** 7  - Itens venda aberta *******");
			System.out.println("***** 8  - Alterar item       *******");
			System.out.println("***** 9  - Remover item       *******");
			System.out.println("***** 10 - Excluir venda      *******");
			System.out.println("***** 11 - Finalizar venda    *******");
			System.out.println("*************************************");
			System.out.println("*********     Relatório Vendas ******");
			System.out.println("***** 12 - Dia                  *****");
			System.out.println("***** 13 - Por data e pagamento *****");
			System.out.println("*************************************");	
			System.out.println("***** 14 - Sair               *******");
			System.out.println("Escolha uma opção: ");
			
			
		}
		
		
		
		
		
}




