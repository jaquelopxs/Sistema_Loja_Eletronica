package Loja;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    private final List<Produto> listaProduto = new ArrayList<>();
    private int indiceAtual = 0;
    private final List<Produto> carrinho = new ArrayList<>();

    private final JLabel[] labelsImg;
    private final JLabel[] labelsNome;
    private final JLabel[] labelsPreco;
    private final JLabel[] labelsAno;
    private final JLabel[] labelsDescricao;
    private final JSpinner[] spinnersQtd;
    private final JButton[] bntAdicionar;
    private final JLabel[] labelsQuantidade;


    public Principal() {
        initComponents();
        carregarProdutos();
        labelsImg = new JLabel[]{labelImg1, labelImg2, labelImg3, labelImg4, labelImg5, labelImg6};
        labelsNome = new JLabel[]{labelNome1, labelNome2, labelNome3, labelNome4, labelNome5, labelNome6};
        labelsPreco = new JLabel[]{labelPreco1, labelPreco2, labelPreco3, labelPreco4, labelPreco5, labelPreco6};
        labelsAno = new JLabel[]{labelAno1, labelAno2, labelAno3, labelAno4, labelAno5, labelAno6};
        spinnersQtd = new JSpinner[]{jSpinner1, jSpinner2, jSpinner3, jSpinner4, jSpinner5, jSpinner6};
        labelsDescricao = new JLabel[]{labelDescricao1, labelDescricao2, labelDescricao3, labelDescricao4, labelDescricao5, labelDescricao6};
        bntAdicionar = new JButton[]{bntAdicionar1, bntAdicionar2, bntAdicionar3, bntAdicionar4, bntAdicionar5, bntAdicionar6};
        labelsQuantidade = new JLabel[]{labelQuantidade1, labelQuantidade2, labelQuantidade3, labelQuantidade4, labelQuantidade5, labelQuantidade6};


        exibirProdutos(indiceAtual);

        bntAdicionar1.addActionListener(e -> adicionarProduto(0));
        bntAdicionar2.addActionListener(e -> adicionarProduto(1));
        bntAdicionar3.addActionListener(e -> adicionarProduto(2));
        bntAdicionar4.addActionListener(e -> adicionarProduto(3));
        bntAdicionar5.addActionListener(e -> adicionarProduto(4));
        bntAdicionar6.addActionListener(e -> adicionarProduto(5));
        bntLimpar.addActionListener(e -> limparBusca());
        
        bntComprar.addActionListener(e -> bntComprarActionPerformed(null));
    }

private void carregarProdutos() {
        listaProduto.add(new Produto(1, "Notebook Dell", "Inspiron 15", 4500.00, 2, "imagens/dell.png", 2022));
        listaProduto.add(new Produto(2, "TV LG", "Smart TV 50'' 4K", 3200.00, 1, "imagens/tv.png", 2021));
        listaProduto.add(new Produto(3, "iPhone 13", "Apple 128GB", 5000.00, 4, "imagens/iphone.png", 2023));
        listaProduto.add(new Produto(4, "PlayStation 5", "Sony PS5", 5500.00, 3, "imagens/ps5.png", 2022));
        listaProduto.add(new Produto(5, "Kindle", "Paperwhite", 600.00, 5, "imagens/kindle.png", 2021));
        listaProduto.add(new Produto(6, "Fone JBL", "Bluetooth", 300.00, 10, "imagens/fone.png", 2023));
        listaProduto.add(new Produto(7, "Monitor Samsung", "Curvo 27''", 1500.00, 3, "imagens/monitor.png", 2022));
        listaProduto.add(new Produto(8, "Teclado Mecânico", "RGB Switch Blue", 350.00, 7, "imagens/teclado.png", 2024));
        listaProduto.add(new Produto(9, "Mouse Logitech", "G Pro Wireless", 450.00, 6, "imagens/mouse.png", 2023));
        listaProduto.add(new Produto(10, "Impressora HP", "LaserJet", 800.00, 2, "imagens/impressora.png", 2020));
        listaProduto.add(new Produto(11, "Cadeira Gamer", "Ergonomic", 1200.00, 4, "imagens/cadeira.png", 2023));
        listaProduto.add(new Produto(12, "Roteador TP-Link", "Wi-Fi 6", 500.00, 5, "imagens/roteador.png", 2024));
    }

