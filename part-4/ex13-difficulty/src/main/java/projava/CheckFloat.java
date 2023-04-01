package projava;

public class CheckFloat {

    enum FloatState {
        START, INT, FRAC_START, FRAC, ZERO
    }

    static boolean check(String data) {
        var state = FloatState.START;
        for (char ch : data.toCharArray()) {
            switch (state) {
                case START -> { // 開始
                    if (ch == '0') {
                        state = FloatState.ZERO;
                    } else if (ch >= '1' && ch <= '9') {
                        state = FloatState.INT;
                    } else {
                        return false;
                    }
                }
                case ZERO -> {
                    if (ch == '.') {
                        state = FloatState.FRAC_START;
                    } else {
                        return false;
                    }
                }
                case INT -> {
                    if (ch >= '0' && ch <= '9') {
                        state = FloatState.INT;
                    } else if (ch == '.') {
                        state = FloatState.FRAC_START;
                    } else {
                        return false;
                    }
                }
                case FRAC_START, FRAC -> {
                    // 小数部
                    if (ch >= '0' && ch <= '9') {
                        state = FloatState.FRAC;
                    } else {
                        return false;
                    }
                }
            }
        }
            return switch (state) {
                case ZERO, INT, FRAC -> true;
                default -> false;
            };
        }

        public static void main(String []args) {
            System.out.println(check(""));
            System.out.println(check("012"));
            System.out.println(check(".12"));
            System.out.println(check("12."));
            System.out.println(check("1.2.3"));
            System.out.println(check("1..3"));
            System.out.println(check("0"));
            System.out.println(check("12"));
            System.out.println(check("12.3"));
            System.out.println(check("0.3"));
            System.out.println(check("12.30"));
        }
}
