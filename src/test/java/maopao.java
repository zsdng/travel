public class maopao {

    public static void main(String[] args) {
        int[] arr = {1, 8, 5, 3, 3, 2, 7};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
            for (int i1 : arr) {
                System.out.println(i1);
            }

    }}
