package projava;

import java.util.List;

public class InterfaceSample {
    interface Named {
        String name();
    }

    record Student(String name, int score) implements Named {}
    record Teacher(String name, String subject) implements Named {}

    public static void main(String[] args) {
        var people = List.of(new Student("taro", 80), new Teacher("jiro", "Math"));
        for (var p : people) {
            var n = p instanceof Student s ? s.name() :
                    p instanceof Teacher t ? t.name() :
                                    "---";
            System.out.println("こんにちは%sさん".formatted(n));
        }
    }
}