private void adicionarProduto(int posicaoPainel) {
        int idxProduto = indiceAtual + posicaoPainel;
        if (idxProduto < listaProduto.size()) {
            Produto p = listaProduto.get(idxProduto);
            int quantidade = (int) spinnersQtd[posicaoPainel].getValue();

            if (quantidade > 0) {
                Produto itemPedido = new Produto(
                    p.getCodigo(), p.getNome(), p.getDescricao(),
                    p.getValor(), quantidade, p.getCaminhoImagem(), p.getAno()
                );
                carrinho.add(itemPedido);
                atualizarNotaFiscal();
            } else {
                JOptionPane.showMessageDialog(this, "Informe uma quantidade válida!");
            }
        }
    }

private void atualizarNotaFiscal() {
        StringBuilder nota = new StringBuilder();
        double total = 0.0;

        nota.append("NOTA FISCAL\n");
        nota.append("-----------------------------\n");

        for (Produto p : carrinho) {
            double subtotal = p.getValor() * p.getQuantidade();
            nota.append(p.getNome())
                .append(" x").append(p.getQuantidade())
                .append(" - R$ ").append(subtotal).append("\n");
            total += subtotal;
        }

        nota.append("-----------------------------\n");
        nota.append("TOTAL: R$ ").append(total);

        jTextArea1.setText(nota.toString());
    }
    
private void exibirProdutos(int indice) {
        for (int i = 0; i < 6; i++) {
            int idxProduto = indice + i;
            if (idxProduto < listaProduto.size()) {
                Produto p = listaProduto.get(idxProduto);
                labelsNome[i].setText("Nome: " + p.getNome());
                labelsPreco[i].setText("Preço: R$" + p.getValor());
                labelsAno[i].setText("Ano: " + p.getAno());
                labelsDescricao[i].setText("Descrição" +p.getDescricao());
                spinnersQtd[i].setValue(0);

                URL url = getClass().getClassLoader().getResource(p.getCaminhoImagem());
                if (url != null) {
                    labelsImg[i].setIcon(new ImageIcon(url));
                } else {
                    System.out.println("Imagem não encontrada: " + p.getCaminhoImagem());
                    labelsImg[i].setIcon(null);
                }
            } else {
                labelsNome[i].setText("Nome:");
                labelsPreco[i].setText("Preço:");
                labelsAno[i].setText("Ano:");
                labelsDescricao[i].setText("Descrição:");
                spinnersQtd[i].setValue(0);
                labelsImg[i].setIcon(null);
            }
        }
    }

private void limparBusca() {
    indiceAtual = 0;
    exibirProdutos(indiceAtual);
    txtPesquisar.setText("");

    // Mostra todos os componentes novamente
    for (int i = 0; i < 6; i++) {
        labelsNome[i].setVisible(true);
        labelsPreco[i].setVisible(true);
        labelsAno[i].setVisible(true);
        labelsDescricao[i].setVisible(true);
        labelsImg[i].setVisible(true);
        spinnersQtd[i].setVisible(true);
        bntAdicionar[i].setVisible(true);
        
    }
}
    
