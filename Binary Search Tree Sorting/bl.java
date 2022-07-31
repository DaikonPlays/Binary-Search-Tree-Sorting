import java.util.Arrays;

public class bl{
    public int partition(Integer[] array, int low, int high) {
        int index = high - 1;
        int num = array[index]; //2
        int smallerBefore = low; //0
        int largerAfter = high - 2; //5, 4 
        while (smallerBefore < largerAfter) { 
            if (array[smallerBefore].compareTo(num) < 0) {
                smallerBefore += 1;
            } else {
                swap(array, smallerBefore, largerAfter);
                largerAfter -= 1;
            }
        }
        if (array[smallerBefore].compareTo(num) < 0) {
            swap(array, smallerBefore + 1, index);
            return smallerBefore + 1;
        } else {
            swap(array, smallerBefore, index);
            return smallerBefore;
        }
        //[6, 9, 3, 8, 11, 7, 2, 4] 
        //6 > 2, 6 and 7 swap
        //1: [7, 9, 3, 8, 11, 6, 2, 4] 
        //2: [11, 9, 3, 8, 7, 6, 2, 4] largerafter = 3
        //3: [8, 9, 3, 11, 7, 6, 2, 4] largerafter = 2
        //4: [3, 9, 8, 11, 7, 6, 2, 4]
        //5: [9, 3, 8, 11, 7, 6, 2, 4]
    }

    private void swap(Integer[] array, int smallerBefore, int largerAfter) {
        int temp = array[largerAfter];
        int temp1 = array[smallerBefore];
        array[smallerBefore] = temp;
        array[largerAfter] = temp1;

    }
    public static void main(String[] args) {
        bl test = new bl();
        Integer[] array = {6, 9, 3, 8, 11, 7, 2, 4};
        System.out.println(test.partition(array, 0, 7));
        for(Integer fei : array) {
            System.out.println(fei);
        }
    }
}
