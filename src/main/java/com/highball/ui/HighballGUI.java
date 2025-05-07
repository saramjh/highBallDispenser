package com.highball.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import com.highball.model.HighballRecipe;
import com.highball.service.HighballMaker;
import com.highball.service.RecipeDatabase;

/**
 * 하이볼 제조 시스템의 그래픽 사용자 인터페이스
 */
public class HighballGUI extends JFrame {
  private final HighballMaker highballMaker;
  private final List<HighballRecipe> recipes;

  private JComboBox<String> recipeComboBox;
  private JTextArea recipeDescription;
  private JTextArea processOutput;
  private JButton makeButton;
  private JLabel statusLabel;
  private JSpinner amountSpinner; // 제조량 입력 스피너 추가

  public HighballGUI() {
    // 서비스 초기화
    highballMaker = new HighballMaker();
    recipes = RecipeDatabase.getAllRecipes();

    // GUI 설정
    setTitle("하이볼 제조 시스템");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // 헤더 패널
    JPanel headerPanel = new JPanel();
    headerPanel.setBackground(new Color(44, 62, 80));
    headerPanel.setPreferredSize(new Dimension(800, 80));
    headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    JLabel titleLabel = new JLabel("생마차! IoT 하이볼 제조 시스템");
    titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
    titleLabel.setForeground(Color.WHITE);
    headerPanel.add(titleLabel);

    add(headerPanel, BorderLayout.NORTH);

    // 주요 콘텐츠 패널
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // 레시피 선택 패널
    JPanel selectionPanel = new JPanel();
    selectionPanel.setLayout(new GridLayout(2, 1, 5, 5));
    selectionPanel.setBorder(BorderFactory.createTitledBorder("하이볼 옵션을 선택하세요"));

    // 레시피 선택 패널 (상단)
    JPanel recipeSelectPanel = new JPanel(new BorderLayout());

    String[] recipeNames = recipes.stream()
        .map(HighballRecipe::getName)
        .toArray(String[]::new);

    recipeComboBox = new JComboBox<>(recipeNames);
    recipeComboBox.addActionListener(e -> updateRecipeDescription());

    recipeSelectPanel.add(new JLabel("하이볼 스타일:"), BorderLayout.WEST);
    recipeSelectPanel.add(recipeComboBox, BorderLayout.CENTER);

    // 제조량 입력 패널 (하단)
    JPanel amountPanel = new JPanel(new BorderLayout());
    amountPanel.add(new JLabel("제조량(ml):"), BorderLayout.WEST);

    // 제조량 스피너 생성 (200ml~700ml, 기본값 300ml)
    SpinnerNumberModel spinnerModel = new SpinnerNumberModel(300, 200, 700, 50);
    amountSpinner = new JSpinner(spinnerModel);
    amountSpinner.setToolTipText("원하시는 양을 선택하세요 (200ml~700ml)");

    JComponent editor = amountSpinner.getEditor();
    if (editor instanceof JSpinner.DefaultEditor) {
      JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor) editor;
      defaultEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
    }

    amountPanel.add(amountSpinner, BorderLayout.CENTER);

    selectionPanel.add(recipeSelectPanel);
    selectionPanel.add(amountPanel);

    // 레시피 설명 패널
    JPanel descriptionPanel = new JPanel();
    descriptionPanel.setLayout(new BorderLayout());
    descriptionPanel.setBorder(BorderFactory.createTitledBorder("하이볼 설명"));

    recipeDescription = new JTextArea(5, 30);
    recipeDescription.setEditable(false);
    recipeDescription.setLineWrap(true);
    recipeDescription.setWrapStyleWord(true);
    recipeDescription.setBackground(new Color(240, 240, 240));

    JScrollPane descriptionScrollPane = new JScrollPane(recipeDescription);
    descriptionPanel.add(descriptionScrollPane, BorderLayout.CENTER);

