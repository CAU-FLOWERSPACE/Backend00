package cau.capstone.dto.color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RGBColor implements Comparable {
    private int r;
    private int g;
    private int b;
    private double proportion;

    @Override
    public int compareTo(Object o) {
        RGBColor rgbColor = (RGBColor) o;
        if (this.proportion < rgbColor.proportion) {
            return -1;
        } else if (this.proportion == rgbColor.proportion) {
            return 0;
        } else {
            return 1;
        }
    }
}
