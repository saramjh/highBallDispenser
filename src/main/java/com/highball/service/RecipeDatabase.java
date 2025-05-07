package com.highball.service;

import java.util.ArrayList;
import java.util.List;

import com.highball.model.HighballRecipe;

/**
 * 하이볼 레시피 정보를 관리하는 데이터베이스 클래스
 */
public class RecipeDatabase {
  private static final List<HighballRecipe> recipes = new ArrayList<>();

  static {
    // 레시피 초기화
    // 1. 위스키 1:3 - 깊고 강렬한 풍미
    recipes.add(new HighballRecipe(
        "깊고 강렬한 풍미",
        "위스키 본연의 개성과 깊은 풍미가 가장 도드라지는 비율입니다. 마치 갓 개봉한 위스키의 향이 코끝을 강하게 스치고, " +
            "입안 가득 묵직하고 진한 맛이 퍼지는 듯한 느낌이에요. 탄산은 위스키의 맛을 해치지 않고 부드럽게 감싸주며 깔끔한 마무리감을 " +
            "선사합니다. 위스키를 좋아하는 분께 추천드리는 비율입니다.",
        1.0,
        3.0,
        false,
        ""));

    // 2. 위스키 1:4 - 균형 잡힌 조화
    recipes.add(new HighballRecipe(
        "균형 잡힌 조화",
        "위스키의 향긋함과 탄산의 시원함이 가장 이상적으로 균형을 이루는 황금 비율로 알려져 있습니다. " +
            "위스키의 풍미는 살아있으면서도 탄산의 청량감이 더해져 부드럽게 목으로 넘어갑니다. 서로의 장점을 살려 조화로운 맛을 " +
            "만들어내며, 어떤 상황에서든 부담 없이 즐기기 좋은 편안함이 느껴집니다.",
        1.0,
        4.0,
        false,
        ""));

    // 3. 위스키 1:5 - 가볍고 산뜻한 청량감
    recipes.add(new HighballRecipe(
        "가볍고 산뜻한 청량감",
        "탄산의 상쾌함이 지배적이며, 위스키는 마치 향수처럼 은은하게 뒤따라오는 비율입니다. 입안 가득 톡톡 터지는 " +
            "기포와 함께 시원하고 가벼운 맛이 퍼집니다. 위스키 특유의 맛이 부담스럽거나, 무더운 여름날 갈증을 해소하기 위한 " +
            "음료처럼 즐기고 싶을 때 좋은 선택입니다.",
        1.0,
        5.0,
        false,
        ""));

    // 4. 레몬 하이볼 - 상큼하고 활력 있는 느낌
    recipes.add(new HighballRecipe(
        "상큼하고 활력 있는 느낌",
        "기본적인 비율에 레몬 조각이나 레몬즙을 더하면, 시트러스 향이 더해져 맛이 한층 더 밝고 활기차집니다. " +
            "위스키와 탄산의 조합에 상큼함이 더해져 마치 산뜻한 바람이 부는 듯한 느낌을 주며, 깔끔함과 생동감이 강조됩니다.",
        1.0,
        4.0,
        true,
        ""));

    // 5. 토닉워터 하이볼 - 달콤하거나 스파이시한 변화 (토닉워터)
    recipes.add(new HighballRecipe(
        "달콤하거나 스파이시한 변화 (토닉워터)",
        "탄산수 대신 토닉 워터를 사용하면 색다른 감각을 느낄 수 있습니다. 토닉 워터는 쌉싸름하면서 달콤한 맛을 더해주고, " +
            "이는 단순히 위스키와 탄산의 조합을 넘어선 복합적인 맛의 경험을 선사합니다.",
        1.0,
        4.0,
        false,
        "토닉워터"));

    // 6. 진저에일 하이볼 - 달콤하거나 스파이시한 변화 (진저에일)
    recipes.add(new HighballRecipe(
        "달콤하거나 스파이시한 변화 (진저에일)",
        "탄산수 대신 진저 에일을 사용하면 색다른 감각을 느낄 수 있습니다. 진저 에일은 은은한 생강 향과 단맛, " +
            "그리고 약간의 스파이시함을 더해줍니다. 이는 단순히 위스키와 탄산의 조합을 넘어선 복합적인 맛의 경험을 선사합니다.",
        1.0,
        4.0,
        false,
        "진저에일"));
  }

  /**
   * 모든 하이볼 레시피 목록을 반환합니다.
   * 
   * @return 하이볼 레시피 리스트
   */
  public static List<HighballRecipe> getAllRecipes() {
    return new ArrayList<>(recipes);
  }

  /**
   * 인덱스로 특정 레시피를 조회합니다.
   * 
   * @param index 레시피 인덱스
   * @return 하이볼 레시피
   */
  public static HighballRecipe getRecipeByIndex(int index) {
    if (index < 0 || index >= recipes.size()) {
      throw new IndexOutOfBoundsException("유효하지 않은 레시피 인덱스입니다: " + index);
    }
    return recipes.get(index);
  }

  /**
   * 레시피의 총 개수를 반환합니다.
   * 
   * @return 레시피 개수
   */
  public static int getRecipeCount() {
    return recipes.size();
  }
}