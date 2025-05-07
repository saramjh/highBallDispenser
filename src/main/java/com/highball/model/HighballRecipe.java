package com.highball.model;

/**
 * 하이볼 레시피 정보를 저장하는 클래스
 */
public class HighballRecipe {
  private final String name;
  private final String description;
  private final double whiskyRatio;
  private final double sodaRatio;
  private final boolean hasLemon;
  private final String specialWater; // 일반 탄산수, 토닉워터, 진저에일 등
  private int totalAmount; // ml 단위의 총 제조량 (기본값 300ml)

  public HighballRecipe(String name, String description, double whiskyRatio,
      double sodaRatio, boolean hasLemon, String specialWater) {
    this.name = name;
    this.description = description;
    this.whiskyRatio = whiskyRatio;
    this.sodaRatio = sodaRatio;
    this.hasLemon = hasLemon;
    this.specialWater = specialWater;
    this.totalAmount = 300; // 기본값 300ml
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getWhiskyRatio() {
    return whiskyRatio;
  }

  public double getSodaRatio() {
    return sodaRatio;
  }

  public boolean hasLemon() {
    return hasLemon;
  }

  public String getSpecialWater() {
    return specialWater;
  }

  public String getRatioDescription() {
    return String.format("위스키 1 : %s %.0f", specialWater.isEmpty() ? "탄산수" : specialWater, sodaRatio);
  }

  /**
   * 총 제조량을 설정합니다.
   * 
   * @param totalAmount 총 제조량 (ml)
   */
  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  /**
   * 총 제조량을 반환합니다.
   * 
   * @return 총 제조량 (ml)
   */
  public int getTotalAmount() {
    return totalAmount;
  }

  /**
   * 위스키의 양을 계산합니다.
   * 
   * @return 위스키 양 (ml)
   */
  public int getWhiskyAmount() {
    return (int) Math.round((totalAmount / (whiskyRatio + sodaRatio)) * whiskyRatio);
  }

  /**
   * 탄산수/특수 워터의 양을 계산합니다.
   * 
   * @return 탄산수 양 (ml)
   */
  public int getSodaAmount() {
    return (int) Math.round((totalAmount / (whiskyRatio + sodaRatio)) * sodaRatio);
  }

  @Override
  public String toString() {
    return name + " - " + getRatioDescription();
  }
}