public class HelloNumbers {
    public static void main(String[] args) {
        int x=0;
	while(x<10) {
		int t=0;
		for(int i=0;i<=x;i++)
		t=t+i;
		System.out.print(t+" ");
		x=x+1;
	}
    }
}