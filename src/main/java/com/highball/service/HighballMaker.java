package com.highball.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.highball.model.HighballRecipe;

/**
 * IoT 연결을 시뮬레이션하여 하이볼 제조 과정을 구현하는 클래스
 */
public class HighballMaker {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");

  /**
   * 하이볼 제조 과정을 시뮬레이션합니다.
   * 
   * @param recipe 제조할 하이볼 레시피
   * @return 제조 과정 로그
   */
  public String makeHighball(HighballRecipe recipe) {
    StringBuilder process = new StringBuilder();
    process.append("==== 하이볼 제조를 시작합니다 ====\n");
    process.append("제조 시작 시간: ").append(LocalDateTime.now().format(formatter)).append("\n\n");

    process.append("1. 제조할 하이볼: ").append(recipe.getName()).append("\n");
    process.append("   - ").append(recipe.getDescription()).append("\n");
    process.append("   - 제조량: 총 ").append(recipe.getTotalAmount()).append("ml\n\n");

    // 얼음 준비
    process.append("2. 얼음을 준비중입니다...\n");
    simulateProcess(1000);
    process.append("   * 얼음이 잔에 담겼습니다.\n\n");

    // 위스키 따르기
    int whiskyAmount = recipe.getWhiskyAmount();
    process.append("3. 위스키를 따르는 중입니다...\n");
    process.append("   * 비율: 1 파트\n");
    process.append("   * 양: ").append(whiskyAmount).append("ml\n");
    simulateProcess(1500);
    process.append("   * 위스키가 잔에 담겼습니다.\n\n");

    // 탄산수 or 특수 워터 따르기
    String waterType = recipe.getSpecialWater().isEmpty() ? "탄산수" : recipe.getSpecialWater();
    int sodaAmount = recipe.getSodaAmount();
    process.append("4. ").append(waterType).append("를 따르는 중입니다...\n");
    process.append("   * 비율: ").append(recipe.getSodaRatio()).append(" 파트\n");
    process.append("   * 양: ").append(sodaAmount).append("ml\n");
    simulateProcess(2000);
    process.append("   * ").append(waterType).append("가 잔에 담겼습니다.\n\n");

    // 레몬 추가 (해당하는 경우)
    if (recipe.hasLemon()) {
      process.append("5. 레몬을 추가하는 중입니다...\n");
      simulateProcess(1000);
      process.append("   * 레몬이 잘 담겼습니다.\n\n");
    }

    // 완성
    process.append("==== 하이볼 제조가 완료되었습니다 ====\n");
    process.append("최종 레시피: ").append(recipe.getRatioDescription()).append("\n");
    process.append("총 제조량: ").append(recipe.getTotalAmount()).append("ml");
    process.append(" (위스키 ").append(whiskyAmount).append("ml + ");
    process.append(waterType).append(" ").append(sodaAmount).append("ml");
    if (recipe.hasLemon()) {
      process.append(" + 레몬");
    }
    process.append(")\n");
    process.append("제조 완료 시간: ").append(LocalDateTime.now().format(formatter)).append("\n");
    process.append("맛있게 드세요!\n");

    return process.toString();
  }

  /**
   * 제조 과정을 시뮬레이션하기 위해 지연 시간을 추가합니다.
   * 
   * @param milliseconds 지연할 시간(밀리초)
   */
  private void simulateProcess(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}