private void exibirProdutosFiltrados(List<Produto> produtos) {
    for (int i = 0; i < 6; i++) {
        if (i < produtos.size()) {
            Produto p = produtos.get(i);
            labelsNome[i].setText("Nome: " + p.getNome());
            labelsPreco[i].setText("Preço: R$" + p.getValor());
            labelsAno[i].setText("Ano: " + p.getAno());
            labelsDescricao[i].setText("Descrição: " + p.getDescricao());
            spinnersQtd[i].setValue(0);

            URL url = getClass().getClassLoader().getResource(p.getCaminhoImagem());
            if (url != null) {
                labelsImg[i].setIcon(new ImageIcon(url));
            } else {
                labelsImg[i].setIcon(null);
            }

            // Torna visíveis apenas os componentes usados
            labelsNome[i].setVisible(true);
            labelsPreco[i].setVisible(true);
            labelsAno[i].setVisible(true);
            labelsDescricao[i].setVisible(true);
            labelsImg[i].setVisible(true);
            spinnersQtd[i].setVisible(true);
            bntAdicionar[i].setVisible(true);
            labelsQuantidade[i].setVisible(true);
            

        } else {
            // Esconde os componentes que não têm produto
            labelsNome[i].setVisible(false);
            labelsPreco[i].setVisible(false);
            labelsAno[i].setVisible(false);
            labelsDescricao[i].setVisible(false);
            labelsImg[i].setVisible(false);
            spinnersQtd[i].setVisible(false);
            bntAdicionar[i].setVisible(false);
            labelsQuantidade[i].setVisible(false);
        }
    }
    
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLoja = new javax.swing.JPanel();
        panelBarraSuperior = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        panelProdutos = new javax.swing.JPanel();
        labelMenuItens = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelImg1 = new javax.swing.JLabel();
        labelNome1 = new javax.swing.JLabel();
        labelPreco1 = new javax.swing.JLabel();
        labelQuantidade1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        labelAno1 = new javax.swing.JLabel();
        bntAdicionar1 = new javax.swing.JButton();
        labelDescricao1 = new javax.swing.JLabel();
        panelNotaFiscal = new javax.swing.JPanel();
        bntComprar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        bntSair = new javax.swing.JButton();
        labelPesquisar = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        bntBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        labelNome2 = new javax.swing.JLabel();
        labelPreco2 = new javax.swing.JLabel();
        labelQuantidade2 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        labelAno2 = new javax.swing.JLabel();
        bntAdicionar2 = new javax.swing.JButton();
        labelDescricao2 = new javax.swing.JLabel();
        labelImg2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labelImg3 = new javax.swing.JLabel();
        labelNome3 = new javax.swing.JLabel();
        labelPreco3 = new javax.swing.JLabel();
        labelQuantidade3 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        labelAno3 = new javax.swing.JLabel();
        bntAdicionar3 = new javax.swing.JButton();
        labelDescricao3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labelImg4 = new javax.swing.JLabel();
        labelNome4 = new javax.swing.JLabel();
        labelPreco4 = new javax.swing.JLabel();
        labelQuantidade4 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        labelAno4 = new javax.swing.JLabel();
        bntAdicionar4 = new javax.swing.JButton();
        labelDescricao4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        labelImg6 = new javax.swing.JLabel();
        labelNome6 = new javax.swing.JLabel();
        labelPreco6 = new javax.swing.JLabel();
        labelQuantidade6 = new javax.swing.JLabel();
        jSpinner6 = new javax.swing.JSpinner();
        labelAno6 = new javax.swing.JLabel();
        bntAdicionar6 = new javax.swing.JButton();
        labelDescricao6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        labelImg5 = new javax.swing.JLabel();
        labelNome5 = new javax.swing.JLabel();
        labelPreco5 = new javax.swing.JLabel();
        labelQuantidade5 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        labelAno5 = new javax.swing.JLabel();
        bntAdicionar5 = new javax.swing.JButton();
        labelDescricao5 = new javax.swing.JLabel();
        btnProx = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        bntLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelLoja.setBackground(new java.awt.Color(19, 26, 37));

        panelBarraSuperior.setBackground(new java.awt.Color(19, 26, 37));
        panelBarraSuperior.setForeground(new java.awt.Color(51, 255, 204));

        labelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/logo.png"))); // NOI18N

        javax.swing.GroupLayout panelBarraSuperiorLayout = new javax.swing.GroupLayout(panelBarraSuperior);
        panelBarraSuperior.setLayout(panelBarraSuperiorLayout);
        panelBarraSuperiorLayout.setHorizontalGroup(
            panelBarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarraSuperiorLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBarraSuperiorLayout.setVerticalGroup(
            panelBarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        labelMenuItens.setBackground(new java.awt.Color(205, 201, 241));
        labelMenuItens.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        labelMenuItens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuItens.setText("MENU ITENS");

        labelNome1.setText("Nome:");

        labelPreco1.setText("Preço:");

        labelQuantidade1.setText("Quantidade");

        labelAno1.setText("Ano:");

        bntAdicionar1.setText("ADICIONAR");

        labelDescricao1.setText("Descrição:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelQuantidade1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao1)
                            .addComponent(labelAno1)
                            .addComponent(labelPreco1)
                            .addComponent(labelNome1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(bntAdicionar1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelImg1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(labelNome1)
                .addGap(8, 8, 8)
                .addComponent(labelPreco1)
                .addGap(8, 8, 8)
                .addComponent(labelAno1)
                .addGap(8, 8, 8)
                .addComponent(labelDescricao1)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidade1)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(bntAdicionar1)
                .addGap(10, 10, 10))
        );

        panelNotaFiscal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(113, 134, 153)));

        bntComprar.setText("COMPRAR");
        bntComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntComprarActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout panelNotaFiscalLayout = new javax.swing.GroupLayout(panelNotaFiscal);
        panelNotaFiscal.setLayout(panelNotaFiscalLayout);
        panelNotaFiscalLayout.setHorizontalGroup(
            panelNotaFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotaFiscalLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(bntComprar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelNotaFiscalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        panelNotaFiscalLayout.setVerticalGroup(
            panelNotaFiscalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNotaFiscalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntComprar)
                .addGap(15, 15, 15))
        );

        bntSair.setText("SAIR");
        bntSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSairActionPerformed(evt);
            }
        });

        labelPesquisar.setText("Pesquisar");

        txtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarActionPerformed(evt);
            }
        });

        bntBuscar.setText("BUSCAR");
        bntBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarActionPerformed(evt);
            }
        });

        labelNome2.setText("Nome:");

        labelPreco2.setText("Preço:");

        labelQuantidade2.setText("Quantidade");

        labelAno2.setText("Ano:");

        bntAdicionar2.setText("ADICIONAR");

        labelDescricao2.setText("Descrição:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelQuantidade2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao2)
                            .addComponent(labelPreco2)
                            .addComponent(labelNome2)
                            .addComponent(labelAno2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(bntAdicionar2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(labelImg2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(labelImg2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(labelAno2)
                        .addGap(8, 8, 8)
                        .addComponent(labelDescricao2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelNome2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPreco2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelQuantidade2))
                .addGap(10, 10, 10)
                .addComponent(bntAdicionar2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNome3.setText("Nome:");

        labelPreco3.setText("Preço:");

        labelQuantidade3.setText("Quantidade");

        labelAno3.setText("Ano:");

        bntAdicionar3.setText("ADICIONAR");

        labelDescricao3.setText("Descrição:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImg3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPreco3)
                            .addComponent(labelNome3)
                            .addComponent(labelAno3)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(labelQuantidade3)
                                    .addGap(40, 40, 40)
                                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(labelDescricao3)
                                    .addGap(112, 112, 112)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(bntAdicionar3)))
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(labelImg3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelNome3)
                .addGap(8, 8, 8)
                .addComponent(labelPreco3)
                .addGap(8, 8, 8)
                .addComponent(labelAno3)
                .addGap(8, 8, 8)
                .addComponent(labelDescricao3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidade3)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(bntAdicionar3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNome4.setText("Nome:");

        labelPreco4.setText("Preço:");

        labelQuantidade4.setText("Quantidade");

        labelAno4.setText("Ano:");

        bntAdicionar4.setText("ADICIONAR");

        labelDescricao4.setText("Descrição:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImg4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelQuantidade4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao4)
                            .addComponent(labelAno4)
                            .addComponent(labelPreco4)
                            .addComponent(labelNome4)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(bntAdicionar4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(labelImg4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(labelNome4)
                .addGap(8, 8, 8)
                .addComponent(labelPreco4)
                .addGap(8, 8, 8)
                .addComponent(labelAno4)
                .addGap(8, 8, 8)
                .addComponent(labelDescricao4)
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidade4)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(bntAdicionar4)
                .addGap(10, 10, 10))
        );

        labelNome6.setText("Nome:");

        labelPreco6.setText("Preço:");

        labelQuantidade6.setText("Quantidade");

        labelAno6.setText("Ano:");

        bntAdicionar6.setText("ADICIONAR");

        labelDescricao6.setText("Descrição:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelImg6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(labelQuantidade6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDescricao6)
                                    .addComponent(labelNome6)
                                    .addComponent(labelAno6)
                                    .addComponent(labelPreco6))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bntAdicionar6)
                .addGap(39, 39, 39))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(labelImg6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labelNome6)
                .addGap(8, 8, 8)
                .addComponent(labelPreco6)
                .addGap(8, 8, 8)
                .addComponent(labelAno6)
                .addGap(8, 8, 8)
                .addComponent(labelDescricao6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidade6)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntAdicionar6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNome5.setText("Nome:");

        labelPreco5.setText("Preço:");

        labelQuantidade5.setText("Quantidade");

        labelAno5.setText("Ano:");

        bntAdicionar5.setText("ADICIONAR");

        labelDescricao5.setText("Descrição:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelImg5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(labelQuantidade5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao5)
                            .addComponent(labelPreco5)
                            .addComponent(labelNome5)
                            .addComponent(labelAno5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(bntAdicionar5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(labelImg5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(labelAno5)
                        .addGap(8, 8, 8)
                        .addComponent(labelDescricao5))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(labelNome5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPreco5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelQuantidade5))
                .addGap(10, 10, 10)
                .addComponent(bntAdicionar5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnProx.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnProx.setText("PROXIMO");
        btnProx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProxActionPerformed(evt);
            }
        });

        btnAnterior.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        btnAnterior.setText("ANTERIOR");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        bntLimpar.setText("LIMPAR BUSCA");
        bntLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProdutosLayout = new javax.swing.GroupLayout(panelProdutos);
        panelProdutos.setLayout(panelProdutosLayout);
        panelProdutosLayout.setHorizontalGroup(
            panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMenuItens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelProdutosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProdutosLayout.createSequentialGroup()
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelProdutosLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5))
                            .addGroup(panelProdutosLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdutosLayout.createSequentialGroup()
                                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAnterior)
                                    .addComponent(btnProx))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bntSair, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelProdutosLayout.createSequentialGroup()
                        .addComponent(labelPesquisar)
                        .addGap(31, 31, 31)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(bntBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(bntLimpar)))
                .addContainerGap(199, Short.MAX_VALUE))
        );
        panelProdutosLayout.setVerticalGroup(
            panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdutosLayout.createSequentialGroup()
                .addComponent(labelMenuItens)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesquisar)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntBuscar)
                    .addComponent(bntLimpar))
                .addGap(17, 17, 17)
                .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProdutosLayout.createSequentialGroup()
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))
                    .addGroup(panelProdutosLayout.createSequentialGroup()
                        .addComponent(panelNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProdutosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bntSair, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(panelProdutosLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnProx, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout panelLojaLayout = new javax.swing.GroupLayout(panelLoja);
        panelLoja.setLayout(panelLojaLayout);
        panelLojaLayout.setHorizontalGroup(
            panelLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLojaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addComponent(panelBarraSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLojaLayout.setVerticalGroup(
            panelLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLojaLayout.createSequentialGroup()
                .addComponent(panelBarraSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarActionPerformed

    private void bntComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntComprarActionPerformed
        if (carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Carrinho vazio!");
        } else {
            JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!");
            carrinho.clear();
            atualizarNotaFiscal();
        }
    }//GEN-LAST:event_bntComprarActionPerformed

    private void bntBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarActionPerformed
        String termoBusca = txtPesquisar.getText().toLowerCase().trim();

        if (termoBusca.isEmpty()) {
            indiceAtual = 0;
            exibirProdutos(indiceAtual);
            return;
        }

        List<Produto> produtosFiltrados = new ArrayList<>();

        for (Produto p : listaProduto) {
            if (p.getNome().toLowerCase().contains(termoBusca) ||
                p.getDescricao().toLowerCase().contains(termoBusca) ||
                String.valueOf(p.getAno()).contains(termoBusca)) {
                produtosFiltrados.add(p);
            }
        }

        if (produtosFiltrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado.");
        } else {
            exibirProdutosFiltrados(produtosFiltrados);
        }     
    }//GEN-LAST:event_bntBuscarActionPerformed

    private void btnProxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProxActionPerformed
        if (indiceAtual + 6 < listaProduto.size()) {
            indiceAtual += 6;
            exibirProdutos(indiceAtual);
        } else {
            JOptionPane.showMessageDialog(this, "Não há mais produtos.");
        }
    }//GEN-LAST:event_btnProxActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (indiceAtual >= 6) {  // Verifica se é possível voltar um grupo
        indiceAtual -= 6;
        exibirProdutos(indiceAtual);
    } else {
        JOptionPane.showMessageDialog(this, "Você já está na primeira página de produtos.");
    }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void bntLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bntLimparActionPerformed

    private void bntSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bntSairActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAdicionar1;
    private javax.swing.JButton bntAdicionar2;
    private javax.swing.JButton bntAdicionar3;
    private javax.swing.JButton bntAdicionar4;
    private javax.swing.JButton bntAdicionar5;
    private javax.swing.JButton bntAdicionar6;
    private javax.swing.JButton bntBuscar;
    private javax.swing.JButton bntComprar;
    private javax.swing.JButton bntLimpar;
    private javax.swing.JButton bntSair;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnProx;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelAno1;
    private javax.swing.JLabel labelAno2;
    private javax.swing.JLabel labelAno3;
    private javax.swing.JLabel labelAno4;
    private javax.swing.JLabel labelAno5;
    private javax.swing.JLabel labelAno6;
    private javax.swing.JLabel labelDescricao1;
    private javax.swing.JLabel labelDescricao2;
    private javax.swing.JLabel labelDescricao3;
    private javax.swing.JLabel labelDescricao4;
    private javax.swing.JLabel labelDescricao5;
    private javax.swing.JLabel labelDescricao6;
    private javax.swing.JLabel labelImg1;
    private javax.swing.JLabel labelImg2;
    private javax.swing.JLabel labelImg3;
    private javax.swing.JLabel labelImg4;
    private javax.swing.JLabel labelImg5;
    private javax.swing.JLabel labelImg6;
    private javax.swing.JLabel labelMenuItens;
    private javax.swing.JLabel labelNome1;
    private javax.swing.JLabel labelNome2;
    private javax.swing.JLabel labelNome3;
    private javax.swing.JLabel labelNome4;
    private javax.swing.JLabel labelNome5;
    private javax.swing.JLabel labelNome6;
    private javax.swing.JLabel labelPesquisar;
    private javax.swing.JLabel labelPreco1;
    private javax.swing.JLabel labelPreco2;
    private javax.swing.JLabel labelPreco3;
    private javax.swing.JLabel labelPreco4;
    private javax.swing.JLabel labelPreco5;
    private javax.swing.JLabel labelPreco6;
    private javax.swing.JLabel labelQuantidade1;
    private javax.swing.JLabel labelQuantidade2;
    private javax.swing.JLabel labelQuantidade3;
    private javax.swing.JLabel labelQuantidade4;
    private javax.swing.JLabel labelQuantidade5;
    private javax.swing.JLabel labelQuantidade6;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelBarraSuperior;
    private javax.swing.JPanel panelLoja;
    private javax.swing.JPanel panelNotaFiscal;
    private javax.swing.JPanel panelProdutos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}
