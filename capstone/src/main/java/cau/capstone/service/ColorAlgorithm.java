package cau.capstone.service;

import cau.capstone.dto.color.ColorClassification;
import cau.capstone.dto.color.RGBColor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ColorAlgorithm {

    List<RGBColor> RGBColors;
    static float[] hsbValues;

    // BaseColor 의 채도와 명도
    static int BaseS;

    // ColorClassification Enum 리스트
    private static final List<ColorClassification> values = new ArrayList<>(Arrays.asList(ColorClassification.values()));

    // 생성자
    public ColorAlgorithm(List<RGBColor> RGBColors) {
        RGBColors.sort(Collections.reverseOrder());
        this.RGBColors = RGBColors.subList(0, 3);  // 3 번째 색상까지만 사용
    }

    // BaseColor 채도 get method
    public int getBaseS() {
        return BaseS;
    }

    // base 색상 기반 추천
    public List<ColorClassification> recommendBaseColor() {
        // 추천할 color 를 담은 리스트
        List<ColorClassification> recommendColors;

        // RGB to HSV value
        hsbValues = RGBToHSV(this.RGBColors.get(0));
        int H = Math.round(hsbValues[0] * 360);
        BaseS = Math.round(hsbValues[1] * 100);  // baseColor 의 채도만 고려

        // H, S 가 모두 0 인 경우 -> r==g==b : 화이트 꽃 추천
        if (H == 0 && BaseS == 0) {
            recommendColors = values.subList(7, 8);  // WHITE
            return recommendColors;
        }

        // Enum 의 index 를 활용해서 base color 의 보색이 어디서부터 어디까지인지 결정한다.
        int startColorIdx = getColor((H + 330) % 360).ordinal();
        int endColorIdx = getColor((H + 30) % 360).ordinal();

        if (startColorIdx <= endColorIdx) {
            recommendColors = values.subList(startColorIdx, endColorIdx+1);
        }
        else {  // 0 ~ end, start ~ 7
            recommendColors = values.subList(0, endColorIdx+1);
            recommendColors.addAll(values.subList(startColorIdx, 7));
        }

        // 추천 Color list return List<ColorClassification>
        return recommendColors;
    }

    // point 색상 기반 추천
    public List<ColorClassification> recommendPointColor() {
        List<ColorClassification> recommendColors;

        // pointColor 1 RGB to HSV value
        hsbValues = RGBToHSV(this.RGBColors.get(1));
        int H1 = Math.round(hsbValues[0] * 360);
        int S1 = Math.round(hsbValues[1] * 100);
        // pointColor 2 RGB to HSV value
        hsbValues =  RGBToHSV(this.RGBColors.get(2));
        int H2 = Math.round(hsbValues[0] * 360);
        int S2 = Math.round(hsbValues[1] * 100);

        // H1, S1 or H2, S2 가 0 인 경우 <- r==g==b
        if (H1 == 0 && S1 == 0) {
            recommendColors = values.subList(7, 8);  // WHITE
        }
        else {
            int pointColorIdx = getColor((H1 + 180) % 360).ordinal();
            recommendColors = values.subList(pointColorIdx, pointColorIdx+1);
        }

        if (H1 != H2) {  // point 색상이 서로 다른 경우
            if (H2 == 0 && S2 == 0) {
                recommendColors = values.subList(7, 8);  // WHITE
            }
            else {
                int pointColorIdx = getColor((H2 + 180) % 360).ordinal();
                recommendColors.addAll(values.subList(pointColorIdx, pointColorIdx + 1));
            }
        }

        // 추천 Color list return List<ColorClassification>
        return recommendColors;
    }

    // RGB 를 HSV 로 변경해주는 method
    private float[] RGBToHSV(RGBColor rgbColor) {
        int R = rgbColor.getR();
        int G = rgbColor.getG();
        int B = rgbColor.getB();

        return Color.RGBtoHSB(R, G, B, null);  // float[3] = [H, S, V]
    }

    // H 의 범위에 따라 Color 를 분류해주는 method
    private ColorClassification getColor(int H) {
//        if (H == 0) {
//            return ColorClassification.WHITE;
//        }  // 꽃이 흰색인 경우는 위에서 처리 됨
        if (348 <= H || H <= 12) {
            return ColorClassification.RED;
        }
        else if (H <= 40) {
            return ColorClassification.ORANGE;
        }
        else if (H <= 70) {
            return ColorClassification.YELLOW;
        }
        else if (H <= 180) {
            return ColorClassification.GREEN;
        }
        else if (H <= 265) {
            return ColorClassification.BLUE;
        }
        else if (H <= 307) {
            return ColorClassification.PURPLE;
        }
        else {  // H <= 347
            return ColorClassification.PINK;
        }
    }
}
