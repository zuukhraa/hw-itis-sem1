package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        String str = "";
        int a = adjacencyMatrix.length;
        ArrayList<Integer> status = new ArrayList<>();
        ArrayDeque<Integer> graph = new ArrayDeque<Integer>();
        graph.add(startIndex);
        while(status.size() < a) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < a; i++) {
                if (adjacencyMatrix[i][graph.peekFirst()]){
                    temp.add(i);
                }
            }
            Collections.sort(temp);
            graph.addAll(temp);

            if(!status.contains(graph.peekFirst())) {
                status.add(graph.peekFirst());
                str = str + graph.pop() + ",";
            }
            else{
                graph.removeFirst();
            }
        }
        return str.substring(0, str.length()-1);
    }

    @Override
    public Boolean validateBrackets(String s) {
        String openingBrackets = "([{";
        String closingBrackets = ")]}";
        char[] input = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (Character c : input) {
            if (openingBrackets.contains(c.toString())) {
                stack.add(c);
            } else if (closingBrackets.contains(c.toString())) {
                if (stack.isEmpty()) {
                    return false;
                } else if (closingBrackets.indexOf(c) == openingBrackets.indexOf(stack.peekLast())) {
                    stack.removeLast();
                }
            }
        }
        return stack.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
            int key = 0;
            ArrayList<Character> line = new ArrayList<>();
            ArrayDeque<Integer> nums = new ArrayDeque<>();
            for (int i = 0;i < s.length();i++) {
                line.add(s.charAt(i));
                if (line.get(i).isDigit(s.charAt(i))){
                    int k = Integer.parseInt(Character.toString(s.charAt(i)));
                    nums.push(k);
                } else {

                    if(key == 0){
                        key = nums.getFirst();
                        nums.pop();

                    }

                    switch(line.get(i)){
                        case '+':
                            key = nums.pop()+key;

                            break;
                        case '-':
                            key = nums.pop()-key;

                            break;
                        case '*':
                            key = nums.pop()*key;

                            break;
                        case '/':
                            key = nums.pop()/key;

                            break;

                    }
                }

            }
            return (long)key;
        }
    }