    // 작업 패널
    JPanel actionPanel = new JPanel();
    actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    makeButton = new JButton("하이볼 제조 시작");
    makeButton.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    makeButton.setBackground(new Color(52, 152, 219));
    makeButton.setForeground(Color.BLACK);
    makeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        makeHighball();
      }
    });
    actionPanel.add(makeButton);

    // 상단 패널 조합
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    topPanel.add(selectionPanel, BorderLayout.NORTH);
    topPanel.add(descriptionPanel, BorderLayout.CENTER);
    topPanel.add(actionPanel, BorderLayout.SOUTH);

    contentPanel.add(topPanel, BorderLayout.NORTH);

    // 프로세스 출력 패널
    JPanel outputPanel = new JPanel();
    outputPanel.setLayout(new BorderLayout());
    outputPanel.setBorder(BorderFactory.createTitledBorder("제조 과정"));

    processOutput = new JTextArea(15, 40);
    processOutput.setEditable(false);
    processOutput.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
    JScrollPane outputScrollPane = new JScrollPane(processOutput);
    outputPanel.add(outputScrollPane, BorderLayout.CENTER);

    contentPanel.add(outputPanel, BorderLayout.CENTER);

    // 상태 패널
    JPanel statusPanel = new JPanel();
    statusPanel.setLayout(new BorderLayout());
    statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    statusLabel = new JLabel("준비 완료");
    statusLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
    statusPanel.add(statusLabel, BorderLayout.WEST);

    contentPanel.add(statusPanel, BorderLayout.SOUTH);

    add(contentPanel, BorderLayout.CENTER);

    // 초기 레시피 설명 업데이트
    updateRecipeDescription();

    // 윈도우 중앙 배치
    setLocationRelativeTo(null);
  }

  /**
   * 선택된 레시피의 설명을 업데이트합니다.
   */
  private void updateRecipeDescription() {
    int selectedIndex = recipeComboBox.getSelectedIndex();
    HighballRecipe selectedRecipe = recipes.get(selectedIndex);

    StringBuilder sb = new StringBuilder();
    sb.append("【 ").append(selectedRecipe.getName()).append(" 】\n\n");
    sb.append(selectedRecipe.getDescription()).append("\n\n");
    sb.append("비율: ").append(selectedRecipe.getRatioDescription());

    if (selectedRecipe.hasLemon()) {
      sb.append(" + 레몬");
    }

    // 현재 설정된 제조량 정보 추가
    int amount = (Integer) amountSpinner.getValue();
    selectedRecipe.setTotalAmount(amount);
    sb.append("\n\n제조량: ").append(amount).append("ml");
    sb.append(" (위스키 약 ").append(selectedRecipe.getWhiskyAmount()).append("ml)");

    recipeDescription.setText(sb.toString());
  }

  /**
   * 선택된 레시피로 하이볼을 제조합니다.
   */
  private void makeHighball() {
    int selectedIndex = recipeComboBox.getSelectedIndex();
    final HighballRecipe selectedRecipe = recipes.get(selectedIndex);

    // 제조량 설정
    int amount = (Integer) amountSpinner.getValue();
    selectedRecipe.setTotalAmount(amount);

    // UI 업데이트
    makeButton.setEnabled(false);
    recipeComboBox.setEnabled(false);
    amountSpinner.setEnabled(false);
    statusLabel.setText("하이볼 제조 중...");
    processOutput.setText("하이볼 제조를 시작합니다...\n");

    // 별도 스레드에서 제조 과정 실행 (UI 블로킹 방지)
    SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
      @Override
      protected String doInBackground() {
        return highballMaker.makeHighball(selectedRecipe);
      }

      @Override
      protected void done() {
        try {
          String process = get();
          processOutput.setText(process);
          makeButton.setEnabled(true);
          recipeComboBox.setEnabled(true);
          amountSpinner.setEnabled(true);
          statusLabel.setText("하이볼 제조 완료!");
        } catch (Exception e) {
          processOutput.setText("오류 발생: " + e.getMessage());
          makeButton.setEnabled(true);
          recipeComboBox.setEnabled(true);
          amountSpinner.setEnabled(true);
          statusLabel.setText("하이볼 제조 실패");
        }
      }
    };

    worker.execute();
  }

  /**
   * 애플리케이션을 실행합니다.
   */
  public static void main(String[] args) {
    // Look and Feel 설정 (MacOS 스타일)
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    // GUI 표시
    SwingUtilities.invokeLater(() -> {
      HighballGUI gui = new HighballGUI();
      gui.setVisible(true);
    });
  }
}