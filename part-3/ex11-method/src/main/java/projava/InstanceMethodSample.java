package projava;

public class InstanceMethodSample {
    record Student(String name, int englishScore, int mathScore){
        // インスタンスメソッドを定義する
        int average() {
            return (this.englishScore() + this.mathScore()) / 2;
        }
    }

    public static void main(String[] args) {
        var taro = new Student("taro", 60, 80);
        // インスタンスメソッドの呼び出し
        var a = taro.average();
        System.out.println("平均点は%d点です".formatted(a));
    }
}
