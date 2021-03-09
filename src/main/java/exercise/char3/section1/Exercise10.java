package exercise.char3.section1;

import java.util.Queue;

/**
 * @author ZZY
 * @date 2021/3/9 16:23
 */
public class Exercise10 {

    public static void main(String[] args) {
        Exercise3<Character, Integer> exercise3 = new Exercise3<>();
        Character[] cs = {'e', 'a', 's', 'y', 'q', 'u', 'e', 's', 't', 'i', 'o', 'n'};
        for (int i = 0; i < cs.length; i++) {
            exercise3.put(cs[i], i);
        }
        Queue<Character> queue = (Queue) exercise3.keys();
        for (Character c : queue) {
            System.out.println(c + "(index): " + exercise3.get(c) + " ");
        }
    }
}
